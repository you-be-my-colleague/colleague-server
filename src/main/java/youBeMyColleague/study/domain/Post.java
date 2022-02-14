package youBeMyColleague.study.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import youBeMyColleague.study.dto.PostRequestDto;

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
    @Lob
    private String content;

    @Embedded
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

    @OneToOne
    private WishList wishList;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
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
    public void addViewsCount() {
        this.views += 1;
    }

    //==비즈니스
    /**
     * 게시글 수정
     */
    public void updatePost(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.stack = postRequestDto.getStack();
        this.gitAddress = postRequestDto.getGitAddress();
        this.postDate = LocalDateTime.now();
    }
    /**
     * 게시글 좋아요 수 카운트
     */
    public void upLikeCount() {
        this.likes += 1;
    }
    public void downLikeCount() {
        this.likes -= 1;
    }

    /**
     * 게시글 상태 수정
     */
    public void updatePostStatus(PostRequestDto postRequestDto) {
        this.postStatus = postRequestDto.getPostStatus();
    }

    //==연관관계 메서드
    public void addComment(Comment comment) {
        comment.setPost(this);
        this.commentCount += 1;
    }
    public void deleteComment(Comment deleteComment) {
        deleteComment.deletePost(this);
        this.commentCount -= 1;
    }



}








