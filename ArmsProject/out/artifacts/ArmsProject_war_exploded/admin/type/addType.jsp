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
    <script type="text/javascript" src="/script/jquery.min.js"></script>
    <link rel="stylesheet" href="/styles/util.css">
    <link rel="stylesheet" href="/styles/color.css">
    <link rel="stylesheet" href="/styles/page.css">
    <title>首页</title>
    <link rel="stylesheet" href="/admin/styles/dialog.css">
    <link rel="stylesheet" href="/styles/form.css">
    <script type="text/javascript" src="/script/util.js"></script>
    <script type="text/javascript" src="/admin/user/script/usermanager.js"></script>
    <link rel="stylesheet" href="/admin/user/styles/userinfo.css">
</head>
<body>
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-添加商品类型
    </div>
    <div class="btn-group" style="text-align: right; display: block; padding-right: 10px">
        <a class="btn-submit" href="goodstype/query">管理主页</a>
        <a class="btn-submit" href="goodstype/add">刷新</a>

    </div>
    <hr/>
    <!--内容部分-->
    <script>
        function yz(f) {
            $.get("goodstype/queryName?typename="+$("#name").val(), function (data) {
                console.log(data);
                var msg = JSON.parse(data)
                if (msg) {
                    Toast("名称以及存在！", 3000, "top");
                    return false;
                }
                return true;
            })
        }

    </script>
    <form action="goodstype/add" method="post" onsubmit="return yz()">
        <table style="width: 50%" align="center">
            <tr>
                <td>类型名:</td>
                <td>
                    <input type="text" required name="n_t_name" oninput="yz" id="name" value="${param.n_t_name}">
                </td>
                <!--验证名字是否可以重复-->
            </tr>
            <tr>
                <td>类型备注:</td>
                <td>
                    <textarea name="n_tremark" style="width: 100%;resize: none;" rows="5"
                              maxlength="128">${param.n_tremark}</textarea>
                </td>
            </tr>
        </table>
        <div>
            <div class="btn-group" style="text-align: center;border-top: 1px solid black;padding-top: 10px">
                <button class="btn-submit" type="submit">添加</button>
                <button class="btn" type="reset">重置</button>
            </div>
        </div>
    </form>
    <!--内容部分-->
</div>
<%@include file="../../tools/msg.jsp" %>
</body>

</html>
