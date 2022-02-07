package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.dto.CommentRequestDto;
import youBeMyColleague.study.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //1. 댓글 등록
    @PostMapping("/comment/{creater_id}/{post_id}")
    public ResponseEntity<String> createComment(@PathVariable("creater_id") Long createrId,
                                                @PathVariable("post_id") Long post_id,
                                                @RequestBody CommentRequestDto commentRequestDto) {
        commentService.createComment(commentRequestDto, post_id, createrId);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

    //    2. 댓글 수정
    @PatchMapping("/comment/{comment_id}")
    public ResponseEntity<String> updateComment(@PathVariable("comment_id") Long commnetId,
                                                @RequestBody CommentRequestDto commentRequestDto) {
        commentService.updateComment(commnetId,commentRequestDto);
        return ResponseEntity.ok().body("{'result':'success'}");
    }
//    3. 댓글 삭제
    @DeleteMapping("/comment/{comment_id}/{post_id}")
    public ResponseEntity<String> deleteComment(@PathVariable("comment_id") Long commnetId,
                                                @PathVariable("post_id") Long postId) {
        commentService.deleteComment(commnetId,postId);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

}








