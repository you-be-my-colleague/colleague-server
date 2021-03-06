package youBeMyColleague.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import youBeMyColleague.study.domain.Member;
import youBeMyColleague.study.dto.MemberResponseDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    @Query("select distinct m from Member m left join fetch m.posts where m.id=:memberId")
    Optional<List<Member>> findMemberPost(@Param("memberId") Long memberId);

    @Query("select m from Member m where m.id = :memberId")
    Optional<MemberResponseDto> findOneMember(@Param("memberId") Long memberId);

    @Query("select m from Member m where m.name = :memberName")
    Member findByName(@Param("memberName") String username);
}
