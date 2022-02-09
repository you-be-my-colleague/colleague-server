package youBeMyColleague.study.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.WishList;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.dto.WishResponseDto;
import youBeMyColleague.study.dto.Wrap;
import youBeMyColleague.study.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WishController {
    private final MemberService memberService;

    //마이페이지 내 관심글
    @GetMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<Wrap> selectLikePost(@PathVariable Long member_id,@PathVariable Long post_id){
        List<WishList> memberLikePost = memberService.findLikePost(member_id, post_id);
        return ResponseEntity.ok().body(new Wrap(memberLikePost.stream()
                .map(WishResponseDto::new)
                .collect(Collectors.toList())));
    }

    //마이페이지 내 관심글 등록
    @PostMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<String> createLikePost (@PathVariable Long member_id,@PathVariable Long post_id){
        memberService.createLikePost(member_id,post_id);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

    // 마이페이지 내 관심글 삭제
    @DeleteMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<String>  deleteLikePost(@PathVariable Long member_id,@PathVariable Long post_id){
        memberService.deleteLikePost(member_id,post_id);
        return ResponseEntity.ok().body("{'result':'success'}");
    }
}
