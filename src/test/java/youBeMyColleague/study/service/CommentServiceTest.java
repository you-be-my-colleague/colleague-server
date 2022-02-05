package youBeMyColleague.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;

import javax.persistence.EntityManager;

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
    private PostRepository postRepository;
    @Autowired
    private MemberRepository memberRepository;


    //댓글 등록
    @Test
    public void () throws Exception {
        //given

        //when

        //then
    }
    //댓글 수정
    @Test
    public void () throws Exception {
        //given

        //when

        //then
    }

    //댓글 삭제
    @Test
    public void () throws Exception {
        //given

        //when

        //then
    }



}
















