<%--
  Created by IntelliJ IDEA.
  User: zhujiajunup
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
    <input type="file" name="filename"/>
    <input type="file" name="filename"/>
    <input type="submit" name="Upload"/>
</form>

</body>
</html>
