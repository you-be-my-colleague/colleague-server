package youBeMyColleague.study.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Comment;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.TechStack;
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
import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@Transactional
@Slf4j
class CommentServiceTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;


    //댓글 등록
    @Test
    public void 댓글_등록() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member);
        //when
        Comment contentTestA = commentService.createComment(new CommentRequestDto("contentTestA"), post.getId(), member.getId());
        Comment contentTestB = commentService.createComment(new CommentRequestDto("contentTestB"), post.getId(), member.getId());
        Comment contentTestC = commentService.createComment(new CommentRequestDto("contentTestC"), post.getId(), member.getId());
        List<Post> findPost = postService.findPost(post.getId());
        List<PostResponseDto> collect = findPost.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
        //then
        assertThat(collect.get(0).getComments().get(0).getId()).isEqualTo(contentTestA.getId());
        assertThat(collect.get(0).getComments().get(1).getId()).isEqualTo(contentTestB.getId());
        assertThat(collect.get(0).getComments().get(2).getId()).isEqualTo(contentTestC.getId());
    }
    //댓글 수정
    @Test
    public void 댓글_수정() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member);
        Comment contentTestA = commentService.createComment(new CommentRequestDto("contentTestA"), post.getId(), member.getId());
        //when
        Comment newContent = commentService.updateComment(contentTestA.getId(), new CommentRequestDto("newContent"));
        //then
        assertThat(contentTestA.getContent()).isEqualTo(newContent.getContent());

    }

    //댓글 삭제
    @Test
    @Rollback(false)
    public void 댓글_삭제() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack(true,true,false,false)), member);
        Comment contentTestA = commentService.createComment(new CommentRequestDto("contentTestA"), post.getId(), member.getId());
        //when
        commentService.deleteComment(contentTestA.getId(),post.getId());
        //then
        List<Post> findPost = postService.findPost(post.getId());
        assertThat(findPost.get(0).getComments().isEmpty()).isTrue();

    }



}
















