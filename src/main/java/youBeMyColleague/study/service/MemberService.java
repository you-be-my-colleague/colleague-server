package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;

import youBeMyColleague.study.domain.Post;
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
                .role("ROLE_USER")
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
        List<Member> findMembers = memberRepository.findByName(member.getName());
        System.out.println("findMembers" + findMembers);
        if (!findMembers.isEmpty()) {
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
        log.info("test"+memberPost.get());
        return memberPost.get();
    }

    public List<Member> findLikePost(Long id) {
        Optional<List<Member>> memberLikePost = memberRepository.findMemberLikePost(id);
        return memberLikePost.get();
    }

    @Transactional
    public WishList createLikePost(Long member_id, Long post_id) {
        Optional<Member> memberId = memberRepository.findById(member_id);
        Optional<Post> postId = postRepository.findById(post_id);
        WishList wishList = WishList.builder()
                .member(memberId.get())
                .post(postId.get())
                .build();
        return wishListRepository.save(wishList);
    }

    @Transactional
    public void deleteLikePost(Long wishList_id) {
        wishListRepository.deleteById(wishList_id);
    }
}
