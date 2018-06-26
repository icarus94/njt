package njt.myproject.dax.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the user_has_todo_list database table.
 */
@Entity
@Table(name = "user_has_todo_list")
@NamedQuery(name = "UserHasTodoList.findAll", query = "SELECT u FROM UserHasTodoList u")
public class UserHasTodoList implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserHasTodoListPK id;

    @Column(nullable = false)
    private byte permissions;

    //bi-directional many-to-one association to TodoList
    @ManyToOne
    @JoinColumn(name = "todo_list_id", nullable = false, updatable = false, insertable = false)
    private TodoList todoList;


    //bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,updatable = false, insertable = false)
    private User user;

    public UserHasTodoList() {
    }

    public UserHasTodoListPK getId() {
        return this.id;
    }

    public void setId(UserHasTodoListPK id) {
        this.id = id;
    }

    public byte getPermissions() {
        return this.permissions;
    }

    public void setPermissions(byte permissions) {
        this.permissions = permissions;
    }

    public TodoList getTodoList() {
        return this.todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}