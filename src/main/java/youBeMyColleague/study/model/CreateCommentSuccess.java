package youBeMyColleague.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.CommentResponseDto;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentSuccess {

    private boolean success;
    private String msg;
    private Long comment_id;
}
