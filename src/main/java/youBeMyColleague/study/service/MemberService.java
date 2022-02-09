package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;

import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.Role;
import youBeMyColleague.study.domain.WishList;

import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.repository.MemberRepository;
import youBeMyColleague.study.repository.PostRepository;
import youBeMyColleague.study.repository.WishListRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final WishListRepository wishListRepository;

    // 회원가입
    @Transactional
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

    @Transactional
    public Member addMemberReg(Member member, MemberRequestDto memberRequestDto){
        member.updateMember(memberRequestDto.getName(), memberRequestDto.getImg(), memberRequestDto.getStack());
        return member;
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (findMembers.isPresent()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void DeleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public Member updateMember(Long id, MemberChangeRequestDto memberChangeRequestDto) {
        Optional<Member> findMember = memberRepository.findById(id);
        findMember.get().updateMember(memberChangeRequestDto.getName(), memberChangeRequestDto.getImg(), memberChangeRequestDto.getStack());
        return findMember.get();
    }

    @Transactional(readOnly = true)
    public List<Member> findMemberPost(Long id) {
        Optional<List<Member>> memberPost = memberRepository.findMemberPost(id);
        return memberPost.get();
    }

    public List<WishList> findLikePost(Long member_id,Long post_id) {
        Optional<List<WishList>> memberFindWish = wishListRepository.finyPost(member_id,post_id);
        return memberFindWish.get();
    }

    @Transactional
    public WishList createLikePost(Long member_id, Long post_id) {
        Optional<Member> member = memberRepository.findById(member_id);
        Optional<Post> post = postRepository.findById(post_id);
        if (!post.isPresent() || !member.isPresent()){
            throw new IllegalStateException("해당 포스트가 존재하지 않습니다.");
        }
        WishList wishList = WishList.builder()
                .member(member.get())
                .post(post.get())
                .build();
        return wishListRepository.save(wishList);
    }

    @Transactional
    public void deleteLikePost(Long member_id, Long post_id) {
        Optional<WishList> findWish = wishListRepository.findWishListByMemberWithPost(member_id,post_id);
        if (!findWish.isPresent()){
            throw new IllegalStateException("관심글을 찾을 수 없습니다");
        }
        findWish.get().deleteWish();
        wishListRepository.deleteById(findWish.get().getId());
    }
}
