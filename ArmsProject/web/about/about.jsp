<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/9 0009
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    out.println("<base href=\"" + basePath + "about/\">");
%>
<!DOCTYPE html>
<!-- saved from url=(0059)http://www.jq22.com/demo/jQueryMoban201705092253/about.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title></title>

    <link rel="stylesheet" type="text/css" href="./bootstrap-3.3.4.css">

    <link rel="stylesheet" href="./bootstrap-theme.min.css">

    <link rel="stylesheet" href="./style.css">

    <link rel="stylesheet" type="text/css" href="./font-awesome.4.6.0.css">
    <link rel="stylesheet" href="./ionicons.min.css">

    <link rel="stylesheet" href="./flexslider.css">

    <link rel="stylesheet" href="./owl.carousel.css">

    <link rel="stylesheet" href="./magnific-popup.css">
    <link rel="stylesheet" href="../shopping/home/css/core-style.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../styles/util.css">
    <link rel="stylesheet" href="../shopping/styles/home.css">
    <style type="text/css">

    </style>

    <!--[if lt IE 9]>

    <![endif]-->
</head>
<body class="  pace-done"><div class="pace  pace-inactive"><div class="pace-progress" data-progress-text="100%" data-progress="99" style="transform: translate3d(100%, 0px, 0px);">
    <div class="pace-progress-inner"></div>
</div>
    <div class="pace-activity"></div></div>

<div id="myloader" style="display: none;">
    <div class="loader">
        <div class="grid">
            <div class="cube cube1"></div>
            <div class="cube cube2"></div>
            <div class="cube cube3"></div>
            <div class="cube cube4"></div>
            <div class="cube cube5"></div>
            <div class="cube cube6"></div>
            <div class="cube cube7"></div>
            <div class="cube cube8"></div>
            <div class="cube cube9"></div>
        </div>
    </div>
</div>

<div id="main-wrap">

    <div id="page-content" class="header-static">

        <div id="page-header" class="secondary-background">
            <div class="container">
                <div class="row no-margin">
                    <div class="text">
                        <h1 class="white">‘包公头’项目组</h1>
                        <ul class="breadcrumb white">
                            <li><a href="../shop">主页</a></li>
                            <li>关于我们</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <div id="home-wrap" class="content-section fullpage-wrap">
            <div class="container">

                <div class="row no-margin padding-lg">
                    <div class="col-md-6 padding-leftright-null">
                        <div class="text padding-topbottom-null">
                            <h2 class="margin-bottom-null left"><br>简介.</h2>
                            <div class="padding-onlytop-sm">
                                <p class="margin-bottom margin-md-bottom-null">
                                    包工头是指工程承包商，能把一项工程成本控制在工程建设方发包价以内就可以挣到钱!。包工头会把工作包揽过来，招人作业，他自己当头，是特殊历史条件下的特殊“产物”
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 padding-leftright-null padding-md-top">
                        <div class="text padding-topbottom-null">
                            <picture>
                                <img src="./about.jpg" class="img-responsive shadow" alt="">
                            </picture>
                        </div>
                    </div>
                </div>

            </div>

            <div class="light-background">
                <div class="container">
                    <div class="row no-margin padding-lg">
                        <div class="col-md-4 padding-leftright-null">
                            <div class="text padding-topbottom-null padding-md-bottom">
                                <i class="material-icons color service">card_travel</i>
                                <h3 class="margin-bottom-extrasmall">经验</h3>
                                <p class="margin-bottom-null">项目经验相当丰富！做过大大小小无数项目！政府和国家给予非常高的评价和重视！</p>
                            </div>
                        </div>
                        <div class="col-md-4 padding-leftright-null">
                            <div class="text padding-topbottom-null padding-md-bottom">
                                <i class="material-icons color service">trending_up</i>
                                <h3 class="margin-bottom-extrasmall">目标</h3>
                                <p class="margin-bottom-null"> 如果你生活没有目标,最终失败,问问自己是否真的是一个失败。如果你没有到达你想去的地方,你只会失败 - 但如果你没有目的地,那就没有失败。</p>
                            </div>
                        </div>
                        <div class="col-md-4 padding-leftright-null">
                            <div class="text padding-topbottom-null">
                                <i class="material-icons color service">lightbulb_outline</i>
                                <h3 class="margin-bottom-extrasmall">技术</h3>
                                <p class="margin-bottom-null">包工头项目组成员，精通各种Web开发语言！各种Web开发框架！熟悉各种开发套路！</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="container">
                <div class="row no-margin padding-lg">
                    <div class="col-md-12 padding-leftright-null">
                        <div class="text text-center padding-topbottom-null">
                            <h3 class="big">我们的团队.</h3>
                        </div>
                    </div>

                    <div class="row team padding-onlytop-md">
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./hh.png" alt="" height="176" class="img-responsive">
                                <h3>韩浩</h3>
                                <span>CEO</span>

                            </div>
                        </div>
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./staff2.jpg" alt="" class="img-responsive">
                                <h3>益成</h3>
                                <span>CTO</span>

                            </div>
                        </div>
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./staff3.jpg" alt="" class="img-responsive">
                                <h3>李强</h3>
                                <span>CMO</span>

                            </div>
                        </div>

                    </div>
                    <div class="row team padding-onlytop-md">
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./staff4.jpg" alt="" class="img-responsive">
                                <h3>润发</h3>
                                <span>Account</span>

                            </div>
                        </div>
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./staff5.jpg" alt="" class="img-responsive">
                                <h3>志祥</h3>
                                <span>Head of Account</span>

                            </div>
                        </div>
                        <div class="col-xs-6 col-md-4 padding-leftright-null">
                            <div class="person">
                                <img src="./zx.jpg" height="176px" alt="" class="img-responsive">
                                <h3>卓超</h3>
                                <span>Web Developer</span>

                            </div>
                        </div>


                </div>
            </div>

        </div>
    </div>

</div>
<%@include file="./../shopping/home/head/bottom.jsp"%>

<script src="./jquery.min.js"></script>
<script src="./bootstrap.min.js"></script>
<script src="./jquery.flexslider-min.js"></script>
<script src="./owl.carousel.min.js"></script>
<script src="./isotope.min.js"></script>
<script src="./jquery.magnific-popup.min.js"></script>

<script src="./jquery.scrollTo.min.js"></script>
<script src="./smooth.scroll.min.js"></script>
<script src="./jquery.appear.js"></script>
<script src="./jquery.countTo1.js"></script>
<script src="./jquery.scrolly.js"></script>
<script src="./plugins-scroll.js"></script>
<script src="./imagesloaded.min.js"></script>
<script src="./pace.min.js"></script>
<script src="./main.js"></script>


</body></html>
