package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberChangeRequestDto;
import youBeMyColleague.study.dto.MemberRequestDto;
import youBeMyColleague.study.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
    public void addMemberReg(Member member, MemberRequestDto memberRequestDto){
        member.updateMember(memberRequestDto.getName(), memberRequestDto.getImg(), memberRequestDto.getStack());
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
    public void updateMember(Member member, MemberChangeRequestDto memberChangeRequestDto) {
            member.updateMember(memberChangeRequestDto.getName(), memberChangeRequestDto.getImg(), memberChangeRequestDto.getStack());
    }

    public List<Member> findMemberPost(Long id) {
        Optional<List<Member>> memberPost = memberRepository.findMemberPost(id);
        return memberPost.get();
    }
}
