package com.poly.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class AppListener implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext app = sce.getServletContext();
        app.setAttribute("visitors", 0); // khởi tạo số đếm
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext app = se.getSession().getServletContext();

        synchronized (app) { // tránh xung đột khi nhiều người truy cập
            Integer count = (Integer) app.getAttribute("visitors");
            if (count == null) count = 0;
            app.setAttribute("visitors", count + 1);
        }
    }
}
