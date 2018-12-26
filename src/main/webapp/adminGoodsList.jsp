<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>显示所有的商品</title>
</head>
<body>
    <form action="/adminGoodsList" method="post">
        <input type="submit" value="为管理员显示所有商品！">
    </form>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>名称</td>
            <td>图片</td>
            <td>价格</td>
            <td>商家</td>
        </tr>
        <c:forEach items="${goodss}" var="goodss" varStatus="st">
            <tr>
                <td>${goodss.name}</td>
                <td><img src="showImage?name=${goodss.name}" width="100" height="100" alt="图片加载失败"/></td>
                <td>${goodss.price}</td>
                <td>${goodss.owner}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
