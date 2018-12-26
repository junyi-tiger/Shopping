<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员后台主界面</title>
</head>
<body>
    <p style="font-size: large;color: red">XXX购物平台抵制一切非正能量！！！</p>
    <p style="color: red;font-size: larger">选择root管理员要进行的操作</p>
    <hr size=10px/>
    <p>查看所有商品及所属商家</p>
    <jsp:include page="adminGoodsList.jsp"></jsp:include>
    <a href="adminDeleteGoods.jsp" style="color: red">删除某件商品</a>
    <br>
    <a href="adminDeleteShop.jsp" style="color: red">级联删除某个商家</a>
    <br>
    <a href="adminSay.jsp">向全站发通告</a>
    <br>
    <a href="adminChangePost.jsp">对发帖模块进行操作</a>
</body>
</html>
