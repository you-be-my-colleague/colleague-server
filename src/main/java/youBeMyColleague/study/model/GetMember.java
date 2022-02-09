package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.dto.PostResponseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetMember {

    private boolean success;
    private String msg;
    private MemberResponseDto data;



}
