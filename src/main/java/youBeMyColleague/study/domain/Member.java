package youBeMyColleague.study.domain;


import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    private String img; //프로필 이미지

    @Embedded
    private TechStack stack;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;



    //== 생성자 메서드
//    @Builder
//    public Long createMember(String name, String email, String img,)

    //== 연관관계 메서드




}
