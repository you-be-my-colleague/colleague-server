package youBeMyColleague.study.domain;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {

    @Id @GeneratedValue
    @Column("comment_id")
    private Long id;

    private String Content;

    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn("post_id")
    private Post post;
}
