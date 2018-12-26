<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员删除商品界面</title>
</head>
<body>
    <form action="/adminDeleteGoods" method="post">
        <input type="text" name="name" placeholder="商品名称"><br>
        <input type="submit" value="删除输入的商品">
    </form>
</body>
</html>
