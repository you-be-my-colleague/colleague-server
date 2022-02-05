package youBeMyColleague.study.config.oauth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("ClientRegist: " +userRequest.getClientRegistration());
        System.out.println("AccessToken: " +userRequest.getAccessToken().getTokenValue());
        System.out.println("Attributes: " +super.loadUser(userRequest).getAttributes());
        return super.loadUser(userRequest);
    }
}
