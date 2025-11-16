package poly.com.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Fullname", nullable = false)
    private String fullname;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Admin")
    private boolean admin;

    // ----- Constructors -----
    public User() {
    }

    public User(String id, String password, String fullname, String email, boolean admin) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
    }

    // ----- Getters -----
    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    // ----- Setters -----
    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    // ----- toString() -----
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", admin=" + admin +
                '}';
    }
}
