<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员删除商家及其下所有商品界面</title>
</head>
<body>
    <form action="/adminDeleteShop" method="post">
        <input type="text" name="owner" placeholder="商家名称"><br>
        <input type="submit" value="删除商家和所有的旗下商品">
    </form>
</body>
</html>
