package youBeMyColleague.study.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import youBeMyColleague.study.domain.Comment;
import youBeMyColleague.study.domain.Post;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {



}












