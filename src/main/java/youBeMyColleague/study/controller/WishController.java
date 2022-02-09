package youBeMyColleague.study.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import youBeMyColleague.study.advice.exception.WishListCreateException;
import youBeMyColleague.study.advice.exception.WishListNotFoundException;
import youBeMyColleague.study.domain.WishList;
import youBeMyColleague.study.dto.WishResponseDto;
import youBeMyColleague.study.model.GetAllWishList;
import youBeMyColleague.study.model.Success;
import youBeMyColleague.study.service.MemberService;
import youBeMyColleague.study.service.WishService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class WishController {

    private final WishService wishService;
    //마이페이지 내 관심글
    @GetMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<GetAllWishList> selectLikePost(@PathVariable Long member_id, @PathVariable Long post_id){
        List<WishList> memberLikePost = Optional.of(wishService.findLikePost(member_id, post_id))
                .orElseThrow(WishListNotFoundException::new);
        return new ResponseEntity<>(new GetAllWishList(true,"내 관심글 조회성공",memberLikePost.stream()
                .map(WishResponseDto::new)
                .collect(Collectors.toList())), HttpStatus.OK);
    }

    //관심글 등록
    @PostMapping("/post-like/{member_id}/{post_id}")
    public ResponseEntity<Success> createLikePost (@PathVariable Long member_id, @PathVariable Long post_id){
        Optional.of(wishService.createLikePost(member_id, post_id)).orElseThrow(WishListCreateException::new);
        return new ResponseEntity<>(new Success(true,"내 관심글 등록 성공"),HttpStatus.OK);
    }

    // 관심글 삭제
    @DeleteMapping("/my-page/my-likes/{member_id}/{post_id}")
    public ResponseEntity<Success> deleteLikePost(@PathVariable Long member_id, @PathVariable Long post_id){
        wishService.deleteLikePost(member_id,post_id);
        return new ResponseEntity<>(new Success(true, "내 관심글 삭제 성공"),HttpStatus.OK);
    }
}







