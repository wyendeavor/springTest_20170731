<%--
  Created by IntelliJ IDEA.
  User: yuanwang
  Date: 17/8/7
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>数据校验结果页面</title>
</head>
<body>
<form:form modelAttribute="user" action="/valid2" >
    <form:errors path="*" />
    <table>
        <tr>
            <td>用户名:</td>
            <td>
                <form:errors path="userName" cssClass="errorClass" /><br />
                <form:input path="userName" value="${userName}"/>
            </td>
        </tr>
        <tr>
            <td>密码:</td>
            <td>
                <form:errors path="password" cssClass="errorClass" /><br />
                <form:input path="password" value="${password}"/>
            </td>
        </tr>
        <tr>
            <td>真实姓名:</td>
            <td>
                <form:errors path="realName" cssClass="errorClass" /><br />
                <form:input path="realName" value="${realName}"/>
            </td>
        </tr>
        <tr>
            <td>出生日期:</td>
            <td>
                <form:errors path="birthday" cssClass="errorClass" /><br />
                <form:input path="birthday" value="${birthday}" />
            </td>
        </tr>
        <tr>
            <td>工资:</td>
            <td>
                <form:errors path="salary" cssClass="errorClass" /><br />
                <form:input path="salary" value="${salary}"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="提交" />
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
