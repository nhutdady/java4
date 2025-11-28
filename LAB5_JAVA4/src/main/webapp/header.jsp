<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<div style="border-bottom:1px solid #ccc; padding:5px 0; margin-bottom:10px;">
    <c:if test="${not empty sessionScope.user}">
        <h3>Xin chào: ${sessionScope.user.fullname}</h3>
    </c:if>

    <p>Visitors: ${applicationScope.visitors}</p>
    
</div>
