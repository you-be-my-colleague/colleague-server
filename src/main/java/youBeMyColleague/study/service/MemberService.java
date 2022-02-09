package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.advice.exception.*;
import youBeMyColleague.study.domain.Member;

import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.Role;
import youBeMyColleague.study.domain.WishList;

import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;
import youBeMyColleague.study.repository.WishListRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final WishListRepository wishListRepository;

    // 회원가입
    public Member join(MemberRequestDto memberRequestDto) {
        Member member = Member.builder()
                .name(memberRequestDto.getName())
                .img(memberRequestDto.getImg())
                .role(Role.USER)
                .stack(memberRequestDto.getStack())
                .build();
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member;
    }
    //추가 회원가입
    public void createMember(Long id, MemberRequestDto memberRequestDto) {
        Member createMember = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        createMember.updateMember(memberRequestDto);
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (findMembers.isPresent()) {
            throw new UserNameDuplicateException();
        }
    }

    public void DeleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    //멤버 정보 수정
    public Member updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member findMember = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        findMember.updateMember(memberRequestDto);
        return findMember;
    }
    //멤버 작성글 조회
    @Transactional(readOnly = true)
    public Optional<List<Member>> findMemberPost(Long id) {
        return Optional.of(memberRepository.findMemberPost(id).orElseThrow(UserNotFoundException::new));
    }
    //멤버 관심글 조회
    @Transactional(readOnly = true)
    public List<WishList> findLikePost(Long member_id,Long post_id) {
        return wishListRepository.findPost(member_id,post_id).orElseThrow(WishListNotFoundException::new);
    }
    //관심글 등록
    public WishList createLikePost(Long member_id, Long post_id) {
        Member member = memberRepository.findById(member_id).orElseThrow(UserNotFoundException::new);
        Post post = postRepository.findById(post_id).orElseThrow(PostNotFoundException::new);
        WishList wishList = WishList.builder()
                .member(member)
                .post(post)
                .build();
        return wishListRepository.save(wishList);
    }
    //관심글 해제
    public void deleteLikePost(Long member_id, Long post_id) {
        WishList findWish = wishListRepository.findWishListByMemberWithPost(member_id, post_id)
                .orElseThrow(WishListCanNotDeleteException::new);
        findWish.deleteWish();
        wishListRepository.deleteById(findWish.getId());
    }


    @Transactional(readOnly = true)
    public Optional<MemberResponseDto> findMember(Long member_id) {
        return Optional.of(memberRepository.findOneMember(member_id)).orElseThrow(UserNotFoundException::new);
    }
}
