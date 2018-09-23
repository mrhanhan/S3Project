<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/5 0005
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    out.println("<base href=\""+basePath+"\">");
%>
<!DOCTYPE html>

<!--[if IE 8 ]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9 ]> <html lang="en" class="ie9"> <![endif]-->

<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="en"> <!--<![endif]-->

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>用户注册</title>
    <link href='css/style.css' rel='stylesheet' type='text/css'>
</head>

<body>


<form id="myform">

    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="cus_name" value="">
    </p>
    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="" value="">
    </p>
    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="" value="">
    </p>
    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="" value="">
    </p>
    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="" value="">
    </p>
    <p>
        <label>请输入用户名</label>
        <input type="password" id="myPassword" name="" value="">
    </p>
</form>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/strength.js"></script>
<script type="text/javascript">
    $(document).ready(function($) {

        $('#myPassword').strength({
            strengthClass: 'strength',
            strengthMeterClass: 'strength_meter',
            strengthButtonClass: 'button_strength',
            strengthButtonText: '显示密码',
            strengthButtonTextToggle: '隐藏密码'
        });

    });
</script>

</body>
</html>