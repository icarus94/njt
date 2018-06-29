package njt.myproject.dax.services;

import javassist.NotFoundException;
import njt.myproject.dax.dto.form.EditTaskForm;
import njt.myproject.dax.models.Task;
import njt.myproject.dax.models.TodoList;
import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.repository.TaskRepository;
import njt.myproject.dax.repository.TodoListRepository;
import njt.myproject.dax.repository.UserHasTodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TodoListRepository todoListRepository;

    @Autowired
    UserHasTodoListRepository userHasTodoListRepository;

    public Task saveTask(Task task, User user, int parent_id) throws NotFoundException {
        TodoList todoList = todoListRepository.findById(parent_id).orElse(null);
        //TODO premission check
        if (todoList != null) {
            task.setDone((byte) 0);
            task.setTodoList(todoList);
            task = taskRepository.save(task);
        } else {
            throw new NotFoundException("Task list not found.");
        }
        return task;
    }

    public void deleteTask(int task_id, User user) throws NotFoundException {
        Task task = taskRepository.findById(task_id).orElse(null);
        if (task != null) {
            //TODO premission check
            System.out.println(task);
            taskRepository.delete(task);
        } else {
            throw new NotFoundException("Task not found.");
        }
    }

    public Task updateTask(EditTaskForm editTaskForm, User user) throws NotFoundException {
        Task task = taskRepository.findById(editTaskForm.getId()).orElse(null);
        if (task != null) {
            //TODO premission check
            task.setName(editTaskForm.getName());
            task.setPriority((byte) editTaskForm.getPriority());
            task.setDescription(editTaskForm.getDescription());
            task.setDueDate(editTaskForm.getDueDate());
            return taskRepository.save(task);
        } else {
            throw new NotFoundException("Task not found.");
        }
    }

    public Task checkOrUncheckTask(int task_id, User user, int checked) throws NotFoundException {
        Task task = taskRepository.findById(task_id).orElse(null);
        if (task != null) {
            //TODO premission check
            task.setDone((byte) checked);
            return taskRepository.save(task);
        } else {
            throw new NotFoundException("Task not found.");
        }
    }

    public List<Task> getUsersTask(User user) {
        List<UserHasTodoList> userHasTodoList = userHasTodoListRepository.getByUserId(user.getId());
        List<Task> tasks = null;
        if (userHasTodoList == null) {
            System.out.println("EMPTY !!!!!!!!!");
        } else {
//            Sort sort = new Sort(Sort.Direction.ASC, "due_date");
//            tasks = taskRepository.findByIds(getTaskIds(userHasTodoList), sort);
//            tasks = taskRepository.findByTodoListOrderByDueDateAsc(getTaskLists(userHasTodoList));
            tasks = taskRepository.findTasksByTodoListsOrederBy(getTaskLists(userHasTodoList));
            System.out.println("SIZE IS:" + tasks.size());
        }
        System.out.println("VALUE IS:" + tasks);
        return tasks;
    }


    private Set<Integer> getTaskLists(List<UserHasTodoList> userHasTodoLists) {
        Set<Integer> list = new HashSet<>();
        for (UserHasTodoList userHasTodoList : userHasTodoLists) {
            list.add(userHasTodoList.getTodoList().getId());
        }
        return list;
    }
}
