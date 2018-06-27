package njt.myproject.dax.services;

import njt.myproject.dax.models.Task;
import njt.myproject.dax.models.User;
import njt.myproject.dax.repository.TaskRepository;
import njt.myproject.dax.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TodoListRepository todoListRepository;

    public Task saveTask(User user){

        return null;
    }
}
