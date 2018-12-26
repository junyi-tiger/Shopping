<%--
  Created by IntelliJ IDEA.
  User: liu
  Date: 2018/12/23
  Time: 1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid" style="padding-top: 80px;padding-bottom: 80px" >

    <h1 class="title center">登录</h1>
    <br/>
    <div class="col-sm-offset-2 col-md-offest-2">
        <!-- 表单输入 -->
        <div  class="form-horizontal">
            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 col-md-2 control-label">邮箱/用户名</label>
                <div class="col-sm-6 col-md-6">
                    <input type="text" class="form-control" id="inputEmail" placeholder="xxxxxx@xx.com"/>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword" class="col-sm-2 col-md-2 control-label">密码</label>
                <div class="col-sm-6 col-md-6">
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
</body>
</html>
