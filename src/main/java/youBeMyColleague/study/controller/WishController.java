package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.service.MemberService;


@RestController
@RequiredArgsConstructor
public class WishController {
    private final MemberService memberService;

    //마이페이지 내 관심글
//    @GetMapping("/my-page/my-likes/{id}")
//    public ResponseEntity<Wrap> selectLikePost(@PathVariable Long id){
//        List<WishList> memberLikePost = memberService.findLikePost(id);
//        return ResponseEntity.ok().body(new Wrap(memberLikePost.stream()
//                .map(MemberResponseDto::new)
//                .collect(Collectors.toList())));
//    }

    //관심글 등록
    @PostMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<String> createLikePost (@PathVariable Long member_id,@PathVariable Long post_id){
        memberService.createLikePost(member_id,post_id);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

    // 관심글 삭제
    @DeleteMapping("/my-page/my-likes/{member_id}/{post_id}")
    public ResponseEntity<String> deleteLikePost(@PathVariable Long member_id, @PathVariable Long post_id){
        memberService.deleteLikePost(member_id,post_id);
        return ResponseEntity.ok().body("{'result':'success'}");
    }
}
