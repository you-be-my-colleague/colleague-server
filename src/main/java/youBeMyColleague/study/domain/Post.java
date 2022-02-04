package youBeMyColleague.study.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private RecruitmentStatus postStatus;

    @Column(columnDefinition = "integer default 0")
    private int views;

    @Column(columnDefinition = "integer default 0")
    private int likes;

    @Column(columnDefinition = "integer default 0")
    private int commentCount;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    @Builder
    public Post(Member member, String title, String content, TechStack stack, String gitAddress, RecruitmentStatus postStatus, LocalDateTime postDate) {
        this.title = title;
        this.content = content;
        this.stack = stack;
        this.gitAddress = gitAddress;
        this.postStatus = postStatus;
        this.postDate = postDate;
        this.member = member;
        member.getPosts().add(this);
    }

    //==비즈니스
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
    public void closePost(RecruitmentStatus postStatus) {
        this.postStatus = postStatus;
    }

    //==연관관계 메서드
    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
        this.commentCount += 1;
    }
    public void deleteComment(Comment deleteComment) {
        this.comments.remove(deleteComment);
        deleteComment.setPost(null);
        this.commentCount -= 1;
    }

}







