<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.poly.entity.Users" %>

<%
    // Lấy user từ session
    Users user = (Users) session.getAttribute("user");

    // Nếu chưa đăng nhập -> đá về login.jsp
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chính</title>
</head>
<body>

<h2>Xin chào: <%= user.getFullname() %></h2>

<a href="<%= request.getContextPath() %>/logout">Đăng xuất</a>

<hr>

<p>xin chào</p>

</body>
</html>
