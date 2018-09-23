<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/8 0008
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%
    path = request.getContextPath();
    basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "shopping/home/head/\">");
%>
<footer class="footer_area clearfix">
    <div class="container">
        <div class="row">
            <!-- Single Widget Area -->
            <div class="col-12 col-md-6">
                <div class="single_widget_area d-flex mb-30">
                    <!-- Logo -->
                    <div class="footer-logo mr-50">
                        <!-- 图标 -->
                        <a href="#"><img src="img/core-img/logo2.png" alt=""></a>
                    </div>
                    <!-- Footer Menu -->
                    <div class="footer_menu">
                        <ul>
                            <li><a href="../../../shop/query">商城</a></li>
                            <li><a href="../../../shop/query">日志</a></li>
                            <li><a href="../../../cart">购物车</a></li>
                        </ul>
                    </div>
                </div>
            </div>


        <div class="row mt-5">
            <div class="col-md-12 text-center">
                <p>
                    Copyright &copy;
                    <script>document.write(new Date().getFullYear());</script>
                    All rights reserved | by Colorlib- More Templates <a
                        href="../../../shop" target="_blank" title="军火网">军火网</a>
                    - Collect from <a href="../../../shop" title="军火网"
                                      target="_blank">军火交易</a> <a href="mailto:1309443685@qq.com?subject=用户建议">[联系我们]</a>

                </p>
            </div>
        </div>

    </div>
</footer>