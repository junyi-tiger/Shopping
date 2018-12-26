<%@ page import="com.shopping.bean.Shop" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>商家主界面</title>
</head>
<body>
    <c:if test="${!empty shop}">
        <div align="center">
            欢迎当前商家: ${shop.account}
        </div>
    </c:if>
    <h2 style="color: red">增加商品</h2>
    <form action="/shopMain" method="post" enctype="multipart/form-data">
        商品名称:<input type="text" name="name"><br>
        商品图片:<input type="file" name="photo" multiple="multiple"><br>
        商品价格：<input type="text" name="price"><br>
        商品所属商家：<input type="text" name="owner" readonly="true" value=${shop.account}><br>
        <input type="submit" value="提交修改">
    </form>
    <%
        //Shop temp=(Shop)request.getAttribute("shop");
        //String temp_account=temp.getAccount();
        //request.setAttribute("owner",temp.getAccount());
        //System.out.println(temp.getAccount());
    %>
    <!--<a href="/shopListGoods" style="font-size: larger;color:green">查看旗下商品</a>-->
    <form action="/shopListGoods" method="post">
        <input type="hidden" name="account" value=${shop.account}>
        <input type="submit" value="查看旗下商品" style="font-size: larger;color:green;">
    </form>
</body>
</html>
