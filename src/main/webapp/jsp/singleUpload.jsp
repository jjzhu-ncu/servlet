<%--
  Created by IntelliJ IDEA.
  User: hzzhujiajun
  Date: 2017/6/13
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload file</title>
</head>
<body>
<h1>select a file to upload</h1>
<form action="singleUpload" enctype="multipart/form-data" method="post">
    Author: <input type="text" name="author"/><br/>
    Select file to upload <input type="file" name="filename"/><br/>
    <input type="submit" value="Upload">
</form>
</body>
</html>
