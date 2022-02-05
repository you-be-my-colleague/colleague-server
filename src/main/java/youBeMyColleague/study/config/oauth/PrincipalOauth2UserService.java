package youBeMyColleague.study.config.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import youBeMyColleague.study.config.oauth.provider.GoogleUserInfo;
import youBeMyColleague.study.config.oauth.provider.OAuth2UserInfo;
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

        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }

        assert oAuth2UserInfo != null;
        String name = oAuth2UserInfo.getName();
        String email = oAuth2UserInfo.getEmail();
        String img = oAuth2UserInfo.getImg();
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
