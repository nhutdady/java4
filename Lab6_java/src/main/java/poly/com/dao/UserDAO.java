package poly.com.dao;


import java.util.List;

import poly.com.entity.User;

public interface UserDAO {
    User findById(Integer id);

    User findByUsername(String username);

    List<User> findAll();

    User create(User entity);

    User update(User entity);

    void delete(Integer id);
}
