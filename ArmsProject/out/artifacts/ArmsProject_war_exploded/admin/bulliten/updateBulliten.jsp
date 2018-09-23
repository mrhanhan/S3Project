<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 14:58
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
    <script type="text/javascript" src="./script/jquery.min.js"></script>
    <link rel="stylesheet" href="./styles/util.css">
    <link rel="stylesheet" href="./styles/color.css">
    <link rel="stylesheet" href="./styles/page.css">
    <title>首页</title>
    <link rel="stylesheet" href="./admin/styles/dialog.css">
    <link rel="stylesheet" href="./styles/form.css">
    <script type="text/javascript" src="./script/util.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
<base target="CONTENTBODY"/>
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-修改公告
    </div>
    <div class="btn-group" style="text-align: right; display: inline-block;float: right; padding-right: 10px">
        <a class="btn-submit" href="bulliten/query">管理主页</a>
        <a class="btn-submit" href="bulliten/update?bnum=${b.bnum}">刷新</a>
    </div>

    <hr class="hr" style="margin-top:45px;"/>
    
    <div>
        <form action="bulliten/update"  method="post">
         <input type="hidden" name="b_id" value="${bull.id}">
         <input type="hidden" name="b_num" value="${bull.bnum}">
            <table style="width: 50%" align="center">
            
                <tr>
                    <td>公告标题：</td>
                    <td>
                        <input type="text" required name="b_title" value="${bull.btitle}">
                    </td>
                </tr>
                <tr>
                    <td>公共内容:</td>
                    <td>
                        <textarea name="b_content" style="width: 100%;resize: none;" rows="5">${bull.bcontent} </textarea>
                    </td>
                 
            </table>
            <div>
                <div class="btn-group" style="text-align: center;border-top: 1px solid black;padding-top: 10px">
                    <button class="btn-submit" type="submit">编辑</button>
                    <button class="btn" type="reset">重置</button>
                </div>
            </div>
        </form>
    </div>

</div>
<%@ include file="../../tools/msg.jsp" %>
</body>
</html>
