package poly.com.controller;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({
    "/video/list",
    "/video/detail/*",
    "/video/like/*",
    "/video/share/*"
})
public class VideoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String uri = req.getRequestURI();

        if (uri.contains("/video/list")) {
            req.setAttribute("message", "Trang danh sách video");
        } else if (uri.contains("/video/detail")) {
            req.setAttribute("message", "Trang chi tiết video");
        } else if (uri.contains("/video/like")) {
            req.setAttribute("message", "Thao tác like video");
        } else if (uri.contains("/video/share")) {
            req.setAttribute("message", "Thao tác share video");
        }

        req.getRequestDispatcher("/page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
