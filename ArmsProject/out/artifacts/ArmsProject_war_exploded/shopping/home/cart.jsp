<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        out.println("<base href=\"" + basePath + "shopping/home/\">");
    %>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>购物车</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>购物车</title>
    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

    <link rel="stylesheet" href="reset.css">
    <link rel="stylesheet" href="carts.css">

    <script type="text/javascript" src="../../script/jquery.min.js"></script>
    <script type="text/javascript" src="../../script/util.js"></script>
    <link rel="stylesheet" href="../../styles/util.css">
    <link rel="stylesheet" href="../styles/home.css">
    <style type="text/css">
        .order_lists li p{
            line-height: 80px;
        }

    </style>
</head>
<body>
<%@include file="head/head.jsp" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<br/>
<br/><br/><br/><br/><br/><br/><br/><br/><br/>
<section class="cartMain">
    <div class="cartMain_hd">
        <ul class="order_lists cartTop" style="padding-left: 30px;">
            <li class="list_chk">
                商品图片
            </li>
            <li class="list_con">商品信息</li>

            <li class="list_price">单价</li>
            <li class="list_price">折扣价</li>
            <li class="list_amount">数量</li>
            <li class="list_sum">金额</li>
            <li class="list_op">操作</li>
        </ul>
    </div>

    <div class="cartBox" style="min-height: 500px;">
        <div class="shop_info">
            <%--<div class="all_check">--%>
            <%--<!--店铺全选-->--%>
            <%--<input type="checkbox" id="shop_a" class="shopChoice"> <label--%>
            <%--for="shop_a" class="shop"></label>--%>
            <%--</div>--%>
            <div class="shop_name">
                购物信息：<a href="javascript:;"></a>
            </div>
        </div>

        <div class="order_content">

        </div>
        <div>
            <div onclick="clearCart()" style="color: red;display: inline-block;padding: 10px 0px;">清空购物车</div>
        </div>
    </div>

    <!--底部-->
    <div class="bar-wrapper">
        <div class="bar-right">
            <div class="piece">
                已购数量<strong class="piece_num cartGoodsNum">0</strong>件
            </div>
            <div class="totalMoney">
                共计: <strong class="total_text">0.00</strong>
            </div>
            <div class="calBtn">
                <a class="btn_sty js" onclick="conmitCart()">结算</a>
            </div>
        </div>
    </div>
</section>
<section class="model_bg"></section>

<script src="carts.js"></script>

<!-- jQuery (Necessary for All JavaScript Plugins) -->
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

<!-- Active js -->
<script src="../script/ShopAJAX.js"></script>
<script type="text/javascript">

    $(
        function () {
            flushCartView("../../../")
            flushCartPage("../../../")
        }
    );
</script>
<%@include file="head/bottom.jsp"%>
</body>
</html>