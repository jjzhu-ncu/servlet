<%--
  Created by IntelliJ IDEA.
  User: zhujiajunup
  Date: 2017/6/9
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>


<html>
<head>
    <title>Today's date</title>
</head>
<body>
<%!
    public Date getTodayDate(){
        return new Date();
    }
%>
<%
    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
    String s = dateFormat.format(getTodayDate());

%>
today is <%= s %> <br/>
<jsp:useBean id="today" class="java.util.Date" />

today is <%= today %>
</body>
</html>
