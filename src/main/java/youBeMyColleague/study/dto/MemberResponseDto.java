package youBeMyColleague.study.dto;

import youBeMyColleague.study.domain.TechStack;

import java.sql.Timestamp;

public class MemberResponseDto {
    private Long id;

    private String name;

    private String email;

    private String role;

    private String img;

    private Timestamp createDate;

    private TechStack stack;
}
