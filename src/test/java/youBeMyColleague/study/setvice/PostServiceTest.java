package youBeMyColleague.study.setvice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
@Transactional(readOnly = true)
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
    public void 게시글_등록_테스트() throws Exception {
        //given
        Member member = new Member();
        memberRepository.save(member);
        System.out.println(member);
        //when
        Post post = postService.createPost(new PostRequestDto("testA", "contentsTest",
                "ekdmd9092@naver.com", new TechStack("s1", "s2", "s3", "")), member);
        //then
        Optional<Post> findpost = postRepository.findById(post.getId());
        assertThat(post).isEqualTo(findpost.get());

    }





}
















