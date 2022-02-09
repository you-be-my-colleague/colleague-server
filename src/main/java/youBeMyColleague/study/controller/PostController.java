package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.dto.PostResponseDto;
import youBeMyColleague.study.model.CreatePostSuccess;
import youBeMyColleague.study.model.GetPost;
import youBeMyColleague.study.model.Success;
import youBeMyColleague.study.service.PostService;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    //1. 상세게시글 조회
    @GetMapping("/post/{post_id}")
    public ResponseEntity<GetPost> findOnePost(@PathVariable("post_id") Long postId) {
        List<Post> post = postService.findPost(postId);
        return new ResponseEntity<>(new GetPost(true,"상세글 조회성공", post.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList())),HttpStatus.OK);
    }

    //2. 게시글 작성
    @PostMapping("/post/{creater_id}")
    public ResponseEntity<CreatePostSuccess> createPost(@RequestBody PostRequestDto postRequestDto,
                                             @PathVariable("creater_id") Long createrId) {
        Long postId = postService.createPost(postRequestDto, createrId);
        return new ResponseEntity<>(new CreatePostSuccess(true,"게시글 생성성공",postId)
                ,HttpStatus.OK);
    }

    //3. 게시글 수정
    @PatchMapping("/post/{post_id}")
    public ResponseEntity<Success> updatePost(@PathVariable("post_id") Long postId,
                                             @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return new ResponseEntity<>(new Success(true,"게시글 수정완료"),HttpStatus.OK);
    }

    //4. 게시글 삭제 -> 게시글 삭제시 댓글도 삭제됨
    @DeleteMapping("/post/{post_id}")
    public ResponseEntity<Success> deletePost(@PathVariable("post_id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity(new Success(true,"게시글 삭제완료"),HttpStatus.OK);
    }
//5. 게시글 마감
    @PutMapping("/post/{post_id}")
    public ResponseEntity<Success> updatePostStatus(@PathVariable("post_id") Long postId,
                                                   @RequestBody PostRequestDto postRequestDto) {
        postService.updatePostStatus(postId,postRequestDto);
        return new ResponseEntity<>(new Success(true,"게시글 상태 수정완료"),HttpStatus.OK);
    }
}



