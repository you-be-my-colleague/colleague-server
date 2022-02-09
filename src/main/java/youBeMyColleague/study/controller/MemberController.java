<<<<<<< HEAD
//package youBeMyColleague.study.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import youBeMyColleague.study.domain.Member;
//import youBeMyColleague.study.dto.MemberChangeRequestDto;
//import youBeMyColleague.study.dto.MemberRequestDto;
//import youBeMyColleague.study.dto.MemberResponseDto;
//import youBeMyColleague.study.repository.MemberRepository;
//import youBeMyColleague.study.service.MemberService;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequiredArgsConstructor
//public class MemberController {
//
//    private final MemberService memberService;
//    private final MemberRepository memberRepository;
//
//    // 추가회원가입
//    @PatchMapping("/signup/{id}")
//    public String SignMember(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto) {
//        Optional<Member> findMember = memberRepository.findById(id);
//        memberService.addMemberReg(findMember.get(), memberRequestDto);
//        return "추가회원 가입 완료";
//    }
//
//    //마이페이지
//    @GetMapping("/my-page/{id}")
//    public ResponseEntity<Wrap> selectMember(@PathVariable Long id){
//        MemberResponseDto oneMember = memberRepository.findOneMember(id);
//        return ResponseEntity.ok().body(new Wrap(oneMember));
//    }
//
//    //회원탈퇴
//    @DeleteMapping("/my-page/{id}")
//    public String deleteMember(@PathVariable Long id){
//        memberService.DeleteMember(id);
//        return "삭제 완료";
//    }
//
//    //마이페이지 설정
//    @PatchMapping("/my-page/{id}")
//    public String updateMember(@PathVariable Long id,
//                               @RequestBody MemberChangeRequestDto memberChangeRequestDto){
//        memberService.updateMember(id, memberChangeRequestDto);
//        return "수정 완료";
//    }
//
//    //마이페이지 내작성글
//    @GetMapping("/my-page/post/my-posts/{id}")
//    public ResponseEntity<Wrap> selectPost(@PathVariable Long id){
//        List<Member> members = memberService.findMemberPost(id);
//        return ResponseEntity.ok().body(new Wrap(members.stream()
//                .map(MemberResponseDto::new)
//                .collect(Collectors.toList())));
//    }
//
//
//}
=======
package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.model.CreateMemberSuccess;
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
    private final MemberRepository memberRepository;

    // 추가회원가입
    @PatchMapping("/signup/{id}")
    public ResponseEntity<CreateMemberSuccess> SignMember(@PathVariable Long id, @RequestBody MemberRequestDto memberRequestDto) {
        Optional<Member> findMember = memberRepository.findById(id);
        memberService.addMemberReg(findMember.get(), memberRequestDto);

        return new ResponseEntity<>(new CreateMemberSuccess(true,"추가 회원 가입 완료",findMember),HttpStatus.OK);
    }

    //마이페이지
    @GetMapping("/my-page/{id}")
    public ResponseEntity<GetMember> selectMember(@PathVariable Long id){
        MemberResponseDto oneMember = memberRepository.findOneMember(id);
        return ResponseEntity.ok().body(new GetMember(true,"마이페이지 조회 완료",oneMember));
    }

    //회원탈퇴
    @DeleteMapping("/my-page/{id}")
    public ResponseEntity<Success> deleteMember(@PathVariable Long id){
        memberService.DeleteMember(id);
        return new ResponseEntity<>(new Success(true,"회원 탈퇴 완료"), HttpStatus.OK);
    }

    //마이페이지 설정
    @PatchMapping("/my-page/{id}")
    public ResponseEntity<Success> updateMember(@PathVariable Long id,
                               @RequestBody MemberChangeRequestDto memberChangeRequestDto){
        memberService.updateMember(id, memberChangeRequestDto);
        return new ResponseEntity<>(new Success(true,"회원정보 수정 완료"),HttpStatus.OK);
    }

    //마이페이지 내작성글
    @GetMapping("/my-page/post/my-posts/{id}")
    public ResponseEntity<GetAllMember> selectPost(@PathVariable Long id){
        List<Member> members = memberService.findMemberPost(id);
        return ResponseEntity.ok().body(new GetAllMember(true,"내 작성글 조회완료",members.stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList())));
    }


}
>>>>>>> ff4f82558b4afcf5d0c1f0cc1b3172f7b9b080ed
