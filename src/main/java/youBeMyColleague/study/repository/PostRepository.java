package youBeMyColleague.study.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import youBeMyColleague.study.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {
        em.persist(post);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class)
                .getResultList();
    }

    public List<Post> findAllPostOfMember(Long memberId) {
        return em.createQuery("select p from Post p" +
                " join fetch Member m",Post.class)
    }


}
