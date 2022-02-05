package youBeMyColleague.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import youBeMyColleague.study.domain.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    private String title;
    private String content;
    private String gitAddress;
    private TechStack stack;
    private RecruitmentStatus postStatus;
    private int views ;
    private int likes ;
    private int commentCount;
    private LocalDateTime postDate;
    private String creater;
    private List<Comment> comments;
    private Member member;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.gitAddress = post.getGitAddress();
        this.stack = post.getStack();
        this.postStatus = post.getPostStatus();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.postDate = post.getPostDate();
        this.creater = post.getMember().getName();
        this.comments = post.getComments();
        this.commentCount = post.getCommentCount();
        this.member = post.getMember();
    }
}
