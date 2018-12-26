<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>顾客登录</title>
</head>
<body>
    <form action="/userLogin" method="post">
        用户名: <input type="text" name="account"><br>
        密码:   <input type="password" name="password"><br>
        <input type="submit" value="登录">
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" value="注册" onclick="location.href='/userRegister.jsp'">
    </form>
    <a href="shopLogin.jsp">如果是商家则从这里登录</a>
</body>
</html>
