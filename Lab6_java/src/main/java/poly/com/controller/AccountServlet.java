package poly.com.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/account/sign-up",
    "/account/change-password",
    "/account/edit-profile"
})
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();
        if (uri.contains("sign-up")) {
            req.setAttribute("message", "Trang đăng ký");
        } else if (uri.contains("change-password")) {
            req.setAttribute("message", "Trang đổi mật khẩu");
        } else if (uri.contains("edit-profile")) {
            req.setAttribute("message", "Trang cập nhật hồ sơ");
        }

        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
