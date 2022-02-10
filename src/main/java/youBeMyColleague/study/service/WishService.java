package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.advice.exception.*;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.WishList;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;
import youBeMyColleague.study.repository.WishListRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WishService {

    private final WishListRepository wishListRepository;

    private final MemberRepository memberRepository;

    private final PostRepository postRepository;

    //관심글 해제
    public void deleteLikePost(Long member_id, Long post_id) {
        WishList findWish = wishListRepository.findWishListByMemberWithPost(member_id, post_id)
                .orElseThrow(WishListCanNotDeleteException::new);
        findWish.deleteWish();
        wishListRepository.deleteById(findWish.getId());
    }

    //멤버 관심글 조회
    @Transactional(readOnly = true)
    public List<WishList> findLikePost(Long member_id, Long post_id) {
        return wishListRepository.findPost(member_id,post_id).orElseThrow(WishListNotFoundException::new);
    }
    
    //관심글 등록
    public Optional<WishList> createLikePost(Long member_id, Long post_id) {
        Member member = memberRepository.findById(member_id).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(post_id).orElseThrow(PostNotFoundException::new);
        Optional<WishList> wishListByMemberWithPost = wishListRepository.findWishListByMemberWithPost(member_id, post_id);
        if (!wishListByMemberWithPost.isPresent()) {
            WishList wishList = WishList.builder()
                    .member(member)
                    .post(post)
                    .build();
            wishListRepository.save(wishList);
        }else {
            throw new WishListCreateException();
        }
        return wishListByMemberWithPost;
    }
}
