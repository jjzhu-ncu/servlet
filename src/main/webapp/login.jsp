<%--
  Created by IntelliJ IDEA.
  User: hzzhujiajun
  Date: 2017/6/13
  Time: 17:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="j_security_check" method="post">
    <table>
        <tr>
            <td>User name:</td>
            <td><input type="text" name="j_username"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="j_password"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Login"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
