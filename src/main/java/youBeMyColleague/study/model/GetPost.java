package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.dto.PostResponseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetPost {

    private boolean success;
    private String msg;
    private List<PostResponseDto> data;



}
