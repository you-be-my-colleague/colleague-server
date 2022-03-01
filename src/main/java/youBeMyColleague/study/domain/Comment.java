package youBeMyColleague.study.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import youBeMyColleague.study.dto.CommentRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Comment(String content, LocalDateTime commentDate) {
        this.content = content;
        this.commentDate = commentDate;
    }

    //==연관관계 메서드==//
    public void setMember(Member member) {
        this.member = member;
        member.getComments().add(this);
    }
    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);
    }
    public void deletePost(Post post) {
        post.getComments().remove(this);
    }

    //==비즈니스
    /**
     * 댓글 수정
     */
    public Comment updateComment(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
        this.commentDate = LocalDateTime.now();
        return this;
    }



}
