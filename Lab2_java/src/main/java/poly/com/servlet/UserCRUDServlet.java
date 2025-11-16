package poly.com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import poly.com.dao.UserDAO;
import poly.com.dao.UserDAOImpl;
import poly.com.entity.User;

@WebServlet({
    "/user/crud/index",
    "/user/crud/edit/*",
    "/user/crud/create",
    "/user/crud/update",
    "/user/crud/delete",
    "/user/crud/reset"
})
public class UserCRUDServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        UserDAO dao = new UserDAOImpl(); 
        User form = new User();
        String message = "Enter user information";

        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String path = req.getServletPath();
        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            form = dao.findById(id);
            message = "Edit: " + id;
        } else if (path.contains("create")) {
            dao.create(form);
            message = "Created: " + form.getId();
            form = new User();
        } else if (path.contains("update")) {
            dao.update(form);
            message = "Updated: " + form.getId();
        } else if (path.contains("delete")) {
            dao.deleteById(form.getId());
            message = "Deleted: " + form.getId();
            form = new User();
        } else if (path.contains("reset")) {
            form = new User();
        }

        List<User> list = dao.findAll();
        req.setAttribute("message", message);
        req.setAttribute("user", form);
        req.setAttribute("users", list);
        req.getRequestDispatcher("/pages/user-crud.jsp").forward(req, resp);
    }
}
