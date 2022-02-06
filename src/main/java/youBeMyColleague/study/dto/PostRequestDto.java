package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import youBeMyColleague.study.domain.RecruitmentStatus;
import youBeMyColleague.study.domain.TechStack;

@Data
public class PostRequestDto {

    private String title;
    private String content;
    private String gitAddress;
    private TechStack stack;
    private RecruitmentStatus recruitmentStatus;

    public PostRequestDto(String title, String content, String gitAddress, TechStack stack) {
        this.title = title;
        this.content = content;
        this.gitAddress = gitAddress;
        this.stack = stack;
    }

    public PostRequestDto(RecruitmentStatus recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;

    }



}
