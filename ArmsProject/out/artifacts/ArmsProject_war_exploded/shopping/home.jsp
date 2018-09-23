<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/4 0004
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<html>
<head>

    <script type="text/javascript" src="script/jquery.min.js"></script>
    <script type="text/javascript" src="script/popper.min.js"></script>
    <link rel="stylesheet" href="styles/bootstrap.min.css">
    <script type="text/javascript" src="script/bootstrap.min.js"></script>

    <script type="text/javascript" src="script/vue.min.js"></script>

    <script type="text/javascript" src="script/vue.min.js"></script>
    <script type="text/javascript" src="script/util.js"></script>
    <script type="text/javascript" src="shopping/script/home.js"></script>
    <script type="text/javascript" src="shopping/script/ShopAJAX.js"></script>
    <link rel="stylesheet" href="shopping/styles/home.css">
    <title>购物商场</title>
</head>
<body>
<!---->
<div class="container-fluid">
    <!--顶部内容-->
    <div class="row">
        <div class="usermsg">
            <core:if test="${ empty LOGIN_CUS}">
                <a href="shop/login">请登录</a>
                | <a href="shop/login">前往注册</a>
            </core:if>
            <core:if test="${not empty LOGIN_CUS}">
                <div class="tools">
                    <img src="../images/customer/${LOGIN_CUS.cimg}" style="border-radius: 3px;" width="24px" height="24px">
                        ${LOGIN_CUS.cname} | &nbsp;&nbsp;
                    <a href="">我的购物车</a> | <a href="">退出登录</a>
                </div>
            </core:if>
        </div>
    </div>

</div>
<div class="container-fluid">
    <div class="row">
        <div class=" col-md-1"></div>
        <div class=" col-md-2" style="text-align: center">
            <div class="title">
                军火网
            </div>
        </div>
        <div class="col-md-9">
            <div class="top-form">
                <form>
                    <select name="querytype">
                        <option value="">全部分类</option>
                        <core:forEach items="${types}" var="i">
                            <option value="${i.tnum}">${i.tname}</option>
                        </core:forEach>
                    </select><input type="text" name="keyword" style="width:60%">
                    <button class=" btns"></button>
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 b">
            <div class="menu">
                <core:forEach items="${types}" var="i">
                    <div> ${i.tname}</div>
                </core:forEach>
            </div>
        </div>
        <div class="col-md-9 b">
            <core:forEach items="${goods}" var="ggs">

                <div class="goods-group">
                    <div class="group-name">
                            ${ggs.type.tname}
                        <span class="rremark"> ${ggs.type.tremark}</span>
                    </div>
                    <hr/>
                    <core:forEach items="${ggs.goods}" var="goods">
                        <div class="goods">
                            <img src="images/goodspic/${goods.gimg}" width="220" height="180">
                            <div class="goods-name">${goods.gname}</div>

                            <p class="remark">${ empty goods.gremark ?"无备注":goods.gremark}</p>
                            <div class="msg"><span>${goods.gisdiscount eq '是'?'<s>':''}价格:${goods.gprice}${goods.gisdiscount eq '是'?'</s>':''}</span>
                                <core:if test="${goods.gisdiscount eq '是'}"><span
                                        style="color: red;">折扣价:${(goods.gprice * goods.gdiscount)/100}</span></core:if>
                                <div class="buts">
                                    <button onmouseover="showPlane(this)" onmouseout="showPlane(this),">+</button>
                                </div>
                            </div>
                            <div class="goods_form" >
                                <form action="cart/add" method="post" onsubmit="addGoods(this); return false;">
                                    <input type="hidden" name="goods_num"  value="${goods.gnum}"><br/>
                                    数量:<input type="number" name="size" value="0" min="0" onchange="changeMoney(this),addGoods(this.parentElement);"><br/>
                                    <span class="money">价格:0</span>
                                    <button>加入购物车</button>
                                </form>
                            </div>
                        </div>
                    </core:forEach>
                </div>

            </core:forEach>
        </div>
        <div class="col-md-1 b">1</div>
    </div>
</div>
<script type="text/javascript">

</script>
<%@include file="../tools/msg.jsp"%>
</body>
</html>
