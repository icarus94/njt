package njt.myproject.dax.services;

import javassist.NotFoundException;
import njt.myproject.dax.models.TodoList;
import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.models.UserHasTodoListPK;
import njt.myproject.dax.repository.TodoListRepository;
import njt.myproject.dax.repository.UserHasTodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListService {

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    UserHasTodoListRepository userHasTodoListRepository;


    @Transactional
    public TodoList saveTodoList(String name, User user) {
        TodoList todoList = new TodoList();
        todoList.setName(name);
        todoList = todoListRepository.save(todoList);

        UserHasTodoList userHasTodoList = new UserHasTodoList();
        UserHasTodoListPK pk = new UserHasTodoListPK();
        pk.setTodoListId(todoList.getId());
        pk.setUserId(user.getId());
        userHasTodoList.setId(pk);
        userHasTodoList.setPermissions((byte) 1);

        todoList.setUserHasTodoLists(new ArrayList<>());
        todoList.addUserHasTodoList(userHasTodoList);
        return todoListRepository.save(todoList);
    }

    @Transactional
    public TodoList updateTodoList(int id, String name, User user) throws NotFoundException {
        TodoList todoList = todoListRepository.findById(id).orElse(null);
        if (todoList != null) {
            //TODO premission check
            todoList.setName(name);
            return todoListRepository.save(todoList);
        } else {
            throw new NotFoundException("Todo list not found.");
        }
    }

    @Transactional
    public void deleteTodoList(int id, int user_id) throws NotFoundException {
        TodoList todoList = todoListRepository.findById(id).orElse(null);
        if (todoList != null) {
            //TODO premission check
            todoListRepository.delete(todoList);
        } else {
            throw new NotFoundException("Todo list not found.");
        }
    }

    public List<UserHasTodoList> getTodoListByUserId(int user_id) {
        return userHasTodoListRepository.getByUserId(user_id);
    }

}
