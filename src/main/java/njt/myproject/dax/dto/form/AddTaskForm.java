package njt.myproject.dax.dto.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class AddTaskForm {

    private int parent_id_n;

    @NotBlank(message = "Name field is required")
    @Size(max = 100,  message = "Name field has max length of 100 characters")
    private String name_n;

    @NotBlank(message = "Description field is required")
    private String description_n;

    @NotNull(message = "Please fill due date field correctly.")
    private Date dueDate_n;

    private int priority_n;


    public int getParent_id_n() {
        return parent_id_n;
    }

    public void setParent_id_n(int parent_id_n) {
        this.parent_id_n = parent_id_n;
    }

    public String getName_n() {
        return name_n;
    }

    public void setName_n(String name_n) {
        this.name_n = name_n;
    }

    public String getDescription_n() {
        return description_n;
    }

    public void setDescription_n(String description_n) {
        this.description_n = description_n;
    }

    public Date getDueDate_n() {
        return dueDate_n;
    }

    public void setDueDate_n(Date dueDate_n) {
        this.dueDate_n = dueDate_n;
    }

    public int getPriority_n() {
        return priority_n;
    }

    public void setPriority_n(int priority_n) {
        this.priority_n = priority_n;
    }
}
