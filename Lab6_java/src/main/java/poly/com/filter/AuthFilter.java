package poly.com.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import poly.com.entity.User;

@WebFilter({
    "/admin/*",
    "/account/change-password",
    "/account/edit-profile",
    "/video/like/*",
    "/video/share/*"
})
public class AuthFilter implements Filter {

    // Dùng key này để lưu URL bảo vệ trong session
    public static final String SECURITY_URI = "securityUri";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Không cần cấu hình gì thêm
    }

    @Override
    public void destroy() {
        // Không cần giải phóng gì thêm
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req  = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user"); // sẽ set trong LoginServlet (bài 3)

        String uri = req.getRequestURI();

        // Xác định trang admin / trang cần login
        boolean isAdminPage = uri.contains("/admin/");
        boolean isProtectedPage = isAdminPage
                || uri.contains("/account/change-password")
                || uri.contains("/account/edit-profile")
                || uri.contains("/video/like")
                || uri.contains("/video/share");

        // Nếu URL không thuộc nhóm bảo vệ thì cho đi luôn
        if (!isProtectedPage) {
            chain.doFilter(request, response);
            return;
        }

        // 1. Chưa đăng nhập mà truy cập trang bảo vệ
        if (user == null) {
            // Lưu lại URL đang định vào → để đăng nhập xong quay lại
            session.setAttribute(SECURITY_URI, uri);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 2. Đã đăng nhập nhưng không phải admin mà vào /admin/*
        if (isAdminPage && !Boolean.TRUE.equals(user.getIsAdmin())) {
            // Có thể lưu lại URI hoặc chỉ đơn giản đẩy về login / báo lỗi
            session.setAttribute(SECURITY_URI, uri);
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 3. Đã đăng nhập hợp lệ → cho đi tiếp
        chain.doFilter(request, response);
    }
}
