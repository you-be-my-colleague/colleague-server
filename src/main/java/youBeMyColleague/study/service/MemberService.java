package youBeMyColleague.study.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import youBeMyColleague.study.security.UserDetailsImpl;
import youBeMyColleague.study.security.kakao.KakaoOAuth2;
import youBeMyColleague.study.security.kakao.KakaoUserInfo;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final KakaoOAuth2 kakaoOAuth2;

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

    // 사용자 정보 삭제
    public void DeleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    //사용자 정보 수정
    public Member updateMember(Long id, MemberRequestDto memberRequestDto) {
        Member findMember = memberRepository.findById(id).orElseThrow(UserNotFoundException::new);
        findMember.updateMember(memberRequestDto);
        return findMember;
    }

    //사용자 작성글 조회
    @Transactional(readOnly = true)
    public Optional<List<Member>> findMemberPost(Long id) {
        return Optional.of(memberRepository.findMemberPost(id).orElseThrow(UserNotFoundException::new));
    }

    // 사용자 정보 조회
    @Transactional(readOnly = true)
    public Optional<MemberResponseDto> findMember(Long member_id) {
        return Optional.of(memberRepository.findOneMember(member_id)).orElseThrow(UserNotFoundException::new);
    }

    //카카오 로그인
    public Optional<Member> kakaoLogin(String authorizedCode) {
        KakaoUserInfo userInfo = kakaoOAuth2.getUserInfo(authorizedCode);
        String email = userInfo.getEmail();

        Member kakaoMember = memberRepository.findByEmail(email).orElse(null);

        // 카카오 정보로 회원가입
        if (kakaoMember == null) {
            Member SameEmail = memberRepository.findByEmail(email).orElse(null);
            if (SameEmail != null) {
                kakaoMember = SameEmail;
                memberRepository.save(kakaoMember);
            } else {

                kakaoMember = Member.builder()
                        .name(userInfo.getName())
                        .img(userInfo.getImg())
                        .email(userInfo.getEmail())
                        .role(Role.USER)
                        .build();

                memberRepository.save(kakaoMember);
            }
        }
        return Optional.of(memberRepository.findByEmail(email)).orElseThrow(UserNotFoundException::new);
    }
}
