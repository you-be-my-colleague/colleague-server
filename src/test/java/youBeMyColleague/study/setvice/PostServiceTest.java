package youBeMyColleague.study.setvice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private EntityManager em;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;


    @Test
    public void 게시글_등록_테스트() throws Exception {
        //given


        //when

        //then
    }





}
















