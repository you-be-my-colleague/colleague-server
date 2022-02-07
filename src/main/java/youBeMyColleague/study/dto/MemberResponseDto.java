package youBeMyColleague.study.dto;

import lombok.Data;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.domain.Post;
import youBeMyColleague.study.domain.TechStack;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MemberResponseDto {
    private Long id;

    private String name;

    private String email;

    private String role;

    private String img;

    private Timestamp createDate;

    private TechStack stack;

    private List<PostResponseDto> post;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.role = member.getRole();
        this.img = member.getImg();
        this.createDate = member.getCreateDate();
        this.stack = member.getStack();
        this.post = member.getPosts().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
