<%--
  Created by IntelliJ IDEA.
  User: hzzhujiajun
  Date: 2017/6/13
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Upload</h1>
<form action="multipleUploads" enctype="multipart/form-data" method="post">
    <input type="file" name="filename"/><br/>
    <input type="file" name="filename"/><br/>
    <input type="submit" name="Upload"/><br/>
</form>
</body>
</html>
