<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/2 0002
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <script type="text/javascript" src="./admin/user/script/usermanager.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-修改商品类型
    </div>
    <div class="btn-group" style="text-align: right; display: block; padding-right: 10px">
        <a class="btn-submit" href="admin/type/typeManager.jsp">管理主页</a>
        <a class="btn-submit" href="/admin/type/addType.jsp">刷新</a>
    </div>
    <hr/>
    <form action="goodstype/update" method="post">
        <table style="width: 50%" align="center">
            <tr>
                <td>类型名:</td>
                <td>
                    <input type="text" required name="t_name" value="${goods.tname}">
                </td>
            </tr>
            <tr>
                <td>类型备注:</td>
                <td>
                    <textarea name="t_remark" style="width: 100%;resize: none;" rows="5">${goods.tremark}</textarea>
                </td>
            </tr>
        </table>
        <div>
            <div class="btn-group" style="text-align: center;border-top: 1px solid black;padding-top: 10px">
                <button class="btn-submit" type="submit">修改</button>
                <button class="btn" type="reset">重置</button>
            </div>
        </div>
    </form>
</div>
<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
