<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员后门登陆界面</title>
</head>
<body>
    <h2 style="font-size: larger;color: red">请务必慎重操作！！！</h2>
    <form action="/adminLogin" method="post">
        用户名:<input type="text" name="account"><br>
        密码:<input type="password" name="password"><br>
    <input type="submit" value="管理员登录"><br>
    </form>
</body>
</html>
