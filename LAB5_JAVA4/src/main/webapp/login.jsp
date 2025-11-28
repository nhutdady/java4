<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<h2>Login</h2>

<form action="login" method="post">
    Username: <input name="username"><br>
    Password: <input type="password" name="password"><br>
    <button>Login</button>
</form>

<p style="color:red">${message}</p>

<hr>
<%@ include file="header.jsp" %>
