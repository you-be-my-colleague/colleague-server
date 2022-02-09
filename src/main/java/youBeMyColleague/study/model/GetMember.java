package youBeMyColleague.study.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import youBeMyColleague.study.domain.Role;
import youBeMyColleague.study.domain.TechStack;
import youBeMyColleague.study.dto.MemberResponseDto;
import youBeMyColleague.study.dto.PostResponseDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMember {

    private boolean success;
    private String msg;
    private Long member_id;
    private String name;
    private String email;
    private Role role;
    private String img;
    private Timestamp createDate;
    private TechStack stack;

    public GetMember(boolean success, String msg,MemberResponseDto data) {
        this.success = success;
        this.msg = msg;
        this.member_id = data.getId();
        this.name = data.getName();
        this.email = data.getEmail();
        this.role = data.getRole();
        this.img = data.getImg();
        this.createDate = data.getCreateDate();
        this.stack = data.getStack();
    }
}
