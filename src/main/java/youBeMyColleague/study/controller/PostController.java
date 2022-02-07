package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.dto.PostRequestDto;
import youBeMyColleague.study.dto.PostResponseDto;
import youBeMyColleague.study.dto.Wrap;
import youBeMyColleague.study.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //1. 상세게시글 조회
    @GetMapping("/post-detail/{post_id}")
    public ResponseEntity<Wrap> findOnePost(@PathVariable("post_id") Long postId) {
        List<Post> post = postService.findPost(postId);
        return ResponseEntity.ok().body(new Wrap(post.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList())));
    }

    //2. 게시글 작성
    @PostMapping("/post/{creater_id}")
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto, @PathVariable("creater_id") Long createrId) {
        postService.createPost(postRequestDto, createrId);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

    //3. 게시글 수정
    @PatchMapping("/post/{post_id}")
    public ResponseEntity<String> updatePost(@PathVariable("post_id") Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return ResponseEntity.ok().body("{'result':'success'}");
    }

    //4. 게시글 삭제 -> 게시글 삭제시 댓글도 삭제됨
    @DeleteMapping("/post/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().body("{'result':'success'}");
    }
//5. 게시글 마감
    @PutMapping("/post/{post_id}")
    public ResponseEntity<String> updatePostStatus(@PathVariable("post_id") Long postId, @RequestBody PostRequestDto postRequestDto) {
        postService.updatePostStatus(postId,postRequestDto);
        return ResponseEntity.ok().body("{'result':'success'}");
    }
}
