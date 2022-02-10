package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.advice.exception.EmptyValueException;
import youBeMyColleague.study.advice.exception.PostNotFoundException;
import youBeMyColleague.study.advice.exception.UserNameDuplicateException;
import youBeMyColleague.study.advice.exception.UserNotFoundException;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.model.GetAllMember;
import youBeMyColleague.study.model.GetMember;
import youBeMyColleague.study.model.Success;
import youBeMyColleague.study.service.MemberService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 추가회원가입
    @PatchMapping("/signup/{memberId}")
    public ResponseEntity<Success> signMember(@PathVariable Long memberId, @RequestBody MemberRequestDto memberRequestDto) {
        if (memberRequestDto.getName().isEmpty()) {
            throw new EmptyValueException();
        }
        if (Objects.equals(memberService.findMember(memberId).get().getName(), memberRequestDto.getName())) {
            throw new UserNameDuplicateException();
        }
        memberService.createMember(memberId, memberRequestDto);
        return new ResponseEntity<>(new Success(true,"추가 회원 가입 완료"),HttpStatus.OK);
    }

    //마이페이지
    @GetMapping("/my-page/{member_id}")
    public ResponseEntity<GetMember> settingMember(@PathVariable Long member_id){
        MemberResponseDto findMember = memberService.findMember(member_id).orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(new GetMember(true,"마이페이지 조회 완료",findMember),HttpStatus.OK);
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
                               @RequestBody MemberRequestDto memberRequestDto){
        log.info(memberRequestDto.getName());
        if (memberRequestDto.getName().isEmpty()) {
            throw new EmptyValueException();
        }
        if (Objects.equals(memberService.findMember(memberId).get().getName(), memberRequestDto.getName())) {
            throw new UserNameDuplicateException();
        }
        memberService.updateMember(memberId, memberRequestDto);
        return new ResponseEntity<>(new Success(true,"회원정보 수정 완료"),HttpStatus.OK);
    }

    //마이페이지 내 작성글
    @GetMapping("/my-page/my-posts/{memberId}")
    public ResponseEntity<GetAllMember> myCreatePost(@PathVariable Long memberId){
        List<Member> members = memberService.findMemberPost(memberId).orElseThrow(PostNotFoundException::new);
        return new ResponseEntity<>(new GetAllMember(true,"내 작성글 조회완료",members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList())),HttpStatus.OK);
    }

    //카카오 로그인 callback
    @GetMapping("/user/kakao/callback")
    public ResponseEntity<GetMember> kakaoLogin(String code){
        Optional<Member> memberResponseDto = memberService.kakaoLogin(code);
        return new ResponseEntity<>(new GetMember(true,"카카오 회원가입 완료",memberResponseDto),HttpStatus.OK);
    }
}