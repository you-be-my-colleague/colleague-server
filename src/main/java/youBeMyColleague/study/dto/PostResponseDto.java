package youBeMyColleague.study.dto;

import lombok.Data;
import youBeMyColleague.study.domain.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PostResponseDto {
    private Long post_id;
    private String creater_name;
    private Long creater_id;
    private String title;
    private String content;
    private String gitAddress;
    private TechStack stack;
    private RecruitmentStatus postStatus;
    private int views ;
    private int likes ;
    private int commentCount;
    private LocalDateTime postDate;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.post_id = post.getId();
        this.creater_name = post.getMember().getName();
        this.creater_id = post.getMember().getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.gitAddress = post.getGitAddress();
        this.stack = post.getStack();
        this.postStatus = post.getPostStatus();
        this.views = post.getViews();
        this.likes = post.getLikes();
        this.postDate = post.getPostDate();
        this.comments = post.getComments().stream()
                .map(c -> new CommentResponseDto(c.getMember().getId(),c.getMember().getName(),c.getId(),c.getContent(),c.getCommentDate()))
                .collect(Collectors.toList());
        this.commentCount = post.getCommentCount();
    }
}
