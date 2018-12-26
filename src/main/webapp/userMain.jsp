<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>顾客主界面</title>
</head>
<body>
    <c:if test="${!empty user}">
        <div align="center">
            欢迎当前顾客: ${user.account}
        </div>
    </c:if>
    <form action="/userMember" method="post">
        <input type="hidden" name="account" value=${user.account}>
        <input type="submit" value="点击成为本站会员">
    </form>
    <hr style="color: red">
    <p>如果你是本站会员，可以发表帖子</p>
    <form action="/userAddPost" method="post">
        帖子内容：<textarea style="width: 250px;height: 75px;"name="content"></textarea><br>
        用户ID：<input type="text" name="user_account" readonly="ture" value=${user.account}><br>
        <input type="submit" value="发布帖子">
    </form>
    <hr style="color: red">
    <form action="/userListPost" method="post">
        <input type="submit" value="查看所有的帖子">
    </form>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>发帖人名称</td>
            <td>帖子内容</td>
        </tr>
        <c:forEach items="${products}" var="product" varStatus="st">
            <tr>
                <td>${product.user_account}</td>
                <td>${product.content}</td>
            </tr>
        </c:forEach>
    </table>
    <hr style="color: red">
    <p>这里是sql模糊搜索</p>
    <form method="post" action="/userSearchGoods">
        <input type="text" name="name" placeholder="物品名称"><br>
        <input type="submit" value="搜索">
    </form>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>名称</td>
            <td>图片</td>
            <td>价格</td>
            <td>商家</td>
            <td>加入购物车</td>
            <td>查看评价</td>
        </tr>
        <c:forEach items="${goodss}" var="goods" varStatus="st">
            <tr>
                <td>${goods.name}</td>
                <td><img src="showImage?name=${goods.photo}" width="100" height="100" alt="图片加载失败"/></td>
                <td>${goods.price}</td>
                <td>${goods.owner}</td>
                <td>
                    <form action="/userAddCart" method="post">
                        数量<input type="text" value="1" name="num">
                        <input type="hidden" name="name" value="${goods.name}">
                        <input type="hidden" name="user_account" value="${user.account}">
                        <input type="hidden" name="shop_account" value="${goods.owner}">
                        <input type="hidden" name="price" value="${goods.price}">
                        <input type="submit" value="加入购物车">
                    </form>
                </td>
                <td>
                    <form action="/userSeeAccess" method="post">
                        <input type="hidden" name="name" value="${goods.name}">
                        <input type="submit" value="查看评价">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <hr style="color: red">
    <form>
        <input type="button" value="查看自己的购物车" onclick="location.href='/userCart.jsp'">
    </form>
    <hr style="color: red">
    <form action="/userSeeOrder" method="post">
        <input type="hidden" value="${user.account}" name="user_account">
        <input type="submit" value="查看自己所有的订单">
    </form>
    <hr style="color:red;">
</body>
</html>
