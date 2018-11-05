<%--
  Created by IntelliJ IDEA.
  User: yuanwang
  Date: 17/8/8
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="website.title" /></title>
</head>
<body>
<fmt:message key="user.testUserList.title" />
<table>
    <c:forEach items="${testUserList}" var="user">
        <tr>
            <td>
                <a href="<c:url value="/user/showUserList" /> ">
                    ${user.userName}
                </a>
            </td>
            <td>
                ${user.realName}
            </td>
            <td>
                <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
