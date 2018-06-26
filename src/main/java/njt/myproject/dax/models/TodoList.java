package njt.myproject.dax.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the todo_list database table.
 */
@Entity
@Table(name = "todo_list")
@NamedQuery(name = "TodoList.findAll", query = "SELECT t FROM TodoList t")
public class TodoList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    //bi-directional many-to-one association to Task
    @OneToMany(mappedBy = "todoList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> tasks;

    //bi-directional many-to-one association to UserHasTodoList
    @OneToMany(mappedBy = "todoList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserHasTodoList> userHasTodoLists;

    public TodoList() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task addTask(Task task) {
        getTasks().add(task);
        task.setTodoList(this);

        return task;
    }

    public Task removeTask(Task task) {
        getTasks().remove(task);
        task.setTodoList(null);

        return task;
    }

    public List<UserHasTodoList> getUserHasTodoLists() {
        return this.userHasTodoLists;
    }

    public void setUserHasTodoLists(List<UserHasTodoList> userHasTodoLists) {
        this.userHasTodoLists = userHasTodoLists;
    }

    public UserHasTodoList addUserHasTodoList(UserHasTodoList userHasTodoList) {
        getUserHasTodoLists().add(userHasTodoList);
        userHasTodoList.setTodoList(this);

        return userHasTodoList;
    }

    public UserHasTodoList removeUserHasTodoList(UserHasTodoList userHasTodoList) {
        getUserHasTodoLists().remove(userHasTodoList);
        userHasTodoList.setTodoList(null);

        return userHasTodoList;
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tasks=" + tasks +
                ", userHasTodoLists=" + userHasTodoLists +
                '}';
    }
}