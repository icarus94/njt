package njt.myproject.dax.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TaskPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

    private int id;

    @Column(name="todo_list_id", insertable=false, updatable=false)
    private int todoListId;

    public TaskPK() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTodoListId() {
        return this.todoListId;
    }
    public void setTodoListId(int todoListId) {
        this.todoListId = todoListId;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TaskPK)) {
            return false;
        }
        TaskPK castOther = (TaskPK)other;
        return
                (this.id == castOther.id)
                        && (this.todoListId == castOther.todoListId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.id;
        hash = hash * prime + this.todoListId;

        return hash;
    }
}
