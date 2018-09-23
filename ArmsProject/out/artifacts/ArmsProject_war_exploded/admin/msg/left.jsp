<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/22 0022
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--功能区-->
<base target="CONTENTBODY">
<div class="tool-group" show="true">
    <a href="admin/home.jsp" target="_top">
        <div class="title title-hover"><img src="./images/icon/home.png"/> 主页</div>
    </a>
</div>
<hr/>
<div class="tool-group" show="false">
    <div class="title title-hover"><img src="./images/icon/user.png"/> 用户</div>
    <div class="tool-item"  ><a href="user/add">用户注册</a></div>
    <div class="tool-item"><a href="user/query">用户管理</a></div>
</div>

<div class="tool-group" show="false">
    <div class="title"><img src="./images/icon/cus.png"/>  客户</div>
    <div class="tool-item"><a href="customer/add">添加客户</a></div>
    <div class="tool-item"><a href="customer/query">客户管理</a></div>
    <!--div class="tool-item"><a href="./admin/customer/cusCunsome.jsp">客户消费</a></div-->
</div>
<hr class="hr"/>
<div class="tool-group" show="false">
    <div class="title"><img src="./images/icon/type.png"/> 类型</div>
    <div class="tool-item"><a href="goodstype/add">添加类型</a></div>
    <div class="tool-item"><a href="goodstype/query">类型管理</a></div>
</div>

<div class="tool-group" show="false">
    <div class="title title-hover"><img src="./images/icon/order.png"/>  公告</div>
    <div class="tool-item"><a href="bulliten/add">发布公告</a></div>
    <div class="tool-item"><a href="bulliten/query">发布管理</a></div>
</div>
<div class="tool-group" show="false">
    <div class="title"><img src="./images/icon/goods.png"/> 商品</div>
    <div class="tool-item"><a href="shop/query" target="_blank">商品信息</a></div>
    <div class="tool-item"><a href="goods/add">添加商品</a></div>
    <div class="tool-item"><a href="goods/query">商品管理</a></div>

</div>
<hr class="hr"/>
<div class="tool-group" show="false">
    <div class="title title-hover"><img src="./images/icon/order.png"/>  订单</div>
    <div class="tool-item">历史订单</div>
    <div class="tool-item">订单记录</div>
    <div class="tool-item"><a href="orderinfo/query">订单管理</a></div>
</div>