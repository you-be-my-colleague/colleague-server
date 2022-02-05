package youBeMyColleague.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

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
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;



    @Test
    @Rollback(false)
    public void 게시글_등록_테스트() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        em.flush();
        //when
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack("s1", "s2", "s3", "")), member);
        //then
        Optional<Post> findpost = postRepository.findById(post.getId());
        assertThat(post).isEqualTo(findpost.get());

    }

    //게시글 상세
    @Test
    public void 게시글_상세_테스트() throws Exception {
        //given

        //when

        //then
    }
    //전체 게시글
    @Test
    public void 전체_게시글_조회() throws Exception {
        //given

        //when

        //then
    }

    //게시글 수정
    @Test
    public void 게시글_수정() throws Exception {
        //given

        //when

        //then
    }
    //게시글 상태 수정
    @Test
    public void 게시글_상태_수정() throws Exception {
        //given

        //when

        //then
    }



}
















