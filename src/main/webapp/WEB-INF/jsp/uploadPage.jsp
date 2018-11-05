<%--
  Created by IntelliJ IDEA.
  User: yuanwang
  Date: 17/8/8
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>请上传文件</title>
</head>
<body>
    <h1>请选择要上传的文件</h1>

<form method="post" action="<c:url value="/user/upload" />"
      enctype="multipart/form-data">
    姓名: <input type="text" name="name" /> <br />
    选择文件: <input type="file" name="file" /> <br />
    <input type="submit" />
</form>


</body>
</html>
