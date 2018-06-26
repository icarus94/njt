package njt.myproject.dax.repository;

import njt.myproject.dax.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    
}
