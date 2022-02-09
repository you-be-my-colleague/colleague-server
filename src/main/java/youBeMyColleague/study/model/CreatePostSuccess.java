package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreatePostSuccess {

    private boolean success;
    private String msg;
    private Long post_Id;
}
