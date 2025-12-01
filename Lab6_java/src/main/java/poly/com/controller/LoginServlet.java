package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.dao.UserDAO;
import poly.com.daoimpl.UserDaoImpl;
import poly.com.entity.User;
import poly.com.filter.AuthFilter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Lấy username & password từ form
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Tìm user trong DB
        User user = userDao.findByUsername(username);

        if (user == null) {
            // Sai username
            req.setAttribute("message", "Invalid username");
        } else if (!user.getPassword().equals(password)) {
            // Sai password
            req.setAttribute("message", "Invalid password");
        } else if (!Boolean.TRUE.equals(user.getActivated())) {
            // Tài khoản chưa kích hoạt (nếu bạn muốn dùng)
            req.setAttribute("message", "Account is not activated");
        } else {
            // Đăng nhập thành công
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            req.setAttribute("message", "Login successfully");

            // Lấy URL bảo vệ mà AuthFilter đã lưu trước đó
            String securityUri = (String) session.getAttribute(AuthFilter.SECURITY_URI);
            if (securityUri != null) {
                // Xóa luôn để tránh redirect lại vòng lặp
                session.removeAttribute(AuthFilter.SECURITY_URI);
                resp.sendRedirect(req.getContextPath() + securityUri.replace(req.getContextPath(), ""));
                return;
            }
        }

        // Nếu chưa redirect đi đâu, quay lại trang login.jsp
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }
}
