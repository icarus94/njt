package njt.myproject.dax.repository;

import njt.myproject.dax.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
