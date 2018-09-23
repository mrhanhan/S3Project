<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 14:58
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
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
<base target="CONTENTBODY">
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-添加用户
    </div>
    <div class="btn-group" style="text-align: right; display: inline-block;float: right; padding-right: 10px">
        <a class="btn-submit" href="user/query">管理主页</a>
        <a class="btn-submit" href="./admin/user/addUser.jsp">刷新</a>
    </div>

    <hr class="hr" style="margin-top:45px;"/>
    <div>
        <form action="user/add" enctype="multipart/form-data" method="post">
        <table align="center" width="50%" border="0px">

            <tr>
                <td>头像:</td>
                <td width="60%">
                    <img src="./images/uiface2.png" onclick="$('#id_s_img')[0].click()" id="id_show_img"  width="80" height="80" style="border-radius: 10px;"/>
                    <input style="width: 100%" type="file" hidden onchange="reViewInputFile(this,'#id_show_img')" accept="image/png,image/jpeg,image/gif,image/jpg" name="u_img" id="id_s_img"></td>
                <td>点击图片选择头像</td>
            </tr>
            
            <tr>
                <td>名称:</td>
                <td width="60%"><input style="width: 100%" required pattern=".{2,6}" type="text" name="u_name" id="id_u_name"></td>
                <td>2-6为字符</td>
            </tr>
            <td>权限:</td>
            <td><select style="width: 100%" name="u_auto" id="id_u_auto">
                <option value="员工">员工</option>
                <option value="经理">经理</option>
            </select></td>
            <td></td>
            </tr>
            <tr>
                <td>账号:</td>
                <td><input style="width: 100%" type="text" pattern="\d{11}" name="u_account" id="id_u_acc"></td>
                <td>11位数字</td>
            </tr>
            <tr>
                <td>密码:</td>
                <td><input style="width: 100%" type="password" pattern="\w{6,16}" name="u_pwd" id="id_u_pwd"></td>
                <td>6-16为字符[数字,字母,下划线]</td>
            </tr>
            <tr>
                <td>确认密码:</td>
                <td><input style="width: 100%" type="password" name="u_pwds" id="id_u_repwd"></td>
                <td>请输入相同密码</td>
            </tr>
            <tr>
                <td>安全协议:</td>
                <td>
                    <textarea style="resize: none;width: 100%" rows="6" readonly><%@include file="../../staticfile/userProtocl.jsp"%></textarea><br/>
                    <input type="checkbox" checked>我同意此协议
                </td>
                <td>请阅读安全协议</td>
            </tr>
        </table>
            <hr/>
            <div class="btn-group" style=" width: 30%; padding-right: 10%;">
                <button type="submit" class="btn-submit" style="width: 45%;float: left;">提交</button>
                <button type="reset" class="btn"  style="width: 45%;float:right">重置</button>
            </div>
        </form>
    </div>
    </div>
</body>
<%@ include file="../../tools/msg.jsp" %>
</html>
