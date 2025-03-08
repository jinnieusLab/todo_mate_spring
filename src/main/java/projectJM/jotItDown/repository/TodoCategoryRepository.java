package projectJM.jotItDown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJM.jotItDown.domain.TodoCategory;

public interface TodoCategoryRepository extends JpaRepository<TodoCategory, Long> {
}
