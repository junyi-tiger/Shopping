<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>查看自己的购物车</title>
    <script type="text/javascript">
        function show() {
            var num=parseInt(document.getElementById("num").value);
            var price=parseFloat(document.getElementById("price").value);
            document.getElementById("sum").value=price*num;
            return;
        }
    </script>
</head>
<body>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>用户名</td>
            <td>商家名</td>
            <td>商品名称</td>
            <td>商品数量</td>
            <td>价格总计</td>
            <td>确认购买</td>
            <td>移除购物车</td>
        </tr>
        <c:forEach items="${orders}" var="order" varStatus="st">
            <tr>
                <td>${order.user_account}</td>
                <td>${order.shop_account}</td>
                <td>${order.goods_name}</td>
                <td>
                    <!--限制只能输入正整数-->
                    <input type="text" value="1" name="goods_num" id="num"
                           oninput="this.value = this.value.replace(/[^0-9]/g, '');">
                </td>
                <td>
                    <input type="hidden" id="price" value="${order.goods_price}" >
                    <input type="text" name="sum" readonly="true" id="sum">
                    <input type="button" value="计算价格" onclick="javascript:show()">
                </td>
                <td>
                    <form action="/userAddOrder" method="post">
                        <input type="hidden" name="goods_name" value="${order.goods_name}">
                        <input type="submit" value="确认购买生成订单">
                    </form>
                </td>
                <td><a href="/userDeleteCart?goods_name=${order.goods_name}">移出购物车</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
