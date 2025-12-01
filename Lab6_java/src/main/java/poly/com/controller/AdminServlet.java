package poly.com.controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/admin/video",
    "/admin/user",
    "/admin/like",
    "/admin/share"
})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("/admin/video")) {
            req.setAttribute("message", "Quản trị Video");
        } else if (uri.contains("/admin/user")) {
            req.setAttribute("message", "Quản trị User");
        } else if (uri.contains("/admin/like")) {
            req.setAttribute("message", "Thống kê Like");
        } else if (uri.contains("/admin/share")) {
            req.setAttribute("message", "Thống kê Share");
        }

        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
