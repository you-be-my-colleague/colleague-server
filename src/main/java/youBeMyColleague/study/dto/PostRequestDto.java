package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import youBeMyColleague.study.domain.RecruitmentStatus;
import youBeMyColleague.study.domain.TechStack;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {

    private String title;
    private String content;
    private String gitAddress;
    private TechStack stack;
    private RecruitmentStatus postStatus;

    public PostRequestDto(String title, String content, String gitAddress, TechStack stack) {
        this.title = title;
        this.content = content;
        this.gitAddress = gitAddress;
        this.stack = stack;
    }

}
