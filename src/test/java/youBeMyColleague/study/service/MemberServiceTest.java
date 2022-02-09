package youBeMyColleague.study.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Role;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@Slf4j
class MemberServiceTest {
    
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;


    @Test
    public void 회원가입() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 추가_회원가입() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 회원탈퇴() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 회원정보_수정() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 내_작성글() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 내_찜글() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 찜_하기() throws Exception {
        //given

        //when

        //then
    }

    @Test
    public void 찜_해제() throws Exception {
        //given

        //when

        //then
    }

    
    
    

}