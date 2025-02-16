package projectJM.jotItDown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJM.jotItDown.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
