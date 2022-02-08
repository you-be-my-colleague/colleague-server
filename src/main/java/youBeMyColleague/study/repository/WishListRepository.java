package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youBeMyColleague.study.domain.WishList;

public interface WishListRepository extends JpaRepository<WishList,Long> {
}
