<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/16
  Time: 11:53:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <table align="center">
        <h2>登录页面</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            用户名：<input type="text" name="username"><br>
            密码：<input type="text" name="password"><br>
            验证码：<input id="coldValue" name="imageCode" type="text"  placeholder="验证码" style="width: 60px;">
            <img id="validateCode" class="authCodeImg" src="${pageContext.request.contextPath}/getCode?time=<%=new Date().getTime() %>"
                 title="看不清可单击图片刷新" onclick="this.src='${pageContext.request.contextPath}/getCode?time='+new Date().getTime();"><br>
            记住我:<input type="checkbox" name="remember-me" value="true"><br/>
            <input type="submit" value="登录"><br>
        </form>
    </table>
</body>
</html>
