<%--
  Created by IntelliJ IDEA.
  User: MrHanHao
  Date: 2018/9/5 0005
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    out.println("<base href=\"" + basePath + "\">");
%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>

</body>
<script type="text/javascript">
    var a = document.createElement("a");
    a.href="../index.jsp";
    a.target="_top"
    console.log(a)
    a.click();

</script>
</html>