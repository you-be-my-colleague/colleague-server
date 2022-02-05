package youBeMyColleague.study.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("ClientRegist: " +userRequest.getClientRegistration());
        System.out.println("AccessToken: " +userRequest.getAccessToken().getTokenValue());
        System.out.println("Attributes: " +super.loadUser(userRequest).getAttributes());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        String img = oAuth2User.getAttribute("picture");
        String role = "ROLE_USER";

        Member memberEntity = memberRepository.findByEmail(email);

        if(memberEntity == null){
            memberEntity = Member.builder()
                    .name(name)
                    .email(email)
                    .img(img)
                    .role(role)
                    .build();
            memberRepository.save(memberEntity);
        }

        return super.loadUser(userRequest);
    }
}
