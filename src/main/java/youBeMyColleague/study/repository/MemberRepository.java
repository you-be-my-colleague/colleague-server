package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youBeMyColleague.study.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {

}
