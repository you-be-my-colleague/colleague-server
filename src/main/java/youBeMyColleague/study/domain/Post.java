package youBeMyColleague.study.domain;


import lombok.Getter;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private List<> stack;

    private String gitAddress;

    private recruitmentStatus postStatus;

    private int views;

    private int likes;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("member_id")
    private Member member;

    @OneToMany(mappedBy = "comment_id")
    private List<Comment> comment;


}
