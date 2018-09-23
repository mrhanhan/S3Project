<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/19 0019
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    out.println("<base href=\""+basePath+"\">");
%>
<%@ include file="../tools/includeLib.jsp"%>
<html>
<script type="text/javascript" src="./script/jquery.min.js"></script>
<head>
    <link rel="stylesheet" href="./styles/util.css">
    <link rel="stylesheet" href="./styles/color.css">
    <title>首页</title>
    <link rel="stylesheet" href="./admin/styles/home.css">
    <link rel="stylesheet" href="./admin/styles/left.css">
    <link rel="stylesheet" href="./admin/styles/dialog.css">

    <link rel="stylesheet" href="./styles/form.css">
    <script type="text/javascript" src="./script/util.js"></script>
    <script src="./admin/script/left.js"></script>
</head>
<body>
<%@include file="dialogs.jsp"%>
    <!--用户信息区-->
    <div class="head">
        <!--包含用户信息-->
        <%@include file="msg/userhead.jsp"%>
    </div>
    <!--底部，功能区/展示区-->
    <div class="body">
        <!--功能区-->
        <div class="left">
            <%@include file="msg/left.jsp"%>
        </div>
        <!--展示区-->
        <div class="right" >
            <iframe style="margin: 0px;padding: 0px; min-height: 700px;" id="body" src="./admin/home_back.html" name="CONTENTBODY" height="100%"  frameborder="0" width="100%" scrolling="no" >
            </iframe>
        </div>
    </div>
<%@include file="msg/bottom.jsp"%>
<%@include file="../tools/msg.jsp"%>
</body>
<script type="text/javascript">
    var src="";
    var  doc = null;
    function iFrameSize() {
        var a =  $("#body")[0];
        var d = a.contentDocument;
        if(!(d == doc)){
            if(d.body && d.body.scrollHeight) {
                doc = d;
                var h = d.body.scrollHeight;

                console.log(d.body)
                var h1 = screen.availHeight;
                console.log(h)
                $(a).css({"transition": "all 0ms", "height": h  });
            }
        }
    }
    $(setInterval(iFrameSize,10))

</script>
</html>
