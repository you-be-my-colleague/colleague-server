package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.dto.PostResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPost {

    private boolean success;
    private String msg;
    private List<PostResponseDto> data;

}
