<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 12:10
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
    <script type="text/javascript" src="./admin/user/script/usermanager.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
    <%--<link rel="prefetch" href="goodstype/query?pageindex=0">--%>
    <%--<link rel="prefetch" href="goodstype/query?pageindex=${typePage$lastIndex}">--%>


    <%--<core:forEach begin="1" end="${typePage$maxPage}" varStatus="i">--%>
    <%--<link rel="prefetch" href="goodstype/query?pageindex=${i.count}">--%>
    <%--</core:forEach>--%>
    <%--<link rel="prefetch" href="goodstype/query?pageindex=${typePage$nextIndex}">--%>
    <%--<link rel="prefetch" href="goodstype/query?pageindex=${typePage$maxPage}">--%>

</head>
<body>
<base target="CONTENTBODY">
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-商品类型管理
    </div>
    <!--功能按钮-->
    <div class="btn-group" style="text-align: right; display: inline-block;float: right;">
        <div class="btn-submit dialog-btn" bind="#addType">添加类型(*)</div>
        <div class="btn-submit dialog-btn" bind="#updType">修改类型</div>
        |
        <script type="text/javascript">
            function update(a) {
                console.log($(".selected")[0])
                u = $(".selected")[0]

                if(u){
                    a.href="goodstype/update?typenum="+u.children[1].innerText;
                    return true;
                }else{
                    Toast("请选择需要修改的类型")
                    return false;
                }
            }
        </script>
        <a class="btn-submit dialog-btn" href="goodstype/add">添加类型</a>
        <a class="btn-submit dialog-btn" href="goodstype/update" onclick=" return update(this)">修改类型</a>
        |
        <a href="goodstype/query" class="btn-submit">刷新</a>
    </div>
    <!--模糊查询-->
    <div class="input-gropu">
        <form action="goodstype/query">
            <input type="text" name="keyword" placeholder="关键字" value="${key}" style="width:260px"/>
            <button class="btn">查询</button>
            <select name="order">
                <option value="t_num" >编号</option>
                <option value="create_time" ${order eq 'create_time'?'selected':''}>创建时间</option>
            </select>
            <button class="btn">排序</button>
        </form>
    </div>
    <!--信息-->
    <div class="userinfo-group">
        <table cellspacing="0">
            <tr class="top">
                <th>序号</th>
                <th>编号</th>
                <th>类型名称</th>
                <th>备注</th>
                <th></th>
            </tr>
            <!--遍历数据-->
            <core:forEach items="${typePage$datas}" var="t" varStatus="i">

                <tr class="select-tr-${((i.index%2) eq 0) ?"1":"2"} userrow">
                    <td>${i.count}</td>
                    <td>${t.tnum}</td>
                    <td>${t.tname}</td>
                    <td style="max-width: 60%;overflow: -webkit-marquee;">${t.tremark}</td>
                    <td style="text-align: center">
                        <button class="btn1"><a  style="display:inline-block;color: inherit;text-decoration: none;" href="goodstype/update?typenum=${t.tnum}">修改</a></button>
                        <button class="btn2"><a href="shop/query?typeName=${t.tname}" target="_blank">查看商品</a></button>
                    </td>
                </tr>
            </core:forEach>
            <!--遍历空白-->
            <core:forEach begin="${typePage$dataSize}" end="21" varStatus="i">
                <tr class="select-tr-${((i.index%2) eq 0) ?"1":"2"}" style="height:25px;">
                    <td>&nbsp;</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                    </td>
                </tr>
            </core:forEach>
        </table>

        <div class="page-group">
            <a class="btn" href="goodstype/query?pageindex=0">首页</a>
            <a class="btn" href="goodstype/query?pageindex=${typePage$lastIndex}">上一页</a>
            <div class="pages">
                <core:forEach begin="1" end="${typePage$maxPage}" varStatus="i">
                    <a class="page ${i.count eq typePage$nowPage?'select':''}"
                       href="goodstype/query?pageindex=${i.count}">${i.count}</a>
                </core:forEach>
            </div>
            <a class="btn" href="goodstype/query?pageindex=${typePage$nextIndex}">下一页</a>
            <a class="btn" href="goodstype/query?pageindex=${typePage$maxPage}">尾页</a>
        </div>
    </div>
</div>
<div class="dialog" width="50%" left="center" top="20%" id="addType">
    <div class="dialog-body">
        <div class="title">
            添加商品类型
        </div>
        <form>
            <table style="width: 70%">
                <tr>
                    <td>类型名:</td>
                    <td>
                        <input type="text" required name="n_t_name">
                    </td>
                </tr>
                <tr>
                    <td>类型备注:</td>
                    <td>
                        <textarea name="n_tremark" style="width: 100%;resize: none;" rows="5"></textarea>
                    </td>
                </tr>
            </table>
            <div>
                <div class="btn-group" style="text-align: right;border-top: 1px solid white;">
                    <button class="btn-submit" type="submit">添加</button>
                    <button class="btn" type="reset">重置</button>
                    <button class="btn-close dialog-btn" bind="#addType" type="button">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="dialog" width="50%" left="center" top="20%" id="updType">
    <div class="dialog-body">
        <div class="title">
            修改商品类型
        </div>
        <form>
            <table style="width: 70%">
                <tr>
                    <td>类型名:</td>
                    <td>
                        <input type="text" required name="n_t_name">
                    </td>
                </tr>
                <tr>
                    <td>类型备注:</td>
                    <td>
                        <textarea name="n_tremark" style="width: 100%;resize: none;" rows="5"></textarea>
                    </td>
                </tr>
            </table>
            <div>
                <div class="btn-group" style="text-align: right;border-top: 1px solid white;">
                    <button class="btn-submit" type="submit">修改</button>
                    <button class="btn" type="reset">重置</button>
                    <button class="btn-close dialog-btn" type="button" bind="#updType">关闭</button>
                </div>
            </div>
        </form>
    </div>
</div>
<%@ include file="../../tools/msg.jsp"%>
</body>
</html>

