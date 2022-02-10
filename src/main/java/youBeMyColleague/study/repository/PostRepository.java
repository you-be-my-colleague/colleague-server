package youBeMyColleague.study.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.Post;

import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select distinct p from Post p left join fetch p.comments join fetch p.member where p.id =:postId")
    Optional<List<Post>> findPostWithAllComment(@Param("postId") Long postId);

    @Query("select distinct p from Post p left join fetch p.comments join fetch p.member")
    List<Post> findAll();

}












