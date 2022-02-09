package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.model.GetAllMember;
import youBeMyColleague.study.model.GetMember;
import youBeMyColleague.study.model.Success;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.service.MemberService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 추가회원가입
    @PatchMapping("/signup/{memberId}")
    public String SignMember(@PathVariable Long memberId, @RequestBody MemberRequestDto memberRequestDto) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        memberService.addMemberReg(findMember.get(), memberRequestDto);
        return "추가회원 가입 완료";
    }

    //마이페이지
    @GetMapping("/my-page/{memberId}")
    public ResponseEntity<GetMember> selectMember(@PathVariable Long memberId){
        MemberResponseDto findMember = memberRepository.findOneMember(memberId);
        return new ResponseEntity<>(new GetMember(true,"마이페이지 조회 완료",findMember),
                HttpStatus.OK);
    }

    //회원탈퇴
    @DeleteMapping("/my-page/{memberId}")
    public ResponseEntity<Success> deleteMember(@PathVariable Long memberId){
        memberService.DeleteMember(memberId);
        return new ResponseEntity<>(new Success(true,"회원 탈퇴 완료"), HttpStatus.OK);
    }

    //마이페이지 설정
    @PatchMapping("/my-page/{memberId}")
    public ResponseEntity<Success> updateMember(@PathVariable Long memberId,
                               @RequestBody MemberChangeRequestDto memberChangeRequestDto){
        memberService.updateMember(memberId, memberChangeRequestDto);
        return new ResponseEntity<>(new Success(true,"회원정보 수정 완료"),HttpStatus.OK);
    }

    //마이페이지 내작성글
    @GetMapping("/my-page/post/my-posts/{memberId}")
    public ResponseEntity<GetAllMember> selectPost(@PathVariable Long memberId){
        List<Member> members = memberService.findMemberPost(memberId);
        return new ResponseEntity<>(new GetAllMember(true,"내 작성글 조회완료",members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList())),HttpStatus.OK);
    }


}
