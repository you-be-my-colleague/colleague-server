package youBeMyColleague.study.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;
import youBeMyColleague.study.dto.MemberRequestDto;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    @Nullable
    private Role role;

    private String img;

    @CreationTimestamp
    private Timestamp createDate;

    @Embedded
    private TechStack stack;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<WishList> wishLists = new ArrayList<>();


    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setMember(this);
    }

    public void updateMember(MemberRequestDto memberRequestDto){
        this.name = memberRequestDto.getName();
        this.img = memberRequestDto.getImg();
        this.stack = memberRequestDto.getStack();
    }
    public Member update(String name, String picture) {
        this.name = name;
        this.img = picture;

        return this;
    }
//    public String getRoleKey() {
//        return this.role.getKey();
//    }

    @Builder
    public Member(String name, String email, String img, Role role,TechStack stack, Timestamp createDate){
        this.name = name;
        this.email = email;
        this.img = img;
        this.role = role;
        this.createDate = createDate;
        this.stack = stack;
    }

}