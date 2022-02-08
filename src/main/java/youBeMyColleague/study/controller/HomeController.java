package youBeMyColleague.study.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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


    @GetMapping(value = "/login/oauth2/code/kakao")
    public String oauthKakaoLogin(@RequestParam("code") String code) {
        log.debug("카카오 로그인 이실행이 되었는지 확인하는 로그");
        return "카카오 인증 완료" + code;
    }
    @GetMapping(value = "/login")
    public String oauthKakaoLogin2(@RequestParam("code") String code) {
        log.debug("카카오 로그인 이실행이 되었는지 확인하는 로그");
        return "카카오 인증 완료" + code;
    }

}















