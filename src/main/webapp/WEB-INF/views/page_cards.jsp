<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2018/12/23
  Time: 13:08
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
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default" onclick="subCounts()">上一页</button>
                                <input type="text" id="no"  value="1"></input>
                                <button type="button" class="btn btn-default" onclick="addCounts(1)">下一页</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 尾部 -->
            <jsp:include page="include/foot.jsp"/>
            <script type="text/javascript">

                var loading = layer.load(0);
                listPageCards();
                layer.close(loading);
                //分页列出
                function listPageCards() {
                    var cardTable=document.getElementById("cardTable");
                    var allCards=getPageCards();

                }
                //列出所有商家
                function listAllUser() {
                    var userTable = document.getElementById("userTable");
                    var bossTable=document.getElementById("bossTable");
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
                    var html0 = "";
                    var html = "";
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
                    }
                    userTable.innerHTML += html;
                    bossTable.innerHTML += html0;
                }


                 function getPageCards(){
                    var pageCards="";
                    var page={};
                    var no=document.getElementById("no").value;
                    var start=parseInt(no);
                    start=3*(no-1)+1;
                    page.beginIndex=start;
                    page.pageSize=3;
                    aler(start+" "+ page.beginIndex);
                     $.ajax({
                         async : false, //设置同步
                         type : 'POST',
                         url : '${cp}/pageQuery',
                         data : page,
                         dataType : 'json',
                         success : function(result) {
                             if (result!=null) {
                                 pageCards = result.threeCards;
                             }
                             else{
                                 layer.alert('分页查询错误');
                             }
                         },
                         error : function(resoult) {
                             layer.alert('分页查询内部错误');
                         }
                     });
                     pageCards = eval("("+pageCards+")");
                     return pageCards;

                 }

                function listAllProduct() {
                    var productArea = document.getElementById("productArea");
                    var allProduct = getAllProducts();
                    var html="";
                    productArea.innerHTML = '';
                    for(var i=0;i<allProduct.length;i++){
                        var imgURL = "${cp}/img/"+allProduct[i].id+".jpg";
                        html+='<div class="col-sm-4 col-md-4 pd-5">'+
                            '<div class="boxes">'+
                            '<div class="big bigimg">'+
                            '<img width:280px; height:160px; src="'+imgURL+'">'+
                            '</div>'+
                            '<p class="font-styles center">'+allProduct[i].name+'</p>'+
                            '<p class="pull-right">价格：'+allProduct[i].price+'</p>'+
                            '<p class="pull-left">库存：'+allProduct[i].counts+'</p>'+
                            '<div class = "row">'+
                            '<button class="btn btn-primary delete-button" type="submit" onclick="deleteProduct('+allProduct[i].id+')">删除商品</button>'+
                            '</div>'+
                            '</div>'+
                            '</div>';
                    }
                    productArea.innerHTML+=html;
                }
                //列出所有商品

                function getAllProducts() {
                    var allProducts = null;
                    var nothing = {};
                    $.ajax({
                        async : false, //设置同步
                        type : 'POST',
                        url : '${cp}/getAllProducts',
                        data : nothing,
                        dataType : 'json',
                        success : function(result) {
                            if (result!=null) {
                                allProducts = result.allProducts;
                            }
                            else{
                                layer.alert('查询所有商品错误');
                            }
                        },
                        error : function(resoult) {
                            layer.alert('查询所有商品错误');
                        }
                    });
                    allProducts = eval("("+allProducts+")");
                    return allProducts;
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
                        layer.close(loadings)
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
            </script>
</body>
</html>