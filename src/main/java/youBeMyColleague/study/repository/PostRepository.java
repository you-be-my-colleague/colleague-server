package youBeMyColleague.study.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.Post;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("select distinct p from Post p")
    public Optional<List<Post>> findPostWithAllComment(Long postId);

}












