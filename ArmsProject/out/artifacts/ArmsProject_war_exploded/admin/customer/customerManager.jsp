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
<script type="text/javascript"
	src="./admin/customer/script/customerManager.js"></script>
<link rel="stylesheet" href="./admin/customer/styles/customer.css">
</head>
<body>
	<base target="CONTENTBODY">
	<div class="usermanager">
		<!--标题-->
		<div class="title">|-顾客管理</div>
		<!--功能按钮-->
		<div class="btn-group"
			style="text-align: right; display: inline-block; float: right;">
			<a class="btn-submit" href="./admin/customer/addCus.jsp">添加顾客</a> <a
				href="./admin/customer/updateCus.jsp" class="btn-submit">修改顾客</a> |
			<a href="./admin/customer/cusInfo.jsp" class="btn-submit">顾客详细</a> <a
				href="./admin/customer/cusCunsome.jsp" class="btn-submit">顾客消费</a> |
			<a href="customer/query" class="btn-submit">刷新</a>
		</div>
		<!--模糊查询-->
		<div class="input-gropu">
			<form action="customer/query">
				<input type="text" placeholder="关键字" name="keyword" value="${key}"
					style="width: 260px" />
				<button class="btn">查询</button>
				| <select>
					<option value="c_num">编号</option>
					<option value="c_num">消费</option>
					<option value="c_num">创建时间</option>
				</select>
				<button class="btn">排序</button>
			</form>
		</div>
		<!--用户信息-->
		<div class="userinfo-group">
			<table cellspacing="0">
				<tr class="top">
					<th>序号</th>
					<th>头像</th>
					<th>编号</th>
					<th>名称</th>
					<th>账号</th>
					<th>密码</th>
					<th>邮箱</th>
					<th>联系电话</th>
					<th></th>
				</tr>
				<!--遍历数据-->
				<core:forEach items="${cusPage$datas}" var="t" varStatus="i">
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'} userrow">
						<td>${i.count}</td>
						<td><img src="images/customer/${t.cimg}" width="28"
							height="28"></td>
						<td>${t.cnum}</td>
						<td>${t.cname}</td>
						<td>${t.caccount}</td>
						<td>${t.cpwd}</td>
						<td>${t.cemail}</td>
						<td>${t.ctelephone}</td>
						<td style="text-align: center">
							<button class="btn1">
								<a href="customer/update?gnum=${t.cnum}">修改</a>
							</button>
							<button class="btn2">信息</button>
						</td>
					</tr>
				</core:forEach>
				<!--遍历空白-->
				<core:forEach begin="${cusPage$dataSize}" end="20" varStatus="i">
					<tr class="select-tr-${((i.index%2) eq 0) ?'1':'2'}">
						<td>&nbsp;</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</core:forEach>
			</table>

			<div class="page-group">
				<a class="btn" href="customer/query?pageindex=0">首页</a> <a
					class="btn" href="customer/query?pageindex=${cusPage$lastIndex}">上一页</a>
				<div class="pages">
					<core:forEach begin="1" end="${cusPage$maxPage}" varStatus="i">
						<a class="page ${i.count eq cusPage$nowPage?'
							select':''}"
                       href="customer/query?pageindex=${i.count}">${i.count}</a>
					</core:forEach>
				</div>
				<a class="btn" href="customer/query?pageindex=${cusPage$nextIndex}">下一页</a>
				<a class="btn" href="customer/query?pageindex=${cusPage$maxPage}">尾页</a>
			</div>
		</div>
	</div>
	<%@include file="../../tools/msg.jsp" %>
</body>
</html>