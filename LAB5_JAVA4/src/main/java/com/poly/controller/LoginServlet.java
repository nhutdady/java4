package com.poly.controller;

import java.io.IOException;

import com.poly.dao.UserDAO;
import com.poly.daoimpl.UserDAOImpl;
import com.poly.entity.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO dao = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        Users user = dao.findById(username);

        if (user == null) {
            req.setAttribute("message", "Sai username");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        if (!user.getPassword().equals(password)) {
            req.setAttribute("message", "Sai password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }

        // Đăng nhập đúng
        session.setAttribute("user", user);    // lưu user vào session

        // Chuyển sang trang chính (Home)
        resp.sendRedirect(req.getContextPath() + "/home.jsp");
    }

}
