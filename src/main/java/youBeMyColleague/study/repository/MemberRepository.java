package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName (String name);
    Member findByEmail(String email);
    void deleteByEmail(String email);

    @Query("select m from Member m left join fetch m.posts where m.id=:id")
    List<Member> findMemberPost(@Param("id") Long id);
}
