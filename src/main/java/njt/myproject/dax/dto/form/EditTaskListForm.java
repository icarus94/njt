package njt.myproject.dax.dto.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditTaskListForm {
    private int id;

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name field has max length of 100 characters")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
