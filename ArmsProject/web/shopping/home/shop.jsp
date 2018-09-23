<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>军火网-${ShowName}</title>
    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/favicon.ico">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../styles/util.css">
    <link rel="stylesheet" href="../styles/home.css">
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
<!-- ##### Breadcumb Area Start ##### -->
<div class="breadcumb_area bg-img"
     style="background-image: url(img/bg-img/breadcumb.jpg);">
    <div class="container h-100">
        <div class="row h-100 align-items-center">
            <div class="col-12">
                <div class="page-title text-center">
                    <h2>${ShowName}</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ##### Breadcumb Area End ##### -->

<!-- ##### Shop Grid Area Start ##### -->
<section class="shop_grid_area section-padding-80">
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 col-lg-3">
                <div class="shop_sidebar_area">

                    <!-- ##### Single Widget ##### -->
                    <div class="widget catagory mb-50">
                        <!-- Widget Title -->
                        <h5 class="widget-title mb-30">武器类别</h5>

                        <!--  Catagories  -->
                        <div class="catagories-menu">
                            <ul id="menu-content2" class="menu-content collapse show"
                                style="font-size: 24px;">
                                <!-- Single Item -->
                                <li data-toggle="collapse" data-target="#clothing" class="collapsed"><a
                                        href="#">枪械</a>
                                    <ul class="sub-menu collapse" id="clothing">
                                        <li><a href="../../shop/query?ShowName=枪械&typeName=枪 ">全部</a></li>
                                        <li><a href="../../shop/query?ShowName=突击步枪&typeName=突击步枪 步枪">突击步枪</a></li>
                                        <li><a href="../../shop/query?ShowName=栓动狙击&typeName=栓动狙击 狙 远">栓动狙击</a></li>
                                        <li><a href="../../shop/query?ShowName=射手步枪&typeName=射手步枪 狙 步枪 射手">射手步枪</a></li>
                                        <li><a href="../../shop/query?ShowName=冲锋枪&typeName=冲锋 近战">冲锋枪  </a></li>
                                        <li><a href="../../shop/query?ShowName=霰弹枪&typeName=霰弹 近">霰弹枪  </a></li>
                                        <li><a href="../../shop/query?ShowName=轻机枪&typeName=轻机枪 机枪">轻机枪</a></li>
                                        <li><a href="../../shop/query?ShowName=手枪&typeName=手枪 近">手枪  </a></li>
                                        <li><a href="../../shop/query?ShowName=近战&typeName=近战">近战 </a>
                                        </li>
                                        <li><a href="../../shop/query?ShowName=弹药&typeName=弹">弹药</a></li>
                                        <li><a href="../../shop/query?ShowName=药品&typeName=药 品">药品</a></li>
                                    </ul>
                                </li>

                                <!-- Single Item -->
                                <li data-toggle="collapse" data-target="#shoes"
                                    class="collapsed"><a href="#">空中</a>
                                    <ul class="sub-menu collapse" id="shoes">
                                        <li><a href="../../shop/query?ShowName=空中&typeName=飞 机 拦截 轰炸 歼击">全部</a></li>
                                        <li><a href="../../shop/query?ShowName=拦截机&typeName=拦截机 拦截">拦截机</a></li>
                                        <li><a href="../../shop/query?ShowName=轰炸机&typeName=轰炸机 轰炸 炸">轰炸机</a></li>
                                        <li><a href="../../shop/query?ShowName=舰载机&typeName=舰载机 舰载">舰载机</a></li>
                                        <li><a href="../../shop/query?ShowName=歼击机&typeName=歼击机 歼击">歼击机</a></li>
                                        <li><a href="../../shop/query?ShowName=五代机&typeName=五代 5代">五代机</a></li>
                                        <li><a href="../../shop/query?ShowName=四代机&typeName=四代 4代">四代机</a></li>
                                    </ul>
                                </li>
                                <!-- Single Item -->
                                <li data-toggle="collapse" data-target="#accessories"
                                    class="collapsed"><a href="#">海面</a>
                                    <ul class="sub-menu collapse" id="accessories">
                                        <li><a href="../../shop/query?ShowName=海面&typeName=船 航母 鱼雷 舰 艇">全部</a></li>
                                        <li><a href="../../shop/query?ShowName=航母&typeName=航母 舰载">航母</a></li>
                                        <li><a href="../../shop/query?ShowName=战略舰&typeName=战略舰 护航">战略舰</a></li>
                                        <li><a href="../../shop/query?ShowName=潜艇&typeName=潜艇 深海">潜艇</a></li>
                                        <li><a href="../../shop/query?ShowName=舰载机&typeName=航母  反潜 舰载机">舰载机</a></li>
                                        <li><a href="../../shop/query?ShowName=反航母鱼雷&typeName=反航母 鱼雷 反 雷">反航母鱼雷</a></li>
                                    </ul>
                                </li>
                                <li data-toggle="collapse" data-target="#hm2"
                                    class="collapsed"><a href="#">地面</a>
                                    <ul class="sub-menu collapse" id=hm2>
                                        <li><a href="../../shop/query?ShowName=地面&typeName=坦克 车 炮 防空">全部</a></li>
                                        <li><a href="../../shop/query?ShowName=坦克&typeName=坦克">坦克</a></li>
                                        <li><a href="../../shop/query?ShowName=装甲车&typeName=装甲车 装甲">装甲车</a></li>
                                        <li><a href="../../shop/query?ShowName=炮&typeName=炮 火箭">炮</a></li>
                                        <li><a href="../../shop/query?ShowName=导弹&typeName=导弹 火箭 导弹">导弹</a></li>
                                        <li><a href="../../shop/query?ShowName=相控雷达&typeName=雷达 电子 ">相控雷达</a></li>
                                    </ul>
                                </li>
                                <li data-toggle="collapse" data-target="#other"
                                    class="collapsed"><a href="#">全部</a>
                                    <ul class="sub-menu collapse" id=other>
                                        <li><a href="../../shop/query?typeName=">全部</a></li>
                                        <core:forEach items="${OtherTypes}" var="type">
                                            <li>
                                                <a href="../../shop/query?ShowName=其他&typeName=${type.tname}">${type.tname}</a>
                                            </li>
                                        </core:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>

            <div class="col-12 col-md-8 col-lg-9">
                <div class="shop_grid_product_area">
                    <div class="row" style="  border-bottom: 1px solid gray;margin-bottom: 10px;">
                        <div class="col-12">
                            <div
                                    class="product-topbar d-flex align-items-center justify-content-between">
                                <!-- Total Products -->
                                <div class="total-products">
                                    <p>
                                        <span>${SIZE}</span> 发现产品
                                    </p>
                                </div>
                                <!-- Sorting -->

                            </div>
                        </div>
                    </div>
                    <div class="row">


                        <core:forEach items="${shopPage$datas}" var="good">

                                <!-- Single Product -->
                                <div class="col-12 col-sm-6 col-lg-4">
                                    <div class="single-product-wrapper goodsImgGroup">
                                        <!-- Product Image -->
                                        <div class="product-img goodsImg"
                                             style="background-image: url('../../images/goodspic/${good.goods.gimg}');min-height:320px;">

                                            <!-- Hover Thumb -->
                                                <%--<img class="hover-img" src="img/product-img/product-2.jpg"--%>
                                                <%--alt="">--%>

                                            <!-- Product Badge -->
                                            <core:if test="${good.goods.gisdiscount eq '是'}">
                                                <div class="product-badge offer-badge">
                                            <span><font style="vertical-align: inherit;">
                                                <font style="vertical-align: inherit;">-${100-good.goods.gdiscount}％</font>
                                            </font></span>
                                                </div>
                                            </core:if>

                                            <core:if test="${good.goods.gisnew eq '是'}">
                                                <div class="product-badge new-badge"
                                                     style="top: ${(good.goods.gisdiscount eq '是')?'50':'20'}px">
                                            <span><font style="vertical-align: inherit;">
                                                <font style="vertical-align: inherit;">新品</font>
                                            </font></span>
                                                </div>
                                            </core:if>

                                            <!-- Favourite -->
                                            <div class="product-favourite">
                                                <a href="#" class="favme fa fa-heart"></a>
                                            </div>
                                        </div>

                                        <!-- Product Description -->
                                        <div class="product-description">
                                            <span>${good.type.tname}</span> <a
                                                href="../../shop/queryGoods?gnum=${good.goods.gnum}" target="_blank">
                                            <h6>${good.goods.gname}</h6>
                                        </a>
                                            <p class="product-price">
                                                <core:if test="${good.goods.gisdiscount eq '是'}">
                                                    <span class="old-price">$<fmt:formatNumber value="${good.goods.gprice}" pattern="####,####.000"></fmt:formatNumber></span><font
                                                        color="red">$<fmt:formatNumber value="${((good.goods.gprice*good.goods.gdiscount)/100)}" pattern="###,####.000"></fmt:formatNumber></font>
                                                </core:if>
                                                <core:if test="${not (good.goods.gisdiscount eq '是')}">
                                                    <font style="vertical-align: inherit;"><font
                                                            style="vertical-align: inherit;">$<fmt:formatNumber value="${good.goods.gprice}" pattern="####,####.000"></fmt:formatNumber></font></font>
                                                </core:if>
                                            </p>

                                            <!-- Hover Content -->
                                            <div class="hover-content">
                                                <!-- Add to Cart -->
                                                <div class="add-to-cart-btn">
                                                    <div onclick="AddCart('../../','${good.goods.gnum}',1)" class="btn essence-btn">添加到购物车</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </core:forEach>
                        <core:if test="${empty shopPage}">
                            <div style="   margin-top: 60px; font-size: 20px; width: 100%;text-align: center;">非常抱歉！未能找到你想要的商品！
                            </div>
                        </core:if>
                    </div>
                </div>
                <!-- 分页 -->
                <core:if test="${not empty shopPage}">
                <nav aria-label="navigation">
                <ul class="pagination mt-50 mb-70">
                <li class="page-item"><a  style="padding-top: 10px; " class="page-link"  href="../../../shop/query?pageIndex=${shopPage$lastIndex}"><i
                class="fa fa-angle-left"></i></a></li>
                <core:forEach begin="1" end="${shopPage$maxPage}" var="i" >
                    <li class="page-item"><a class="page-link" ${(shopPage$nowPage == i)?"style='color:blue;'":""} href="../../../shop/query?pageIndex=${i}">${i}</a></li>
                </core:forEach>
                <li class="page-item"><a style="padding-top: 10px; " class="page-link" href="../../../shop/query?pageIndex=${shopPage$nextIndex}"><i
                class="fa fa-angle-right"></i></a></li>
                </ul>
                </nav>
                </core:if>
            </div>
        </div>
    </div>
</section>
<!-- ##### Shop Grid Area End ##### -->

<!-- ##### Footer Area Start ##### -->
<%@ include file="head/bottom.jsp"%>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<!-- ##### Footer Area End ##### -->
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
</script>

</body>
</html>