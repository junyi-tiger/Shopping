<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商家查看并删除商品界面</title>
</head>
<body>
    <table align='center' border='1' cellspacing='0'>
        <tr>
            <td>名称</td>
            <td>图片</td>
            <td>价格</td>
            <td>商家</td>
            <td>删除商品</td>
        </tr>
        <c:forEach items="${products}" var="product" varStatus="st">
            <tr>
                <td>${product.name}</td>
                <td><img src="showImage?name=${product.name}" height="100" width="100" alt="图片加载失败"/></td>
                <td>${product.price}</td>
                <td>${product.owner}</td>
                <td>
                    <form action="/shopDeleteGoods" method="post">
                        <input type="hidden" name="name" value="${product.name}">
                        <input type="submit" value="删除这类商品">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
