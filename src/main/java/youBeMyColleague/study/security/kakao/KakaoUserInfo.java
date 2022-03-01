package youBeMyColleague.study.security.kakao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import youBeMyColleague.study.domain.Role;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    Long id;
    String email;
    String name;
    String img;
}