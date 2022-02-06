package youBeMyColleague.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.*;
import youBeMyColleague.study.dto.CommentRequestDto;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.dto.PostResponseDto;
import youBeMyColleague.study.repository.CommentRepository;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class PostServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private PostService postService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CommentRepository commentRepository;



    @Test
    public void 게시글_등록_테스트() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member);
        em.flush();
        //when
        Optional<Post> findpost = postRepository.findById(post.getId());
        //then

        assertThat(post).isEqualTo(findpost.get());

    }

    //게시글 상세
    @Test
    public void 게시글_상세_테스트() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member);
        Comment comment = Comment.builder()
                .content("코멘트 확인")
                .commentDate(LocalDateTime.now())
                .build();
        commentService.createComment(new CommentRequestDto(comment.getContent()),post.getId(),member.getId());
        Optional<Post> findPost = postRepository.findById(post.getId());
        //when
        List<Post> postWithAllComment = postService.findPost(findPost.get().getId());
        List<PostResponseDto> collect = postWithAllComment.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        //then
        assertThat(collect.size()).isEqualTo(1);
        assertThat(collect.get(0).getContent()).isEqualTo(findPost.get().getContent());
        assertThat(collect.get(0).getCreater()).isEqualTo(findPost.get().getMember().getName());
        assertThat(collect.get(0).getGitAddress()).isEqualTo(findPost.get().getGitAddress());
        assertThat(collect.get(0).getStack()).isEqualTo(findPost.get().getStack());
        assertThat(collect.get(0).getTitle()).isEqualTo(findPost.get().getTitle());
    }
    //전체 게시글
    @Test
    @Rollback(false)
    public void 전체_게시글_조회() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("userA");
        member1.setEmail("ekdmd@naver.com");
        member1.setStack(new TechStack(true,false,true,true));
        Member member2 = new Member();
        member2.setName("userB");
        member2.setEmail("dmd@naver.com");
        member2.setStack(new TechStack(true,false,true,false));
        Member member3 = new Member();
        member3.setName("userC");
        member3.setEmail("ek@naver.com");
        member3.setStack(new TechStack(true,false,false,true));
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
        postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member1);
        postService.createPost(new PostRequestDto("testB", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member2);
        postService.createPost(new PostRequestDto("testC", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member3);
        //when
        List<Post> allPost = postService.findAllPost();
        //then
        assertThat(allPost.size()).isEqualTo(3);

    }

    //게시글 수정
    @Test
    public void 게시글_수정() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("userA");
        member1.setEmail("ekdmd@naver.com");
        member1.setStack(new TechStack(true,false,true,true));
        memberService.join(member1);

        Post postA = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true, true, false, false)), member1);

        //when
        Post post = postService.updatePost(postA.getId(), new PostRequestDto("newTestC", "newContentsTest", "ekdmd@gamil.com", new TechStack(false, false, true, false)));
        Optional<Post> findPost = postRepository.findById(postA.getId());
        //then
        assertThat(post.getContent()).isEqualTo(findPost.get().getContent());

    }
    //게시글 상태 수정
    @Test
    public void 게시글_상태_수정_마감() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("userA");
        member1.setEmail("ekdmd@naver.com");
        member1.setStack(new TechStack(true,false,true,true));
        memberService.join(member1);

        Post postA = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true, true, false, false)), member1);

        //when
        Post post = postService.updatePostStatus(postA.getId(),new PostRequestDto(RecruitmentStatus.COMPLETE));
        Optional<Post> findPost = postRepository.findById(postA.getId());
        //then
        assertThat(post.getPostStatus()).isEqualTo(findPost.get().getPostStatus());
    }
    @Test
    public void 게시글_상태_수정_마감취소() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("userA");
        member1.setEmail("ekdmd@naver.com");
        member1.setStack(new TechStack(true,false,true,true));
        Post postA = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true, true, false, false)), member1);
        //when
        Post post = postService.updatePostStatus(postA.getId(),new PostRequestDto(RecruitmentStatus.CONTINUE));
        Optional<Post> findPost = postRepository.findById(postA.getId());
        //then
        assertThat(post.getPostStatus()).isEqualTo(findPost.get().getPostStatus());

    }



}
















