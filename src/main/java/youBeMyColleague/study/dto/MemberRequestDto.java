package youBeMyColleague.study.dto;

import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.domain.TechStack;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberRequestDto {

    private String name;

    @NotEmpty(message = "이메일은 필수입니다??")
    private String email;

    private String img;

    private TechStack stack;
}
