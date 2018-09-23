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
		<div class="title">|-用户信息</div>

		<!--用户信息-->
		<div class="userinfo-group">
			<table cellspacing="0">
				<tr class="top">
					
					<th>编号</th>
					<th>头像</th>
					<th>姓名</th>
					<th>权限</th>
					<th>创建时间</th>
					<th>创建者</th>
				</tr>
				<!--遍历数据-->
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow"
						style="text-align: center">
						<td>${LOGIN_USER.unum}</td>
						<th><img src="images/user/${LOGIN_USER.uimg}" width="48" height="28"></th>
						<td>${LOGIN_USER.uname}</td>
						<td>${LOGIN_USER.uauto}</td>
						<td>${LOGIN_USER.createTime}</td>
						<td>${LOGIN_USER.uCreateUnum}</td>

					</tr>

				<!--遍历空白-->
				<core:forEach begin="${userPage$dataSize}" end="20" varStatus="i">
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
	<%@ include file="../../tools/msg.jsp"%>
</body>
</html>
