package youBeMyColleague.study.dto;

import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.domain.TechStack;

@Getter @Setter
public class MemberChangeRequestDto {

    private String name;

    private String img;

    private TechStack stack;
}
