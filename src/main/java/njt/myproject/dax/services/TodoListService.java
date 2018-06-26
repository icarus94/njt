package njt.myproject.dax.services;

import njt.myproject.dax.models.TodoList;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.repository.TodoListRepository;
import njt.myproject.dax.repository.UserHasTodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    UserHasTodoListRepository userHasTodoListRepository;

    @Transactional
    public TodoList getTaskListById(int id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Transactional
    public TodoList saveTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Transactional
    public TodoList updateTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Transactional
    public void deleteTodoList(int id) {
        todoListRepository.deleteById(id);
    }

    public List<UserHasTodoList> getTodoListByUserId(int user_id) {
        return userHasTodoListRepository.getByUserId(user_id);
    }

}
