package youBeMyColleague.study.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class WishList {
    @Id @GeneratedValue
    @Column(name = "wishList_id")
    private Long id;

    @OneToOne(mappedBy = "wishList",fetch = FetchType.LAZY)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
