package youBeMyColleague.study.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class WishList {
    @Id @GeneratedValue
    @Column(name = "wishList_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public WishList(Post post, Member member){
        this.post = post;
        this.member = member;

        member.getWishLists().add(this);
    }
}
