<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<html>
<head>
    <script type="text/javascript" src="./script/jquery.min.js"></script>
    <link rel="stylesheet" href="./styles/util.css">
    <link rel="stylesheet" href="./styles/color.css">
    <link rel="stylesheet" href="./styles/page.css">
    <title>首页</title>
    <link rel="stylesheet" href="./admin/styles/dialog.css">
    <link rel="stylesheet" href="./styles/form.css">
    <script type="text/javascript" src="./script/util.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
<base target="CONTENTBODY">
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-添加消费信息分布
    </div>
    <div class="btn-group" style="text-align: right; display: inline-block;float: right; padding-right: 10px">
        <a class="btn-submit" href="./admin/customer/customerManager.jsp">管理主页</a>
        <a class="btn-submit" href="./admin/customer/cusCunsome.jsp">刷新</a>
    </div>

    <hr class="hr" style="margin-top:45px;"/>
    <div>

    </div>
    <%@ include file="../../tools/msg.jsp" %>
</body>
</html>
