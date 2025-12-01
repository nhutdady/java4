<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chung</title>
</head>
<body>

<h1>Xin chào!</h1>
<hr/>

<h3>Nội dung từ servlet:</h3>
<p>${message}</p>

<hr/>
<p>Đường dẫn hiện tại: ${pageContext.request.requestURI}</p>

</body>
</html>
