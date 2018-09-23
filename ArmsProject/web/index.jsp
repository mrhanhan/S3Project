<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/8/12 0012
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    out.println("<base href=\""+basePath+"\">");
%>
<html >
<head>

    <title>军火库管理</title>
    <link rel="stylesheet" href="styles/color.css">
    <link rel="stylesheet" href="styles/util.css">
    <script type="text/javascript" src="script/jquery.min.js"></script>
    <script type="text/javascript" src="script/util.js"></script>
    <style type="text/css">
        *{
            color: white;

        }
        body{
            padding-top: 200px;
            background-color: #3b3e40;
            text-align: center;
            height: 70%;
        }
        .login {
            padding: 0px;
            width: 250px;
            height: 300px;
            margin: 0px auto;
            color: white;
        }
        hr{
            background-color: white;
        }
        table{
            width: 100%;
        }
        input{
            border: 1px solid black;
            width: 100%;
            border-radius: 3px;
            margin: 10px 0px;
            font-size: 12px;
            padding: 5px 5px;
            color: black;
        }

        .title{

            padding:0px;
            margin: 0px auto;
            text-align: center;
            font-size: 24pt;
        }
        a{
            color: #fceeff;
            font-size: 12px;
            transition: all 300ms;
            padding: 0px 10px;
            text-decoration-color: transparent;
        }
        a:hover{
            text-decoration: underline;
            text-decoration-color: #015d96;
        }
        button{
            border: 1px solid black;
            width: 100%;
            border-radius: 5px;
            background-color: #015d96;
            display: inline-block;
            color: #ffffff;
            font-family: "宋体";
            font-weight: 700;
            padding: 5px 2px;
            transition: all 200ms;

        }
        button:hover{
            background-color: lightgray;
            color: black;
        }

    </style>
</head>
<body >
<div class="login">

    <h1 class="title">军 火 库 系 统</h1>

    <form action="user/login" method="post">

                    <input type="text" placeholder="请输入员工账号" required name="n_username" id="id_name"/>

                    <input type="password" placeholder="请输入员工密码" required name="n_userpwd"/>

        <div style="text-align: center;">
            <button>登录</button>
        </div>
        <div style="text-align: center;margin-top: 10px;">
            <a href="">找回密码</a>
        </div>
    </form>
</div>
<script>
    $(function () {
        $("#id_name").focus()
    })
</script>
</body>
<%@include file="tools/msg.jsp" %>
</html>
