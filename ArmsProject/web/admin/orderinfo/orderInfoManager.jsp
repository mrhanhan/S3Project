<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        |-订单查找
    </div>
    <!--功能按钮-->
    <div class="btn-group" style="text-align: right; display: inline-block;float: right;">
        
        <a href="orderinfo/query" class="btn-submit">刷新</a>
    </div>
    <!--模糊查询-->
    <div class="input-gropu">
        <form action="orderinfo/query">
            <input type="text" name="keyword" placeholder="关键字" value="${key}" style="width:260px"/>
            <button class="btn">查询</button>
            <select name="order">
                <option value="o_num" >编号</option>
                <option value="o_date" ${order eq 'o_date'?'selected':''}>时间</option>
                <option value="o_money" ${order eq 'o_money'?'selected':''}>消费价格</option>
                <option value="o_count" ${order eq 'o_count'?'selected':''}>购买数量</option>
            </select>
            <button class="btn">排序</button>
        </form>
    </div>
    <!--信息-->
    <div class="userinfo-group">
        <table cellspacing="0">
            <tr class="top">
                <th>序号</th>
                <th>订单编号</th>
                <th>顾客编号</th>
                <th>总价格</th>
                <th>总数量</th>
                <th>下单时间</th>
                <th></th>
            </tr>
            <!--遍历数据-->
            <core:forEach items="${orderInfoPage$datas}" var="o" varStatus="i">

                <tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow">
                    <td>${i.count}</td>
                    <td>${o.onum}</td>
                    <td>${o.cnum}</td>
                    <td><fmt:formatNumber value="${o.omoney}" pattern="####,####.000"></fmt:formatNumber></td>
                    <td>${o.ocount}</td>
                    <td>${o.odate}</td>
                    <td style="text-align: center">
                        <button class="btn2" style="width:200px"><a href="orderdetails/queryorder?onum=${o.onum}">查看订单明细</a></button>
                    </td>
                </tr>
            </core:forEach>
            <!--遍历空白-->
            <core:forEach begin="${orderInfoPage$dataSize}" end="20" varStatus="i">
                <tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'}">
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                    </td>
                </tr>
            </core:forEach>
        </table>

        <div class="page-group">
            <a class="btn" href="orderinfo/query?pageindex=0">首页</a>
            <a class="btn" href="orderinfo/query?pageindex=${orderInfoPage$lastIndex}">上一页</a>
            <div class="pages">
                <core:forEach begin="1" end="${orderInfoPage$maxPage}" varStatus="i">
                    <a class="page ${i.count eq orderInfoPage$nowPage?'select':''}"
                       href="orderinfo/query?pageindex=${i.count}">${i.count}</a>
                </core:forEach>
            </div>
            <a class="btn" href="orderinfo/query?pageindex=${orderInfoPage$nextIndex}">下一页</a>
            <a class="btn" href="orderinfo/query?pageindex=${orderInfoPage$maxPage}">尾页</a>
        </div>
    </div>
</div>
<%@ include file="../../tools/msg.jsp" %>
</body>
</html>