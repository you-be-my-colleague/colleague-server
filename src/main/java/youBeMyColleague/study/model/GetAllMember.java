package youBeMyColleague.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.dto.MemberResponseDto;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class GetAllMember {
    private boolean success;
    private String msg;
    private List<MemberResponseDto> data;
}
