package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.dto.PostResponseDto;
import youBeMyColleague.study.dto.Wrap;
import youBeMyColleague.study.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<Wrap> home() {
        List<Post> allPost = postService.findAllPost();
        return ResponseEntity.ok().body(new Wrap(allPost.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList())));
    }

}















