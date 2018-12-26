<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>顾客查看商品评价</title>
</head>
<body>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>用户id</td>
            <td>评价</td>
            <td>商品</td>
        </tr>
        <c:forEach items="${accesses}" var="access" varStatus="st">
            <tr>
                <td>${access.user_account}</td>
                <td>${access.content}</td>
                <td>${access.goods_name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
