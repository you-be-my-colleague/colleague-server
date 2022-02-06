package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import youBeMyColleague.study.repository.PostRepository;
import youBeMyColleague.study.service.PostService;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;


}
