<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>顾客账号注册</title>
</head>
<body>
    <form action="/userRegister" method="post">
        账号：<input type="text" name="account"><br>
        密码：<input type="password" name="password"><br>
        <input type="submit" value="注册"><br>
        <p>刚开始注册默认为非会员</p>
    </form>
</body>
</html>
