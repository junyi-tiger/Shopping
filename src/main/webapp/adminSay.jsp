<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员向全站通告的内容</title>
</head>
<body>
    <form method="post" action="/adminSay">
        <input type="text" name="content" placeholder="XXXX-XX-XX:balabala">
        <br>
        <input type="submit" value="向全站发出通告">
    </form>
</body>
</html>
