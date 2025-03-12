package projectJM.jotItDown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJM.jotItDown.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
