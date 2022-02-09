package youBeMyColleague.study.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.Role;
import youBeMyColleague.study.domain.TechStack;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    private String name;

    private String img;

    private TechStack stack;

    private Role role;
}
