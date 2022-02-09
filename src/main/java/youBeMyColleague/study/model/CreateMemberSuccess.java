package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.Member;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberSuccess {

    private boolean success;
    private String msg;
    private Member member;
}
