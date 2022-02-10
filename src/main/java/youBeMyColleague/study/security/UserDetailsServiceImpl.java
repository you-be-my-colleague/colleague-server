package youBeMyColleague.study.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.repository.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private MemberRepository memberRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = (Member) memberRepository.findByName(username);
        return new UserDetailsImpl(member);
    }
}