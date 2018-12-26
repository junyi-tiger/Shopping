<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家登录</title>
</head>
<body>
    <form action="/shopLogin" method="post">
    用户名: <input type="text" name="account"><br>
    密码:   <input type="password" name="password"><br>
    <input type="submit" value="登录"><br>
    </form>
    <a href="shopRegister.jsp">如果没有商家账号请点击这里</a>
</body>
</html>
