package youBeMyColleague.study.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.dto.MemberResponseDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMember {
    private boolean success;
    private String msg;
    private List<MemberResponseDto> data;
}
