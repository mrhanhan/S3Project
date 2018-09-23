<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        |-添加商品
    </div>
    <div class="btn-group" style="text-align: right; display: inline-block;float: right; padding-right: 10px">
        <a class="btn-submit" href="goods/query">管理主页</a>
        <a class="btn-submit" href="goods/update?gnum=${goods.gnum}">刷新</a>
    </div>

    <hr class="hr" style="margin-top:45px;"/>
    <div>
        <form action="goods/update" enctype="multipart/form-data" method="post">
            <input type="hidden" name="g_id" value="${goods.id}">
            <table align="center" width="60%" border="0px" height="550px">
                <tr>
                    <td width="30%" colspan="2" style="overflow: hidden;text-align: center;">
                        <img src="./images/goodspic/${goods.gimg}" onclick="$('#id_s_img')[0].click()" id="id_show_img"
                             style="border-radius: 10px; max-width: 350px; max-height: 250px;"/>
                        <input style="width: 80%" type="file" hidden onchange="reViewInputFile(this,'#id_show_img')"
                               accept="image/png,image/jpeg,image/gif,image/jpg" name="u_img" id="id_s_img">
                        <div>点图图片选择商品图片</div>
                    </td>
                    <td>商品封页图片</td>
                </tr>
                <tr>
                    <td width="10">商品编码:</td>
                    <td width="60%"><input style="width: 100%" required readonly value="${goods.gnum}" type="text"
                                           name="g_num"></td>
                    <td>商品的编号！固定</td>
                </tr>
                <tr>
                    <td>商品名称:</td>
                    <td><input style="width: 100%" required pattern=".{1,20}" value="${goods.gname}" type="text" name="g_name">
                    </td>
                    <td>商品的名称!1-20个字符</td>
                </tr>
                <td>类型:</td>
                <td><select style="width: 100%" name="t_num">
                    <core:forEach items="${types}" var="i">
                        <option value="${i.tnum}" ${goods.tnum eq i.tnum?'selected':''}>${i.tname}</option>
                    </core:forEach>
                </select></td>
                <td></td>
                </tr>
                <tr>
                    <td>商品价格:</td>
                    <td><input style="width: 100%" type="number" min="0.00"  value="<fmt:formatNumber value="${goods.gdiscount}" pattern="#.000"></fmt:formatNumber>" max="99999999999" required name="g_price" id=""></td>
                    <td>请输入数字</td>
                </tr>
                <tr>
                    <td>商品折扣:</td>
                    <td><input style="width: 100%" type="number" min="1"  max="99" required name="g_discount"></td>
                    <td>请输入数字</td>
                </tr>
                <tr>
                    <td>状态:</td>
                    <td><input type="radio" name="g_state" ${goods.gstate eq '上架'?'checked':''} value="上架">上架
                        <input type="radio" name="g_state" ${goods.gstate eq '上架'?'':'checked'} value="下架">下架
                    </td>
                    <td></td>
                <tr>
                    <td>推荐:</td>
                    <td><input type="radio" name="g_isrecom" ${goods.gisrecom eq '是'?'checked':''} value="是">推荐
                        <input type="radio" name="g_isrecom" ${goods.gisrecom eq '否'?'checked':''}  value="否">不推荐
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>折扣:</td>
                    <td><input type="radio" name="g_isdiscount" ${goods.gisdiscount eq '是'?'checked':''} value="是">折扣
                        <input type="radio" name="g_isdiscount" ${goods.gisdiscount eq '否'?'checked':''} value="否">不折扣
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>新品:</td>
                    <td><input type="radio" name="g_isnew" ${goods.gisnew eq '是'?'checked':''} value="是">新品
                        <input type="radio" name="g_isnew" ${goods.gisnew eq '否'?'checked':''} value="否">不新品
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>备注:</td>
                    <td><textarea name="g_remark" style="resize: none;width: 100%;" rows="5" maxlength="128">${goods.gremark}</textarea></td>
                    <td></td>
                </tr>
            </table>
            <hr/>
            <div class="btn-group" style=" width: 30%; padding-right: 10%;">
                <button type="submit" class="btn-submit" style="width: 45%;float: left;">提交</button>
                <button type="reset" class="btn" style="width: 45%;float:right">重置</button>
            </div>
        </form>
    </div>
</div>
<%@ include file="../../tools/msg.jsp" %>
</body>
</html>
