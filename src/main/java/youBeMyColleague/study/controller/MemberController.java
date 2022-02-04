package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.service.MemberService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signUp")
    public Member SignMember(@RequestBody MemberRequestDto memberRequestDto){

        TechStack techStack = new TechStack(memberRequestDto.getStack().getStack1(), memberRequestDto.getStack().getStack2(), memberRequestDto.getStack().getStack3(), memberRequestDto.getStack().getStack4());
        Member member = new Member();
        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setImg(memberRequestDto.getImg());
        member.setStack(techStack);
        memberService.join(member);
        return member;
    }

    @GetMapping("/myPage/{email}")
    public Member selectMember(@PathVariable String email){
        return memberRepository.findByEmail(email);
    }
}