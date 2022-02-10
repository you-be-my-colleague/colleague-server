package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.advice.exception.EmptyValueException;
import youBeMyColleague.study.dto.CommentRequestDto;
import youBeMyColleague.study.model.Success;
import youBeMyColleague.study.service.CommentService;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //1. 댓글 등록
    @PostMapping("/comment/{creater_id}/{post_id}")
    public ResponseEntity<Success> createComment(@PathVariable("creater_id") Long createrId,
                                                 @PathVariable("post_id") Long post_id,
                                                 @RequestBody CommentRequestDto commentRequestDto) {
        if(commentRequestDto.getContent().isEmpty()){
            throw new EmptyValueException();
        }

        commentService.createComment(commentRequestDto, post_id, createrId);
        return new ResponseEntity<>(new Success(true,"댓글 등록 완료"), HttpStatus.OK);
    }

    //2. 댓글 수정
    @PatchMapping("/comment/{comment_id}")
    public ResponseEntity<Success> updateComment(@PathVariable("comment_id") Long commentId,
                                                @RequestBody CommentRequestDto commentRequestDto) {
        if(commentRequestDto.getContent().isEmpty()){
            throw new EmptyValueException();
        }
        
        commentService.updateComment(commentId,commentRequestDto);
        return new ResponseEntity<>(new Success(true,"댓글 수정 완료"),HttpStatus.OK);
    }
    //3. 댓글 삭제
    @DeleteMapping("/comment/{comment_id}/{post_id}")
    public ResponseEntity<Success> deleteComment(@PathVariable("comment_id") Long commentId,
                                                @PathVariable("post_id") Long postId) {
        commentService.deleteComment(commentId,postId);
        return new ResponseEntity<>(new Success(true,"댓글 삭제 완료"),HttpStatus.OK);
    }

}








