package youBeMyColleague.study.dto;

import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.domain.TechStack;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberRequestDto {

    private String name;

    private String img;

    private TechStack stack;
}
