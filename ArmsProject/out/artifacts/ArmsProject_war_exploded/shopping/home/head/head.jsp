<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/6 0006
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" language="java" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/head/\">");
%>
<header class="header_area">
    <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between">
        <!-- Classy Menu -->
        <nav class="classy-navbar" id="essenceNav">
            <!-- Logo -->
            <a class="nav-brand" href="../../../shop"><img src="img/core-img/logo.jpg" width="90px"
                                                       style="border-radius: 25%;" height="80px" alt=""></a>
            <!-- Navbar Toggler -->
            <div class="classy-navbar-toggler">
                <span class="navbarToggler"><span></span><span></span><span></span></span>
            </div>
            <!-- Menu -->
            <div class="classy-menu">
                <!-- close btn -->
                <div class="classycloseIcon">
                    <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                </div>
                <!-- Nav Start -->
                <div class="classynav">
                    <ul>
                        <li><a href="#">购物</a>
                            <div class="megamenu">
                                <!--具体类型的商品-->
                                <ul class="single-mega cn-col-4">
                                    <li class="title">枪械</li>
                                    <li><a href="../../../shop/query?ShowName=步枪&typeName=步枪 陆军">步枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=突击步枪&typeName=AKM M16A4 M416 SCAR-L">突击步枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=狙击步枪&typeName=AWM M24 98K">狙击步枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=精确射手步枪&typeName=SKS MINI14 VSS SLR">精确射手步枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=霰弹枪&typeName=S12K S686">霰弹枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=步冲锋枪&typeName=UMP9 UZI">冲锋枪</a></li>
                                    <li><a href="../../../shop/query?ShowName=手枪&typeName=P92 P1911 R1985">手枪</a></li>
                                    
                                </ul>
                                <ul class="single-mega cn-col-4">
                                    <li class="title">重型</li>
                                    <li><a href="../../../shop/query?ShowName=陆军坦克&typeName=坦克 车">陆军坦克</a></li>
                                    <li><a href="../../../shop/query?ShowName=陆军火炮&typeName=火炮 炮 箭 导">陆军火炮</a></li>
                                    <li><hr/></li>
                                    <li><a href="../../../shop/query?ShowName=海军战舰&typeName=战舰 船">海军战舰</a></li>
                                    <li><a href="../../../shop/query?ShowName=海军航母&typeName=战舰 航母 艇 防空">海军航母</a></li>
                                    <li><hr/></li>
                                    <li><a href="../../../shop/query?ShowName=空军战机&typeName=飞机 机 歼击 轰炸">空军战机</a></li>
                                    <li><a href="../../../shop/query?ShowName=海军航母&typeName=电子 雷达 导">电子雷达</a></li>
                                    
                                </ul>
                                <ul class="single-mega cn-col-4">
                                	<li class="title">补给</li>
                                    <li><a href="../../../shop/query?ShowName=头盔&typeName=盔">头盔</a></li>
                                    <li><a href="../../../shop/query?ShowName=防弹衣&typeName=防弹">防弹衣</a></li>
                                    <li><a href="../../../shop/query?ShowName=腰带&typeName=腰 带">腰带</a></li>
                                    <li><a href="../../../shop/query?ShowName=背包&typeName=包">背包</a></li>
                                    <li><a href="../../../shop/query?ShowName=子弹&typeName=弹">子弹</a></li>
                                </ul>
                                <div class="single-mega cn-col-4">
                                    <img src="img/other/po.jpeg" alt="">
                                </div>
                            </div>
                        </li>
                        <li><a href="#">页面</a>
                            <ul class="dropdown">
                                <li><a href="../../../shop">主页</a></li>
                                <li><a href="/../../../shop/query">军火仓库</a></li>
                                <li><a href="../../../cart">购物车</a></li>
                                <li><a href="../../../shopgoods/orderQuery">我的订单</a></li>
                                <li><a href="../../../about/about.jsp" target="_blank">关于我们</a></li>
                            </ul>
                        </li>
                        <li><a href="../../../about/about.jsp" target="_blank">About</a>

                        </li>

                    </ul>
                </div>
                <!-- Nav End -->
            </div>
        </nav>

        <!-- Header Meta Data -->
        <div class="header-meta d-flex clearfix justify-content-end">
            <!-- Search Area -->
            <div class="search-area">
                <form action="/../../../shop/query" method="post">
                    <input type="search" name="typeName" value="${typeName}" id="headerSearch" placeholder="类型名称">
                    <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                </form>
            </div>
            <!-- Favourite Area -->
            <div class="favourite-area">
                <a href="../../../shopgoods/orderQuery"><img src="fonts/sent.svg" alt=""></a>
            </div>
            <!-- User Login Info -->
            <div class="user-login-info">
                <core:if test="${empty LOGIN_CUS}">
                <a href="./../../../useroperation/login">
                    <img src="img/core-img/user.svg" alt="">
                </a>
                </core:if>
                <core:if test="${not empty LOGIN_CUS}">
                    <div style="width: 82px;text-align: left">
                    <a href="./../../../useroperation/logout" onclick="return confirm('是否退出登录？')" title="点击退出登陆"
                       style="width :65px;height:82px; padding-left:3px;margin:0px auto;display: block;background-image: url('../../../images/customer/${LOGIN_CUS.cimg}');background-repeat: no-repeat;background-size: 60px;background-position: center center;">

                    </a>
                    </div>
                </core:if>
            </div>
            <!-- Cart Area -->
            <div class="cart-area">
                <!--购物车商品数量-->
                <a href="#" id="essenceCartBtn" onclick="flushCartView('../../../cart/get')"><img src="img/core-img/bag.svg" alt="" > <span class="cartGoodsNum">0</span></a>
            </div>
        </div>
    </div>
</header>
<!-- ##### Right Side Cart Area ##### -->
<div class="cart-bg-overlay"></div>

<div class="right-side-cart-area">
    <!-- Cart Button -->
    <!-- 购物车按钮 -->
    <div class="cart-button">
        <!-- 商品数量 -->
        <a href="#" id="rightSideCart"><img src="img/core-img/bag.svg" alt=""> <span class="cartGoodsNum">0</span></a>
    </div>
    <div class="cart-content d-flex">

        <!-- Cart List Area -->
        <!-- 购物车列表区域 -->
        <div class="cart-list" id="cart">
            <!-- Single Cart Item -->

        </div>

        <!-- Cart Summary -->
        <!-- 购物车账单 -->
        <div class="cart-amount-summary" id="order">
            <h2 id="title">账单</h2>
            <ul class="summary-table" id="orderlist">
                <li><span>原价:</span> <span>0.000</span></li>
                <li><span>数量:</span> <span>0</span></li>
                <li><span>折扣:</span> <span>0.000</span></li>
                <li><span>现价:</span> <span>0.000</span></li>
            </ul>
            <div class="checkout-btn mt-100" id="buyBtn">
                <a href="../../../cart" class="btn essence-btn">买单</a>
            </div>
        </div>
    </div>
</div>
<br/><br/><br/><br/>
<!-- ##### Right Side Cart End ##### -->
