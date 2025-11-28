package com.poly.filter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import com.poly.entity.Users;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AppFilter implements Filter {

    private String logFilePath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Lấy đường dẫn file logs.txt trong thư mục WEB-INF
        logFilePath = filterConfig.getServletContext().getRealPath("/WEB-INF/logs.txt");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        // 1) Thiết lập UTF-8
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        // 2) Ghi log nếu user đã đăng nhập
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        Users user = null;
        if (session != null) {
            user = (Users) session.getAttribute("user");
        }

        if (user != null) {
            String line = String.format(
                "%s | %s | %s\n",
                LocalDateTime.now(),
                request.getRequestURI(),
                user.getUsername()
            );

            FileWriter fw = new FileWriter(logFilePath, true);
            fw.write(line);
            fw.close();
        }

        // Cho request đi tiếp
        chain.doFilter(req, res);
    }
}
