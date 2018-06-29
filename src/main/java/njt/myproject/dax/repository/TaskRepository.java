package njt.myproject.dax.repository;

import njt.myproject.dax.models.Task;
import njt.myproject.dax.models.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTodoListOrderByDueDateAsc(List<TodoList> todoLists);

    @Query("from Task where todo_list_id IN :todo_list_ids ORDER BY due_date ASC")
    List<Task> findTasksByTodoListsOrederBy(@Param("todo_list_ids") Set<Integer> ids);
}
