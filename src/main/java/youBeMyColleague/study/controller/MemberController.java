package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.service.MemberService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/signup")
    public Member SignMember(@RequestBody MemberRequestDto memberRequestDto){
        Member member = memberService.join(memberRequestDto);
        return member;
    }

    @GetMapping("/my-page/{id}")
    public Optional<Member> selectMember(@PathVariable Long id){
        return memberRepository.findById(id);
    }

    @DeleteMapping("/my-page/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.DeleteMember(id);
        return "삭제 완료";}

    @PatchMapping("/my-page/{id}")
    public String updateMember(@PathVariable Long id,
                               @RequestBody MemberChangeRequestDto memberChangeRequestDto){
        TechStack techStack = new TechStack(memberChangeRequestDto.getStack().getSpring(),memberChangeRequestDto.getStack().getReact(),memberChangeRequestDto.getStack().getPython(),memberChangeRequestDto.getStack().getNode());
        Optional<Member> findMember = memberRepository.findById(id);
        findMember.get().setImg(memberChangeRequestDto.getImg());
        findMember.get().setName(memberChangeRequestDto.getName());
        findMember.get().setStack(techStack);
        memberService.updateMember(findMember.get());
        return "수정 완료";}

    @GetMapping("/my-page/post/my-posts/{id}")
    public String selectPost(@PathVariable Long id){
        return memberService.findMemberPost(id);
    }
}