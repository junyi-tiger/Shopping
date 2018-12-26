<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员启动或者关闭帖子模块</title>
</head>
<body>
    <form action="/adminPostClose" method="post">
        <input type="submit" value="关闭帖子功能">
    </form>
    <br>
    <form action="/adminPostOpen" method="post">
        <input type="submit" value="开启帖子功能">
    </form>
</body>
</html>
