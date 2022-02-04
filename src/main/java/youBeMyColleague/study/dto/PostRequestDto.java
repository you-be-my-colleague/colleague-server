package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import youBeMyColleague.study.domain.TechStack;

@Data
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String gitAddress;
    private TechStack stack;

}
