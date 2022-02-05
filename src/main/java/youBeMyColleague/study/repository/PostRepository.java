package youBeMyColleague.study.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.Post;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select p from Post p join fetch p.comments join fetch p.member where p.id = :postId")
    public List<Post> findPostWithAllComment(@Param("postId") Long postId);
}












