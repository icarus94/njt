package njt.myproject.dax.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;

    @Column(nullable = false)
    private byte active;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 300)
    private String password;

    @Column(nullable = false, length = 30)
    private String surname;


    //bi-directional many-to-one association to UserHasTodoList
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserHasTodoList> userHasTodoLists;

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getActive() {
        return this.active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public List<UserHasTodoList> getUserHasTodoLists() {
        return this.userHasTodoLists;
    }

    public void setUserHasTodoLists(List<UserHasTodoList> userHasTodoLists) {
        this.userHasTodoLists = userHasTodoLists;
    }

    public UserHasTodoList addUserHasTodoList(UserHasTodoList userHasTodoList) {
        getUserHasTodoLists().add(userHasTodoList);
        userHasTodoList.setUser(this);

        return userHasTodoList;
    }

    public UserHasTodoList removeUserHasTodoList(UserHasTodoList userHasTodoList) {
        getUserHasTodoLists().remove(userHasTodoList);
        userHasTodoList.setUser(null);

        return userHasTodoList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
