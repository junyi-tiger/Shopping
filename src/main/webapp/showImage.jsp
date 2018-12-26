<%--
  Created by IntelliJ IDEA.
  User: YJ
  Date: 2018/12/23
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.shopping.bean.Image" %>
<%@ page import="com.shopping.dao.ImageDAO" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="jdk.internal.util.xml.impl.Input" %>
<%@ page import="java.io.InputStream" %>
<%
    InputStream inputStream = new ImageDAO().getImage(request.getParameter("name"));
    byte []b =new byte[inputStream.available()];
    inputStream.read(b);
    response.setContentType("image/jpeg");
    OutputStream outputStream = response.getOutputStream();
    outputStream.write(b);
    outputStream.flush();
    outputStream.close();
%>
