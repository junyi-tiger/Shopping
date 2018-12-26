<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2018/12/22
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>购物+</title>
    <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${cp}/css/style.css" rel="stylesheet">

    <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
    <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
    <script src="${cp}/js/html5shiv.min.js"></script>
    <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--导航栏部分-->
<jsp:include page="include/header.jsp"/>

<!-- 中间内容 -->
<div class="container-fluid bigHead">
    <div class="row">
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <div class="jumbotron">
                <h1>发帖啦！！！</h1>
                <p>欢迎各种吐槽</p>
            </div>
            <div class="col-lg-12 col-md-12 col-sm-12" id="productArea"><div>
        </div>
        <div class="col-sm-10  col-md-10 col-sm-offset-1 col-md-offset-1">
            <textarea class="form-control" rows="30" id="cardContent"></textarea>

            <hr/>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-4"></div>
                <button type="button" class="btn btn-danger btn-lg col-lg-4 col-md-4 col-sm-4" onclick="sendContent()">看帖发帖</button>
            </div>
        </div>
    </div>
</div>
    </div>
</div>
<jsp:include page="include/foot.jsp"/>



<script type="text/javascript">

    var loading = layer.load(0);
    //listAllCards();
    layer.close(loading);



    function sendContent() {
        //var postContent=new String(document.getElementById("cardContent").innerHTML);

       var card={};
       card.content=document.getElementById("cardContent").value;
         card.userId=${currentUser.id};
        // card.setContent()
        // alert(card.content);
       // alert(card.content=="");
        if(card.content==""){
            layer.alert('帖子不能为空');
        }
        else {
        var cardResult = null;
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/addCard',
            data :card,
            dataType : 'json',
            success : function(result) {
                cardResult = result.result;
            },
            error : function(result) {
                layer.alert('内部问题失败了啊！！！');
            }
        })
        if(cardResult == "success") {
            layer.confirm('看看发布的帖子吧！', {icon: 1, title:'发布成功',btn:['前往查看','就是不看']},
                function(){
                //此处要改
                    window.location.href = "${cp}/all_cards";
                },
                function(index){
                    layer.close(index);
                    window.location.href = "${cp}/send_card";
                    }
            );
        }
        }
    }
</script>
</body>
</html>