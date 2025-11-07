package poly.com.user;
import jakarta.persistence.*; // Hoặc javax.persistence.* tùy phiên bản

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "Password")
    private String password;

    @Column(name = "Fullname")
    private String fullname;

    @Column(name = "Email")
    private String email;

    @Column(name = "Admin")
    private Boolean admin;

    // 1. Constructor mặc định (BẮT BUỘC cho JPA)
    public User() {}

    // 2. Constructor đầy đủ (Tùy chọn, giúp tạo đối tượng nhanh hơn)
    public User(String id, String password, String fullname, String email, Boolean admin) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.email = email;
        this.admin = admin;
    }

    // 3. Getters và Setters (BẮT BUỘC cho Java Bean)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    // (Tùy chọn) toString() giúp in đối tượng dễ dàng hơn khi test
    @Override
    public String toString() {
        return "User [Id=" + id + ", Fullname=" + fullname + ", Email=" + email + ", Admin=" + admin + "]";
    }
}