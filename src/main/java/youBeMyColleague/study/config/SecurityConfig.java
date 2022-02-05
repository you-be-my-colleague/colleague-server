package youBeMyColleague.study.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import youBeMyColleague.study.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
                .antMatchers("/test").authenticated() // 인증이 필요한 페이지
                .anyRequest().permitAll()
                .and()
                .oauth2Login()
                .loginPage("/")
                .userInfoEndpoint()
                .userService(principalOauth2UserService); //구글 로그인 후 후처리
//                .and()
//                .formLogin()
//                .loginPage("/loginPage"); //로그인 페이지
//                .loginProcessingUrl("/login"); //  login 주소 호출되면 springSecurity로 대신 로그인 진행
//                .defaultSuccessUrl("/");    //  로그인 성공시  이동 할 페이지
    }
}