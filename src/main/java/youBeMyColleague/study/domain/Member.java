package youBeMyColleague.study.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    @JsonIgnore
    private Long id;

    private String name;

    private String email;

    @JsonIgnore
    private String role;

    private String img;

    @CreatedDate
    private String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

    @Embedded
    private TechStack stack;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
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
}