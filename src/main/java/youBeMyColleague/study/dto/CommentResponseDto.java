package youBeMyColleague.study.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponseDto {

    private Long creater_id;
    private String contentCreater;
    private String content;
    private LocalDateTime contentDate;
}
