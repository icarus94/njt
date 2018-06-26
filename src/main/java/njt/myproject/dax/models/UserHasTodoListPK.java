package njt.myproject.dax.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserHasTodoListPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="user_id", insertable=false, updatable=false)
    private int userId;

    @Column(name="todo_list_id", insertable=false, updatable=false)
    private int todoListId;

    public UserHasTodoListPK() {
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
        if (!(other instanceof UserHasTodoListPK)) {
            return false;
        }
        UserHasTodoListPK castOther = (UserHasTodoListPK)other;
        return
                (this.userId == castOther.userId)
                        && (this.todoListId == castOther.todoListId);
    }

    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.userId;
        hash = hash * prime + this.todoListId;

        return hash;
    }
}
