<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sc" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>这是主页</title>
</head>
<body>
这是主页<br>
<sc:authorize access="hasAuthority('/user/add')">
    <a href="${pageContext.request.contextPath}/user/add">新增用户</a><br>
</sc:authorize>
<sc:authorize access="hasAuthority('/user/delete')">
    <a href="${pageContext.request.contextPath}/user/delete">删除用户</a><br>
</sc:authorize>
<sc:authorize access="hasAuthority('/user/update')">
    <a href="${pageContext.request.contextPath}/user/update">修改用户</a><br>
</sc:authorize>
<sc:authorize access="hasAuthority('/user/find')">
    <a href="${pageContext.request.contextPath}/user/find">查询用户</a><br>
</sc:authorize>



</body>
</html>
