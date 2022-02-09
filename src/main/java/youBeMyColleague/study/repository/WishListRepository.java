package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList,Long> {
    @Modifying
    @Query("delete from WishList w  where w.member.id = :member_id and w.post.id = :post_id")
    void deleteWishPost(@Param("member_id") Long member_id,@Param("post_id") Long post_id);

    @Query("select w from WishList w  where w.member.id = :member_id and w.post.id = :post_id")
    Optional<List<WishList>> finyPost(@Param("member_id") Long member_id,@Param("post_id") Long post_id);

    @Query("select w from WishList w where w.member.id = :member_id and w.post.id = :post_id")
    Optional<WishList> findWishListByMemberWithPost(@Param("member_id") Long member_id,@Param("post_id") Long post_id);
}
