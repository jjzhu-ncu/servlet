<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: zhujiajunup
  Date: 2017/6/9
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP inplicit objects</title>
</head>

<body>
<b>Http headers:</b><br/>
<%
    for(Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();){
        String header = e.nextElement();
        out.println(header+":"+request.getHeader(header)+"<br/>");
    }
%>
<hr/>
<%
    out.println("Buffer size: "+response.getBufferSize());
    out.println("Seesion id: " + session.getId()+"<br/>");
    out.println("Servlet name:" + config.getServletName()+"<br/>");
    out.println("Server info:" + application.getServerInfo()+"<br/>");
%>
</body>
</html>
