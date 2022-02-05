package youBeMyColleague.study.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class wishList {
    @Id @GeneratedValue
    private Long id;

    @OneToOne
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
