<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/6 0006
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>
<!DOCTYPE html>
<html lang="en">
<!-- jQuery (Necessary for All JavaScript Plugins) -->
<script src="js/jquery/jquery-2.2.4.min.js"></script>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>暗网 - 军火交易最佳网站</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/logo.jpg">

    <%--<link href="bull/css/demo.css" rel="stylesheet" type="text/css">--%>


    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../styles/util.css">
    <link rel="stylesheet" href="../styles/home.css">
    <style type="text/css">

        .gun {
            transition: all 1s;
            overflow: hidden;
        }

        .gun * .over, .gun .over {
            min-width: 300px;
        }

    </style>
</head>

<body>
<!-- ##### Header Area Start ##### -->
<%@include file="head/head.jsp" %>
<!-- ##### Header Area End ##### -->
<%
     path = request.getContextPath();
     basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>


<!-- ##### Welcome Area Start ##### -->
<section class="welcome_area bg-img background-overlay" style="background-image: url(img/bg-img/m416.jpg);"
         goodsnum="001">
    <div class="container h-100">
        <div class="row h-100 align-items-center">
            <div class="col-12">
                <div class="hero-content">
                    <h6 style="color: white;">M416</h6>
                    <h2 style="color: white;">武器终结者</h2>
                    <a href="../../../shop/query?typeName=M4 M416 M16" target="_blank" class="btn essence-btn">去瞅瞅</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- ##### Welcome Area End ##### -->

<div class="top_catagory_area section-padding-80 clearfix">
    <div class="container">
        <div class="row justify-content-center">
            <!-- Single Catagory -->
            <div class="col-12 col-sm-6 col-md-4">
                <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
                     style="background-image: url(img/bg-img/bg-2.jpg);">
                    <div class="catagory-content">
                        <a href="../../shop/query?ShowName=陆军装备&typeName=枪 炮 坦克 车">陆军</a>
                    </div>
                </div>
            </div>
            <!-- Single Catagory -->
            <div class="col-12 col-sm-6 col-md-4">
                <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
                     style="background-image: url(img/bg-img/bg-3.jpg);">
                    <div class="catagory-content">
                        <a href="../../shop/query?ShowName=空中装备&typeName=飞 机 拦截 轰炸 歼击">空军</a>
                    </div>
                </div>
            </div>
            <!-- Single Catagory -->
            <div class="col-12 col-sm-6 col-md-4">
                <div class="single_catagory_area d-flex align-items-center justify-content-center bg-img"
                     style="background-image: url(img/bg-img/bg-4.jpg);">
                    <div class="catagory-content">
                        <a href="../../shop/query?ShowName=海面装备&typeName=船 航母 鱼雷 舰 艇">海军</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<section class="new_arrivals_area section-padding-80 clearfix">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="section-heading text-center">
                    <h2>热门商品</h2>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <!--热门商品容器-->
                <div class="popular-products-slides owl-carousel" id="hatGoodsGropu">

                    <!-- Single Product -->
                    <div id="temp" class="single-product-wrapper over">
                        <!-- Product Image -->
                        <div class="product-img">
                            <div class="goodsImg" style="background-image: url('../../images/goodspic/$GOODSIMG'); ">
                                <%--<img src="../../images/goodspic/$GOODSIMG" style="height: 250px;" alt="">--%>
                            </div>
                            <!-- Hover Thumb -->
                            <%--<img class="hover-img"src="../../images/goodspic/$GOODSIMG" style="height: 250px;"  alt="">--%>
                            <!-- Favourite -->
                            $F
                            <div class="product-favourite">
                                <a href="#" class="favme fa fa-heart"></a>
                            </div>
                        </div>
                        <!-- Product Description -->
                        <div class="product-description over">
                            <span>$TYPENAME</span>
                            <a href="../../shop/queryGoods?gnum=$GNUM" target="_blank">
                                <h6>$GOODSNAME</h6>
                            </a>
                            <p class="product-price">
                                价格:$PRICE</p>

                            <!-- Hover Content -->
                            <div class="hover-content">
                                <!-- Add to Cart -->
                                <div class="add-to-cart-btn">
                                    <div onclick="AddCart('../../','$GNUM',1)" class="btn essence-btn">添加到购物车</div>
                                </div>
                            </div>
                        </div>
                    </div>



                </div>


            </div>
        </div>
    </div>
</section>
<%@ include file="head/bottom.jsp"%>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/\">");
%>

<%--<section id="getintouch" class="animated">--%>
    <%--<div class="container" style="border-bottom: 0;">--%>
        <%--<h1>--%>
            <%--<span>标题</span>--%>
        <%--</h1>--%>

    <%--</div>--%>
    <%--<div class="container">--%>
        <%--<br/>--%>

        <%--${bull.btitle}--%>
        <%--2--%>

    <%--</div>--%>
<%--</section>--%>
<%--<script src="bull/js/jquery-1.10.2.min.js" type="text/javascript"></script>--%>
<%--<!--Framework-->--%>
<%--<script src="bull/js/jquery-1.10.2.min.js" type="text/javascript"></script>--%>
<%--<script src="bull/js/jquery-ui.js" type="text/javascript"></script>--%>
<%--<!--End Framework-->--%>
<%--<script src="bull/js/jquery.ffform.js" type="text/javascript"></script>--%>
<script src="js/popper.min.js"></script>
<!-- Bootstrap js -->
<script src="js/bootstrap.min.js"></script>


<!-- Plugins js -->
<script src="js/plugins.js"></script>
<!-- Classy Nav js -->
<script src="js/classy-nav.min.js"></script>


<script src="../script/ShopAJAX.js"></script>

<!-- Active js -->
<script src="js/active.js"></script>
<!-- Active js -->
<script src="../../script/util.js"></script>
<script type="text/javascript">
    $(function () {
        queryHot()
        LBT();
        flushCartView("../../../cart/get")
    });

    function queryHot() {
        console.log("请求")
        $.get("../../../shopgoods/hotgoods", function (data) {

            if (data) {
                var hot = data.Hot;
                var good = null;
                var hotGroup = $(".owl-stage")[0];
                //   hotGroup.innerHTML="";
                //新品
                var n = " <div class=\"product-badge new-badge\" style=\"top:$TOPpx\" >\n" +
                    "                                <span><font style=\"vertical-align: inherit;\" ><font\n" +
                    "                                        style=\"vertical-align: inherit;\">$T</font></font></span>\n" +
                    "                            </div>";
                //折扣
                var d = "<div class=\"product-badge offer-badge\" style=\"top:$TOPpx\">\n" +
                    "                                    <span><font style=\"vertical-align: inherit;\"><font style=\"vertical-align: inherit;\">$Z</font></font></span>\n" +
                    "                                </div>"
                var gtxt = document.getElementById("temp").parentElement.innerHTML;
                document.getElementById("temp").parentElement.remove();

                console.log(hot)
                for (var i = 0; i < hot.length; i++) {
                    var news = n;
                    var dis = d;
                    good = hot[i].Good;
                    var txt = gtxt;
                    txt = txt.replace("$GOODSNAME", good.goods.gname).replace("$TYPENAME", good.type.tname)
                        .replace("$GOODSIMG", good.goods.gimg).replace("$GOODSIMG", good.goods.gimg)

                    var T = "";
                    var M = "";
                    var Z = 0
                    if (good.goods.gisnew == "是") {
                        T += "新品"
                    }
                    if (good.goods.gisrecom == "是") {
                        T += " 推荐"
                    }
                    if (good.goods.gisdiscount == "是") {
                        Z = good.goods.gdiscount;

                        var  p =good.goods.gprice*Z/100;
                        console.log(p,Z);
                        txt = txt.replace("$PRICE","<font><s>$"+good.goods.gprice+"</s></font> <font color='red' size='+1'>$"+p+"</font>" )
                    }else{
                        txt = txt.replace("$PRICE", "$"+good.goods.gprice)
                    }
                    if(T){
                        news = news.replace("$TOP",20).replace("$T",T)
                        M+=news;
                        if(Z && (100-Z)>0) {
                            dis = dis.replace("$TOP", 50).replace("$Z","-"+(100-Z)+"%")
                            console.log(Z+"%")
                            M+=dis;
                        }
                    }else if(Z && (100-Z)>0){
                        dis = dis.replace("$TOP", 50).replace("$Z","-"+(100-Z)+"%")
                        console.log(Z+"%")
                        M+=dis;
                    }
                    if(M){
                        txt =    txt.replace("$F",M)
                    }
                    txt = txt.replace("$GNUM",good.goods.gnum).replace("$GNUM",good.goods.gnum);
                    var div = document.createElement("div");
                    div.className = "owl-item gun";
                    $(div).attr({"style": "width: 300px; margin-right: 30px;height:500px"})
                    div.innerHTML = txt;
                    hotGroup.append(div);
                    console.log(div)
                }
                $(hotGroup).css({"width": 330 * hot.length + "px", "transform": " translate3d(-330px, 0px, 0px)"})

                // hotGroup.innerHTML = strs;
            }
        })
    }

    function LBT() {
        //轮播图
        setTimeout(function () {
            var list = $(".gun");
            if (list.length > 0) {
                var p = list[0].parentElement;
                var f = list[0];//获取第1个
                $(f).css({"width": "0px", "margin-right": "0px"})

                setTimeout(function () {
                    f.remove();
                    $(f).css({"width": "300px", "margin-right": "30px"})
                    p.appendChild(f)
                    LBT();
                }, 1000)

            }
        }, 3000)
    }
</script>
<%@include file="../../tools/msg.jsp"%>
</body>

</html>
