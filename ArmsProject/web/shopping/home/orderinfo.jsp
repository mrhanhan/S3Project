<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>订单详情</title>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>Essence - Fashion Ecommerce Template</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="../../styles/util.css">
    <link rel="stylesheet" href="../styles/home.css">
    <link rel="stylesheet" href="style.css">
    <style type="text/css">
        #productSize {
            font-size: 20px;
            border: none;
            border-bottom: 1px solid #c1a9a9;
            text-align: left;
            padding-left: 10px;
            display: inline;
            width: auto;
            width: 170px;
        }

        .good {
            height: 600px;
            width: 100%;
        }

        ul.order-title{
            text-align: center;
            margin: 10px 50px;
            transition: all 200ms;
            background-color: rgba(1, 154, 226, 0.53);
            border-radius: 3px;
            box-shadow: 0px 0px 30px rgba(54, 0, 128, 0.14);
        }
       ul.order-msg{
            text-align: center;

            margin: 10px 50px;
            transition: all 200ms;
        }
        .order-title li, .order ul.order-msg li {
            display: inline-block;
            width: 200px;

            line-height: 30px;
            padding: 10px 0px;
            /* border-bottom: 1px solid black; */
        }



        .order ul.order-msg {
            text-align: center;
            margin: 10px 50px;
            transition: all 200ms;
            border: 1px solid rgba(128, 128, 128, 0);
        }

        .order ul.order-msg:hover {
            border: 1px solid rgba(128, 128, 128, 0.42);
            box-shadow: 0px 0px 5px rgba(54, 0, 128, 0.14);
        }

         .dateil-group a{
             display: block;
             color: black;
         }

        .order{

            overflow: hidden;
            transition: all 200ms;
        }

       ul.dateil-msg{
            text-align: center;
            margin: 10px 50px;
            transition: all 200ms;
        }
        ul.dateil-title li, ul.dateil-msg li {
            display: inline-block;
            width: 200px;

            line-height: 30px;
            padding: 10px 0px;
            /* border-bottom: 1px solid black; */
        }
        ul.dateil-title  {
            text-align: center;

            transition: all 200ms;
            border-bottom: 1px solid gray;
        }
        .dateil-group{
            box-shadow: 0px 0px 5px rgba(54, 0, 128, 0.14);
            margin: 10px 200px;
            height: 0px;
            overflow: hidden;
            transition: all 500ms;
        }
    </style>
</head>
<body>

<!-- ##### Header Area Start ##### -->
<%@include file="head/head.jsp" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<!-- ##### Header Area End ##### -->
<div class="order-group" style="min-height: 600px;">
    <core:if test="${not empty cusOrderList}">

        <ul class="order-title">
            <li>单号</li>
            <li>订单时间</li>
            <li>商品个数</li>
            <li>消费金额</li>
            <li>订单状态</li>
            <li>收货地址</li>
        </ul>
        <core:forEach items="${cusOrderList}" var="cgs" varStatus="i">
            <div class="order" onclick="show(this)">
                <ul class="order-msg">
                    <li>${SIZE-i.count}</li>
                    <li>${cgs.order.odate}</li>
                    <li>${cgs.order.ocount}</li>
                    <li>${cgs.order.omoney}</li>
                    <li>${(i.count eq 1)?"待发货":((i.count eq 2)?"发货中":"完结")}</li>
                    <li>${cgs.customer.caddress}</li>
                </ul>
                <div class="dateil-group" >
                    <core:if test="${not empty cgs.orderDetails}">
                        <ul class="dateil-title">
                            <li>序号</li>
                            <li>商品名称</li>
                            <li>购买数量</li>
                            <li>价格</li>
                        </ul>
                        <core:forEach items="${cgs.orderDetails}" var="dateil" varStatus="j">
                            <a href="../../shop/queryGoods?gnum=${dateil.goods.gnum}">
                                <ul class="dateil-msg">
                                    <li>${j.count}</li>
                                    <li>${dateil.goods.gname}</li>
                                    <li>${dateil.detail.dcount}</li>
                                    <li>${dateil.detail.dmoney}</li>
                                </ul>
                            </a>
                        </core:forEach>
                    </core:if>
                    <core:if test="${empty cgs.orderDetails}">
                        空订单
                    </core:if>
                </div>
            </div>
        </core:forEach>
    </core:if>
    <core:if test="${ empty cusOrderList}">
        <br><br><br><br><br><br><br><br><br><br>
        <div style="font-size: 18pt;color: black;text-align: center;">未曾购买商品 <a href="../../shop/query"><div style="font-size: 18pt;color: black;text-align: center;display: inline-block;">前往购买</div></a></div>
        <br><br><br><br><br><br><br><br><br><br>
    </core:if>
</div>
<!-- ##### Single Product Details Area End ##### -->
<%@include file="head/bottom.jsp" %>
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
<script src="../../script/util.js"></script>
<!-- Active js -->
<script src="../script/ShopAJAX.js"></script>
<script type="text/javascript">
    $(
        function () {
            flushCartView("../../../")
        }
    );

    function Add() {
        var size = $("#productSize").val()
        $("#productSize").val("1")
        if (size == "0" || !size) {
            Toast("亲输入添加购物车的数据", 3000, "top");
            $("#productSize").focus();
        } else {
            AddCart("../../../", "${good.gnum}", size);
        }
        return false;
    }
    function show(g) {
        g = g.querySelector(".dateil-group")
        if($(g).css("height") == "0px"){
            $(g).css({"height":"auto"})
        }else{
            $(g).css({"height":"0px"})
        }
    }
</script>

</body>
</html>