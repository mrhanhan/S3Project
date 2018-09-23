<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/1 0001
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<div class="title">|-公告管理</div>
		<!--功能按钮-->
		<div class="btn-group"
			style="text-align: right; display: inline-block; float: right;">
			<a class="btn-submit" href="./admin/bulliten/addBulliten.jsp">添加公告</a>
			| <a href="./admin/bulliten/updateBulliten.jsp" class="btn-submit">修改公告</a>

			<a href="bulliten/query" class="btn-submit">刷新</a>
		</div>
		<!--模糊查询-->
		<div class="input-gropu">
			<input type="text" placeholder="关键字" style="width: 260px" />
			<button class="btn">查询</button>
			<select>
				<option value="b_num">编号</option>
				<option value="b_title" ${b.btitle} >标题</option>
			</select>
			<button class="btn">排序</button>
		</div>
		<!--公告信息-->
		<div class="userinfo-group">
			<table cellspacing="0">
				<tr class="top">
					<th>序号</th>
					<th>标题</th>
					<th>内容</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<!--遍历数据-->
				<core:forEach items="${bullPage$datas}" var="t" varStatus="i">
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow" style="text-align:center">
						<td>${i.count}</td>
						<td>${t.btitle}</td>
						<td>${t.bcontent}</td>
						<td>${t.bdate}</td>
						<td style="text-align: center">
							<button class="btn1"><a href="bulliten/update?bnum=${t.bnum}">修改</a></button>
							<button class="btn2">查看公告</button>
						</td>
					</tr>
				</core:forEach>
				<!--遍历空白-->
				<core:forEach begin="${bullPage$dataSize}" end="20"
					varStatus="i">
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'}">
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</core:forEach>
			</table>
		</div>
	</div>
	</div>
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
