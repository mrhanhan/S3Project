<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 12:10
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
    <script type="text/javascript" src="./admin/user/script/usermanager.js"></script>
    <link rel="stylesheet" href="./admin/user/styles/userinfo.css">
</head>
<body>
<base target="CONTENTBODY">
<div class="usermanager">
    <!--标题-->
    <div class="title">
        |-商品管理
    </div>
    <!--功能按钮-->
    <div class="btn-group" style="text-align: right; display: inline-block;float: right;">
        <a class="btn-submit" href="goods/add">添加商品</a>
        <script type="text/javascript">
            function update(a) {
                console.log($(".selected")[0])
                u = $(".selected")[0]

                if(u){
                    a.href="goods/update?gnum="+u.children[1].innerText;
                    return true;
                }else{
                    Toast("请选择需要修改的商品")
                    return false;
                }
            }
        </script>
        |
        <a class="btn-submit" href="goods/queryState?state=上架">上架</a>
        <a class="btn-submit" href="goods/queryState?state=下架">下架</a>
        <a class="btn-submit" href="goods/queryState?state=">全部</a>
        |
        <a href="goods/query" class="btn-submit">刷新</a>
    </div>
    <!--模糊查询-->
    <div class="input-gropu">
        <form action="goods/query">
            <input type="text" placeholder="关键字" value="${key}" name="keyword" style="width:260px"/>
            <button class="btn">查询</button>
            <select name="order">
                <option value="g_num">编号</option>
                <option value="create_time" ${order eq 'create_time'?'selected':''}>创建时间</option>
                <option value="g_price" ${order eq 'g_price'?'selected':''}>价格</option>
                <option value="g_discount " ${order eq 'g_discount'?'selected':''}>折扣</option>
            </select>
            <button class="btn">排序</button>
        </form>
    </div>
    <!--用户信息-->
    <div class="userinfo-group">
        <table cellspacing="0">
            <tr class="top">
                <th>序号</th>
                <th>图片</th>
                <th>商品码</th>
                <th>名称</th>
                <th>备注</th>
                <th>价格</th>
                <th>状态</th>
                <th>新品</th>
                <th>推荐</th>
                <th>添加时间</th>
                <th></th>
            </tr>
            <core:forEach items="${goodsPage$datas}" var="t" varStatus="i" >

                <tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow" style="text-align:center">
                    <th>${i.count}</th>
                    <th><img src="images/goodspic/${t.gimg}" width="48" height="28"></th>
                    <th>${t.gnum}</th>
                    <th>${t.gname}</th>
                    <th style="max-width: 400px;">${t.gremark}</th>
                    <th><fmt:formatNumber value="${t.gprice}" pattern="####,####.000"></fmt:formatNumber></th>
                    <th>${t.gstate}</th>
                    <th>${t.gisnew}</th>
                    <th>${t.gisrecom}</th>
                    <th>${t.createTime}</th>
                    <td style="text-align: center">
                        <button class="btn1"><a style="color: inherit;font-size: inherit;text-decoration: none;"  href="goods/update?gnum=${t.gnum}">修改</a></button>
                        <button class="btn2"><a style="color: inherit;font-size: inherit;text-decoration: none;"  href="shopgoods/queryGoods?gnum=${t.gnum}" target="_blank">查看商品</a></button>
                    </td>
                </tr>
            </core:forEach>
            <!--遍历空白-->
            <core:forEach begin="${goodsPage$dataSize}" end="20" varStatus="i">
                <tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'}">
                    <td >&nbsp;</td>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                  
                </tr>
            </core:forEach>
        </table>

        <div class="page-group">
            <a class="btn" href="goods/query?pageindex=0">首页</a>
            <a class="btn" href="goods/query?pageindex=${goodsPage$lastIndex}">上一页</a>
            <div class="pages">
                <core:forEach begin="1" end="${goodsPage$maxPage}" varStatus="i">
                    <a class="page ${i.count eq goodsPage$nowPage?'select':''}"
                       href="goods/query?pageindex=${i.count}">${i.count}</a>
                </core:forEach>
            </div>
            <a class="btn" href="goods/query?pageindex=${goodsPage$nextIndex}">下一页</a>
            <a class="btn" href="goods/query?pageindex=${goodsPage$maxPage}">尾页</a>
        </div>
    </div>
</div>
<%@ include file="../../tools/msg.jsp" %>
</body>
</html>
