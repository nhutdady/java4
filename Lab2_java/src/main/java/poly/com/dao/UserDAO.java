package poly.com.dao;

import java.util.List;
import poly.com.entity.User;

public interface UserDAO {
    List<User> findAll();        // Truy vấn tất cả
    User findById(String id);    // Truy vấn theo mã
    void create(User item);      // Thêm mới
    void update(User item);      // Cập nhật
    void deleteById(String id);  // Xóa theo mã
}
