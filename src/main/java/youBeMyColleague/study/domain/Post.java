package youBeMyColleague.study.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Embedded
    @Column(nullable = false)
    private TechStack stack;

    @Column(columnDefinition = "varchar(255) default ''")
    private String gitAddress;

    @Enumerated(EnumType.STRING)
    private recruitmentStatus postStatus;

    @Column(columnDefinition = "integer default 0")
    private int views;

    @Column(columnDefinition = "integer default 0")
    private int likes;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(Member member,String title, String content, TechStack stack, String gitAddress, recruitmentStatus postStatus, LocalDateTime postDate) {
        this.title = title;
        this.content = content;
        this.stack = stack;
        this.gitAddress = gitAddress;
        this.postStatus = postStatus;
        this.postDate = postDate;
        this.member = member;
    }

    /**
     * 게시글 수정
     */
    public void updatePost(String title, String content, TechStack stack, String gitAddress) {
        this.title = title;
        this.content = content;
        this.stack = stack;
        this.gitAddress = gitAddress;
    }

    /**
     * 게시글 마감
     */
    public void closePost(recruitmentStatus postStatus) {
        this.postStatus = postStatus;
    }

    //==연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }
    public void deleteComment(Comment deleteComment) {
        this.comments.remove(deleteComment);
        deleteComment.setPost(null);
    }

}








