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
    <script src="${cp}/js/ajaxfileupload.js" type="text/javascript"></script>
    <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${cp}/js/layer.js" type="text/javascript"></script>

    <!--[if lt IE 9]>
    <script src="${cp}/js/html5shiv.min.js"></script>
    <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${cp}/main">购物+</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

            <ul class="nav navbar-nav navbar-right">
                <li> <a  href="#" onclick="enablePost();return false;">开启发帖功能</a></li>
                <li><a  href="#" onclick="unablePost();return false;">关闭发帖功能</a></li>
                <li><a href="${cp}/main"  >退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 中间内容 -->
<div class="container-fluid">
    <div class="row">
        <!-- 控制栏 -->
        <div class="col-sm-3 col-md-2 sidebar sidebar-1">
            <ul class="nav nav-sidebar">
                <li class="list-group-item-diy"><a href="#section1">查看商家<span class="sr-only">(current)</span></a></li>
                <li class="list-group-item-diy"><a href="#section2">查看买家<span class="sr-only">(current)</span></a></li>
                <li class="list-group-item-diy"><a href="#section3">查看vip<span class="sr-only">(current)</span></a></li>
                <li class="list-group-item-diy"><a href="#section4">查看帖子</a></li>

            </ul>
        </div>
        <!-- 控制内容 -->
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="col-md-12">
                <h1><a name="section1" id="section1">商家信息</a></h1>
                <hr/>
                <table class="table table-hover center" id="bossTable">
                </table>
            </div>


            <div class="col-md-12">
            <h1><a name="section2" id="section2">普通买家信息</a></h1>
            <hr/>
            <table class="table table-hover center" id="userTable">
            </table>
            </div>

            <div class="col-md-12">
                <h1><a name="section3" id="section3">vip信息</a></h1>
                <hr/>
                <table class="table table-hover center" id="vipTable">
                </table>
            </div>

            <div class="col-md-12">
                <h1><a name="section4" id="section4">管理帖子</a></h1>
                <hr/>
                <table class="table table-hover center" id="manageCardTable">
                </table>
            </div>

        </div>
    </div>
</div>


<!-- 尾部 -->
<jsp:include page="include/foot.jsp"/>
<script type="text/javascript">

    var loading = layer.load(0);
    listAllUser();
    //listAllProduct();
    listCards();
    layer.close(loading);
    function listCards() {
        var cardTable=document.getElementById("manageCardTable");
        var allCards=getCards();
        var html="";
        cardTable.innerHTML='<tr>'+
            '<th> 编号 </th>'+
            '<th> 用户</th>'+
            '<th> 浏览</th>'+
            '<th> 删除</th>'+
            '</tr>';
        for(var i=0;i<allCards.length;i++){

            html += '<tr>'+
                '<td>'+allCards[i].id+'</td>'+
                '<td>'+allCards[i].userId+'</td>'+
                '<td>'+allCards[i].content+'</td>'+
                '<td>'+
                '<button class="btn btn-primary btn-sm" type="submit" onclick="deleteCard('+allCards[i].id+')">删除</button>'+
                '</td>'+
                '</tr>';
        }
        cardTable.innerHTML+=html;
    }
    function getCards() {
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
    //列出所有商家
    function listAllUser() {
        var userTable = document.getElementById("userTable");
        var bossTable=document.getElementById("bossTable");
        var vipTable=document.getElementById("vipTable");
        var allUser = getAllUsers();
        userTable.innerHTML = '<tr>'+
            '<th> 用户ID </th>'+
            '<th> 用户名</th>'+
            '<th> 昵称</th>'+
            '<th> 邮箱</th>'+
            '<th> 是否删除</th>'+
            '</tr>';
        bossTable.innerHTML = '<tr>'+
            '<th> 用户ID </th>'+
            '<th> 用户名</th>'+
            '<th> 昵称</th>'+
            '<th> 邮箱</th>'+
            '<th> 是否删除</th>'+
            '</tr>';
        vipTable.innerHTML='<tr>'+
            '<th> 用户ID </th>'+
            '<th> 用户名</th>'+
            '<th> 昵称</th>'+
            '<th> 邮箱</th>'+
            '<th> 是否删除</th>'+
            '</tr>';
        var html0 = "";
        var html = "";
        var html1="";
        for(var i=0;i<allUser.length;i++){
            if(allUser[i].role==0)
            {
                html += '<tr>'+
                    '<td>'+allUser[i].id+'</td>'+
                    '<td>'+allUser[i].name+'</td>'+
                    '<td>'+allUser[i].nickName+'</td>'+
                    '<td>'+allUser[i].email+'</td>'+
                    '<td>'+
                    '<button class="btn btn-primary btn-sm" type="submit" onclick="deleteUser('+allUser[i].id+')">删除</button>'+
                    '</td>'+
                    '</tr>';
            }
            if(allUser[i].role==1)
            {
                html0 += '<tr>'+
                    '<td>'+allUser[i].id+'</td>'+
                    '<td>'+allUser[i].name+'</td>'+
                    '<td>'+allUser[i].nickName+'</td>'+
                    '<td>'+allUser[i].email+'</td>'+
                    '<td>'+
                    '<button class="btn btn-primary btn-sm" type="submit" onclick="deleteBoss('+allUser[i].id+')">删除</button>'+
                    '</td>'+
                    '</tr>';
            }
            if(allUser[i].role==2)
            {
                html1 += '<tr>'+
                    '<td>'+allUser[i].id+'</td>'+
                    '<td>'+allUser[i].name+'</td>'+
                    '<td>'+allUser[i].nickName+'</td>'+
                    '<td>'+allUser[i].email+'</td>'+
                    '<td>'+
                    '<button class="btn btn-primary btn-sm" type="submit" onclick="deleteBoss('+allUser[i].id+')">删除</button>'+
                    '</td>'+
                    '</tr>';
            }
        }
        userTable.innerHTML += html;
        bossTable.innerHTML += html0;
        vipTable.innerHTML+=html1;
    }

    function getAllUsers() {
        var allUsers = "";
        var nothing = {};
        $.ajax({
            async : false, //设置同步
            type : 'POST',
            url : '${cp}/getAllUser',
            data : nothing,
            dataType : 'json',
            success : function(result) {
                if (result!=null) {
                    allUsers = result.allUsers;
                }
                else{
                    layer.alert('查询所有用户错误');
                }
            },
            error : function(resoult) {
                layer.alert('查询所有用户错误');
            }
        });
        allUsers = eval("("+allUsers+")");
        return allUsers;
    }



    function deleteUser(id) {
        var user = {};
        user.id = id;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/deleteUser',
            data : user,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('查询用户错误');
            }
        });
        layer.msg(deleteResult.message);
        listAllUser()
    }

    function deleteCard(id) {
        alert("进入delteCard（）")
        var card = {};
        card.id = id;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/deleteCard',
            data : card,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('查询用户错误');
            }
        });
        layer.msg(deleteResult.message);
        listCards();
    }


    function deleteBoss(id) {
        var user = {};
        user.id = id;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/deleteBoss',
            data : user,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('查询用户错误');
            }
        });
        layer.msg(deleteResult.message);
        listAllUser()
    }

    function deleteProduct(id) {
        var product = {};
        product.id = id;
        var deleteResult = "";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/deleteProduct',
            data : product,
            dataType : 'json',
            success : function(result) {
                deleteResult = result;
            },
            error : function(result) {
                layer.alert('删除商品错误');
            }
        });
        layer.msg(deleteResult.message);
        listAllProduct();
    }

    function addProduct() {
        var loadings = layer.load(0);
        var product = {};
        product.name = document.getElementById("productName").value;
        product.description = document.getElementById("productDescribe").value;
        product.keyWord = document.getElementById("keyWord").value;
        product.price = document.getElementById("productPrice").value;
        product.counts = document.getElementById("productCount").value;
        product.type = document.getElementById("productType").value;
        var addResult="";
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/addProduct',
            data : product,
            dataType : 'json',
            success : function(result) {
                addResult = result.result;
            },
            error : function(result) {
                layer.alert('删除商品错误');
            }
        });
        if(addResult == "success") {
            fileUpload();
            layer.msg('添加商品成功', {icon: 1, time: 1000});
            layer.close(loadings);
        }
        listAllProduct();
    }

    function fileUpload() {
        var results = "";
        var name = document.getElementById("productName").value;
        $.ajaxFileUpload({
            url:'${cp}/uploadFile?name='+name,
            secureuri:false ,
            fileElementId:'productImgUpload',
            type:'POST',
            dataType : 'text',
            success: function (result){
                result = result.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre style="....">text</pre>前后缀
                result = result.replace(/<PRE.*?>/g, '');
                result = result.replace("<PRE>", '');
                result = result.replace("</PRE>", '');
                result = result.replace("<pre>", '');
                result = result.replace("</pre>", '');
                result = JSON.parse(result);
                results = result.result;
                if(results == "success") {
                    layer.msg("图片上传成功", {icon: 1});
                    window.location.href = "${cp}/control";
                    //var imgPreSee = document.getElementById("imgPreSee");
                    //var imgSrc = '${cp}/img/'+name+'.jpg';
                    //imgPreSee.innerHTML +='<img src="'+imgSrc+')" class="col-sm-12 col-md-12 col-lg-12"/>';
                }
                else {
                    layer.msg("图片上传失败", {icon: 0});
                }

            },
            error: function ()
            {
                layer.alert("上传错误");
            }}
        );
    }
  function  enablePost(){
        var isopen=1;
      $.ajax({
          async : false,
          type : 'POST',
          url : '${cp}/Post',
          data : isopen,
          dataType : 'json',
          success : function(result) {
              addResult = result.result;
          },
          error : function(result) {

          }
      });
      if(addResult == "success") {

      }
  }
    function  unablePost(){
        alert("hello")
        var isopen=0;
        $.ajax({
            async : false,
            type : 'POST',
            url : '${cp}/Post',
            data : isopen,
            dataType : 'json',
            success : function(result) {
                addResult = result.result;
            },
            error : function(result) {

            }
        });
        if(addResult == "success") {

        }
    }
</script>
</body>
</html>