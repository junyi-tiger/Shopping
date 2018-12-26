
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
    <script src="${cp}/js/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<!--导航栏部分-->
<jsp:include page="include/header.jsp"/>

<!-- 中间内容 -->
<div class="container-fluid">
    <div class="row">
        <!-- 控制栏 -->
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-md-12">
                <h1><a name="section2">看帖看帖看帖</a></h1>
                <hr/>
                <table class="table table-hover center" id="cardTable">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">

                </button>
            </div>
            <div class="modal-body" id="show">

            </div>
            <div class="modal-footer">
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
<!-- 尾部 -->
<jsp:include page="include/foot.jsp"/>
<script type="text/javascript">

    var loading = layer.load(0);
    listAllCards();
    layer.close(loading);
    //全部列出
    function listAllCards() {
        var cardTable=document.getElementById("cardTable");
        var allCards=getAllCards();
        var html="";
        cardTable.innerHTML='<tr>'+
            '<th> 编号 </th>'+
            '<th> 用户</th>'+
            '<th> 浏览</th>'+
            '</tr>';
        for(var i=0;i<allCards.length;i++){
            html += '<tr>'+
                '<td>'+allCards[i].id+'</td>'+
                '<td>'+allCards[i].userId+'</td>'+
                '<td>'+
                '<td >'+allCards[i].content+'</td>'+
                '</tr>';

        }
        cardTable.innerHTML+=html;

    }
    function Scan(content) {
        alert(content);
        //var place=document.getElementById("show");
        //place.innerHTML(content);
    }
    function getAllCards(){
        var allCards=null;
        var nothing = {};
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getAllCards',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                if (result!=null) {
                    allCards = result.allCards;
                }
                else{
                    layer.alert('查询所有帖子错误');
                }
            },
            error : function(resoult) {
                layer.alert('内部查询所有帖子错误');
            }
        });
        allCards = eval("("+allCards+")");
        return allCards;
    }

</script>
</body>
</html>
