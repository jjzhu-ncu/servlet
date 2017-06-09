<%--
  Created by IntelliJ IDEA.
  User: hzzhujiajun
  Date: 2017/6/9
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="errorHandler.jsp" %>
<html>
<head>
    <title>test error</title>
</head>
<body>
<%
    int a = Integer.parseInt("1ab");
%>
<%=a%>
</body>
</html>
