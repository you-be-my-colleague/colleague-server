package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseDto {

    private Long creater_id;
    private String contentCreater;
    private Long comment_id;
    private String content;
    private LocalDateTime contentDate;
}
