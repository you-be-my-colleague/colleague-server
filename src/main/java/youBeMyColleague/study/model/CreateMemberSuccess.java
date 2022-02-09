package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import youBeMyColleague.study.domain.Member;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class CreateMemberSuccess {

    private boolean success;
    private String msg;
    private Optional<Member> member_id;
}
