<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>查看用户订单</title>
</head>
<body>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>商品</td>
            <td>商家</td>
            <td>数量</td>
            <td>总价</td>
            <!--<td>状态(0:未付款,1:已付款未发货,2:已发货到货)</td>-->
            <td>评价</td>
        </tr>
        <c:forEach items="${listorders}" var="listorder" varStatus="st">
            <tr>
                <td>${listorder.goods_name}</td>
                <td>${listorder.shop_account}</td>
                <td>${listorder.goods_num}</td>
                <td>${listorder.sum}</td>
                <!--<td></td>-->
                <!--<td>
                    <form action="/userPay" method="post">
                        <input type="hidden" value="" name="goods_name">
                        <input type="submit" value="付款">
                    </form>
                </td>-->
                <td>
                    <form action="/userWriteAccess" method="post">
                        <textarea placeholder="在这里填写评价" name="content"></textarea>
                        <input type="hidden" name="user_account" value="${listorder.user_account}">
                        <input type="hidden" name="goods_name" value="${listorder.goods_name}">
                        <input type="submit" value="进行评价">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
