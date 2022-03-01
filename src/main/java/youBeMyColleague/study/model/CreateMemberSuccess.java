package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.MemberResponseDto;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberSuccess {

    private boolean success;
    private String msg;
    //private MemberResponseDto member;
}
