package njt.myproject.dax.dto.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditUserForm {

    @NotBlank(message = "Name is required")
    @Size(max = 30, message = "Name field has max length of 30 characters")
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(max = 30, message = "Surname field has max length of 30 characters")
    private String surname;

    @NotNull(message = "Password is required")
    @Size(max = 30, message = "Password field has max length of 30 characters")
    private String password;

    @NotBlank(message = "Email is required")
    @Size(max = 50, message = "Email field has max length of 50 characters")
    private String email;

    private int role;

    private int active;

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
