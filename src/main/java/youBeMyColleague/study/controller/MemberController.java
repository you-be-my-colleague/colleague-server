package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.dto.Wrap;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.service.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<MemberResponseDto> selectMember(@PathVariable Long id){
        Optional<MemberResponseDto> member = Optional.ofNullable(memberRepository.findOneMember(id));
        return member;
    }

    @DeleteMapping("/my-page/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.DeleteMember(id);
        return "삭제 완료";}

    @PatchMapping("/my-page/{id}")
    public String updateMember(@PathVariable Long id,
                               @RequestBody MemberChangeRequestDto memberChangeRequestDto){
        Optional<Member> findMember = memberRepository.findById(id);
        memberService.updateMember(findMember.get(), memberChangeRequestDto);
        return "수정 완료";}

    @GetMapping("/my-page/post/my-posts/{id}")
    public ResponseEntity<Wrap> selecPost(@PathVariable Long id){
        List<Member> members = memberService.findMemberPost(id);
        return ResponseEntity.ok().body(new Wrap(members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList())));
    }
}