<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>${type.tname}</title>
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
		#productSize{
            font-size: 20px;
            border: none;
            border-bottom: 1px solid #c1a9a9;
            text-align: left;
            padding-left: 10px;
            display: inline;
            width: auto;
            width: 170px;
        }
        .good{
            height: 600px;
            width: 100%;
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

<!-- ##### Right Side Cart Area ##### -->

<!-- ##### Single Product Details Area Start ##### -->
<section class="single_product_details_area d-flex align-items-center" >

    <!-- Single Product Thumb -->
    <div class="single_product_thumb clearfix">
        <div class="product_thumbnail_slides owl-carousel">
            <div class="goodsImg good" style="border:none;background-image: url('../../../images/goodspic/${good.gimg}');display: inline-block"></div>
           <core:forEach items="${goodsimgs}" var="img">
            <div class="goodsImg good" style="border:none;background-image: url('../../../images/goodspic/${img.imessage}');display: inline-block"></div>
           </core:forEach>
            <core:if test="${empty goodsimgs }">
                <div class="goodsImg good" style="background-image: url('../../../images/goodspic/${good.gimg}');display: inline-block"></div>
                <div class="goodsImg good" style="background-image: url('../../../images/goodspic/${good.gimg}');display: inline-block"></div>

            </core:if>
                <%--<img src="../../../images/goodspic/${good.gimg}" alt=""> <img--%>
                <%--src="../../../images/goodspic/${good.gimg}" alt=""> <img--%>
                <%--src="i../../../images/goodspic/${good.gimg}" alt="">--%>
        </div>
    </div>

    <!-- Single Product Description -->
    <div class="single_product_desc clearfix">
        <a href="../../../shop/query?typeName=${type.tname}"> <span>${type.tname} </span>
        <h2>${good.gname}</h2>
    </a>
        <p class="product-price">
            <core:if test="${good.gisdiscount eq '是'}">
                <span class="old-price">$<fmt:formatNumber value="${good.gprice}" pattern="####,####.000"></fmt:formatNumber></span><font
                    color="red"><i>$<fmt:formatNumber value="${((good.gprice*good.gdiscount)/100)}" pattern="####,####.000"></fmt:formatNumber></i></font>
            </core:if>
            <core:if test="${not (good.gisdiscount eq '是')}">
                <font style="vertical-align: inherit;"><font
                        style="vertical-align: inherit;">$<fmt:formatNumber value="${good.gprice}" pattern="####,####.000"></fmt:formatNumber></font></font>
            </core:if>
        </p>
        <p class="product-desc">${good.gremark}</p>
        <!-- Form -->
        <form class="cart-form clearfix" method="post" onsubmit="return Add()">
            <!-- Select Box -->
            <div class="select-box d-flex mt-50 mb-30">
                <input type="number" name="select" id="productSize" min="1" value="1" class="mr-5" max="99" placeholder="请输入购买数量">
                </input>
            </div>
            <!-- Cart & Favourite Box -->
            <div class="cart-fav-box d-flex align-items-center">
                <!-- Cart -->
                <button type="submit" name="addtocart"
                        class="btn essence-btn">添加到购物车
                </button>
                <!-- Favourite -->
                <div class="product-favourite ml-4">
                </div>
            </div>
        </form>
    </div>
</section>
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
        if(size == "0" || !size){
            Toast("亲输入添加购物车的数据",3000,"top");
            $("#productSize").focus();
        }else{
            AddCart("../../../","${good.gnum}",size);
        }
        return false;
    }
</script>


</body>
</html>