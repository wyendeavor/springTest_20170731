<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Spring MVC Excel View</title>
</head>
<body>

<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Date</th>
    </tr>
    <c:forEach var="c" items="${courses}">
        <tr>
            <td>${c.id}</td>
            <td>${c.name}</td>
            <td><fmt:formatDate value="${c.date}" pattern="MM/dd/yy"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>