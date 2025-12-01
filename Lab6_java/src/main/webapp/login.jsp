<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>

<h2>Đăng nhập</h2>

<!-- Hiển thị thông báo -->
<c:if test="${not empty message}">
    <p style="color:red">${message}</p>
</c:if>

<c:url var="url" value="/login"/>

<form action="${url}" method="post">
    Username: <input name="username"><br><br>
    Password: <input name="password" type="password"><br><br>
    <button>Đăng nhập</button>
</form>

</body>
</html>
