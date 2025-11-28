package com.poly.daoimpl;

import com.poly.dao.UserDAO;
import com.poly.entity.Users;
import poly.com.utils.JpaUtils;

import jakarta.persistence.EntityManager;

public class UserDAOImpl implements UserDAO {

    @Override
    public Users findById(String username) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(Users.class, username);
        } finally {
            em.close();
        }
    }
}
