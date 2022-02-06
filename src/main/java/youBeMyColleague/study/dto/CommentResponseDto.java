package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponseDto {

    private String contentCreater;
    private String content;
}
