package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.advice.exception.UserNotFoundException;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.dto.PostResponseDto;
import youBeMyColleague.study.model.GetAllPost;
import youBeMyColleague.study.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<GetAllPost> home() {
        List<Post> allPost = postService.findAllPost().orElseThrow(UserNotFoundException::new);
        return new ResponseEntity<>(new GetAllPost(true,"메인페이지 접속",allPost.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList())), HttpStatus.OK);
    }


}















