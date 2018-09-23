<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../styles/util.css">
    <link rel="stylesheet" href="../styles/home.css">
    <style type="text/css">
        .customer-msg{
            width: 70%;
            height: 500px;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(128, 128, 128, 0.21);
        }
        .customer-msg  table{
            width: 100%;
            margin: 50px auto;

            padding: 10px;
        }
        .cusimg{
            background-image: url("../../../images/customer/${LOGIN_CUS.cimg}");
            background-size: 100%;
            background-position: center center;
            background-repeat: no-repeat;

            width: 150px;
            height: 150px;
        }
        .key{
            padding: 0px 20px;
            font-size: 18pt;
            font-weight: bold;;
            font-family: "幼圆";
        }
        .val{
            margin: 0px 20px;
            font-size: 18pt;
            font-weight: bold;;
            font-family: "幼圆";

        }
    </style>
</head>
<%@include file="head/head.jsp" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<body>

<!--标题-->
<div class="customer-msg">
    <table>
        <tr>
            <td rowspan="3" class="cusimg" title="点击切换头像">

            </td>
            <td>
               <div class="key">昵称：</div>
                <input class="val" value="${LOGIN_CUS.cname}">
            </td>
        </tr>
        <tr>
            <td>
                账号
            </td>
        </tr>

        <td>
            密码:***
        </td>
        </tr>
        <tr>
            <td colspan="2">
                电话
            </td>
        </tr>
        <tr>
            <td colspan="2">
                邮箱
            </td>
        </tr>
        <tr>
            <td colspan="2">
                地址
            </td>
        </tr>
    </table>
</div>
<%@ include file="head/bottom.jsp" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<script src="js/jquery/jquery-2.2.4.min.js"></script>
<!-- Popper js -->
<script src="js/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="js/bootstrap.min.js"></script>
<!-- Plugins js -->
<script src="js/plugins.js"></script>
<!-- Classy Nav js -->
<script src="js/classy-nav.min.js"></script>
<!-- Active js -->
<script src="js/active.js"></script>
<script src="../../script/util.js"></script>
<!-- Active js -->
<script src="../script/ShopAJAX.js"></script>
<script type="text/javascript">
    $(
        function () {
            flushCartView("../../../")
        }
    );
</script>

<%@include file="../../tools/msg.jsp" %>

</body>
</html>