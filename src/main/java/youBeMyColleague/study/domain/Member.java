package youBeMyColleague.study.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @Column(nullable = false)
    private Role role;

    private String img;

    @CreationTimestamp
    private Timestamp createDate;

    @Embedded
    private TechStack stack;

    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();
    
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<WishList> wishLists = new ArrayList<>();


    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setMember(this);
    }

    public void updateMember(String name,String img, TechStack stack){
        this.name = name;
        this.img = img;
        this.stack = stack;
    }
    public Member update(String name, String picture) {
        this.name = name;
        this.img = picture;

        return this;
    }
    public String getRoleKey() {
        return this.role.getKey();
    }

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