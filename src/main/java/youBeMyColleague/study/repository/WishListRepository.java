package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import youBeMyColleague.study.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    @Query("select w from WishList w where w.member.id =: memberId and w.post.id =: postId")
}
