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
    <div class="container-fluid" style="padding-top: 80px;padding-bottom: 80px" >

        <h1 class="title center">只要密码呀</h1>
        <br/>
        <h3 class="title center">密码只有一个</h3>
        <br/>
        <div class="col-sm-offset-2 col-md-offest-2">
            <!-- 表单输入 -->
            <div  class="form-horizontal">

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <input type="password" class="form-control" id="inputPassword" placeholder="禁止输入非法字符" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="startLogin()">登录</button>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>

    <!--尾部-->
    <jsp:include page="include/foot.jsp"/>

    <script type="text/javascript">
        function startLogin() {
            var loading = layer.load(0);
           var psw= document.getElementById("inputPassword").value;

            if(psw == '6666'){
                layer.msg('登录成功',{icon:1});
                window.location.href = "${cp}/managerControl";
            }
           else{
                layer.msg('密码不对，加油猜',{icon:2});
            }

        }
    </script>

  </body>
</html>