<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.xmbcit.xj808.arms.vo.OrderGoodsDateils"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="./admin/user/script/usermanager.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">


    <%--<link rel="prefetch" href="orderinfo/query?pageindex=0">--%>
    <%--<link rel="prefetch" href="orderinfo/query?pageindex=${orderInfoPage$lastIndex}">--%>


    <%--<core:forEach begin="1" end="${orderInfoPage$maxPage}" varStatus="i">--%>
    <%--<link rel="prefetch" href="orderinfo/query?pageindex=${i.count}">--%>
    <%--</core:forEach>--%>
    <%--<link rel="prefetch" href="orderinfo/query?pageindex=${orderInfoPage$nextIndex}">--%>
    <%--<link rel="prefetch" href="orderinfo/query?pageindex=${orderInfoPage$maxPage}">--%>

</head>
<body>
<base target="CONTENTBODY">
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-订单详情
    </div>
    <div class="btn-group" style="text-align: right; display: inline-block;float: right;">
        <a href="orderinfo/query" class="btn-submit">订单主页</a>
    </div>



    <div>
    	<table class="orderdetailsTable" style="text-align: left;">
    	
		<tr>
			<th class="name_td"  style="text-align: right;">订单编号:</th>
			<td>${cgd.order.onum}</td>
			<th class="name_td"  style="text-align: right;">下单时间:</th>
			<td>${cgd.order.odate}</td>
			
		</tr>
		
		<tr>
			<th class="name_td" style="text-align: right;">客户编号:</th>
			<td>${cgd.customer.cnum }</td>
			<th class="name_td" style="text-align: right;">客户账号:</th>
			<td>${cgd.customer.caccount } </td>
			
		</tr>
		<tr>
			<th class="name_td" style="text-align: right;">客户姓名:</th>
			<td>${cgd.customer.cname}</td>
			<th class="name_td" style="text-align: right;">电话:</th>
			<td>${cgd.customer.ctelephone }</td>

		</tr>
		<tr>
            <th class="name_td" style="text-align: right;">邮箱:</th>
            <td>${cgd.customer.cemail }</td>
			<th class="name_td" style="text-align: right;">收货地址:</th>
			<td>${cgd.customer.caddress }</td>
		</tr>
            <tr>
                <th class="name_td" style="text-align: right;">消费:</th>
                <td><fmt:formatNumber value="${cgd.order.omoney }" pattern="####,####.000"></fmt:formatNumber></td>
                <td></td>
                <td></td>
            </tr>

	</table>
    </div>
    <!--信息-->
    <div class="userinfo-group">
        <table cellspacing="0">
            <tr class="top">
                <th>序号</th>
                <th>商品图片</th>
                <th>商品编号</th>
                
                <th>商品名称</th>
                <th>商品价格</th> 
                <th>商品折扣</th> 
                <th>购买数量</th> 
                
            </tr>
            <!--遍历数据-->
            <core:forEach items="${cgd.orderDetails}" var="o" varStatus="i">
                <tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow" style="text-align: center;">
                    <td>${i.count}</td>
                  	<td><img src="images/goodspic/${o.goods.gimg}" width="48px" height="48px"></td>
					<td>${o.goods.gnum}</td>
					<td>${o.goods.gname}</td>
					<td><fmt:formatNumber value="${o.goods.gprice}" pattern="####,####.000"></fmt:formatNumber></td>
					<td>${o.goods.gdiscount}</td>
					<td>${o.detail.dcount}</td>
					
                </tr>
            </core:forEach>
        </table>
    </div>
</div>
<%@ include file="../../tools/msg.jsp" %>
</body>
</html>