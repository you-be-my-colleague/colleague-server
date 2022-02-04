package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youBeMyColleague.study.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    List<Member> findByName (String name);
    Member findByEmail(String email);
}
