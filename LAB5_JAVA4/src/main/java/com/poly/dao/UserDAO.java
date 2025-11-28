package com.poly.dao;

import com.poly.entity.Users;

public interface UserDAO {
    Users findById(String username);
}
