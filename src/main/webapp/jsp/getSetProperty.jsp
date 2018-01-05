<%--
  Created by IntelliJ IDEA.
  User: zhujiajunup
  Date: 2017/6/9
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jjzhu.study.httpsession.Product" %>
<html>
<head>
    <title>get property and set property</title>
</head>
<body>
<jsp:useBean id="product" class="jjzhu.study.httpsession.Product"/>
<jsp:setProperty name="product" property="id" value="1"/>
<jsp:setProperty name="product" property="name" value="Mac air"/>
<jsp:setProperty name="product" property="description" value="a very expensive computer"/>
<jsp:setProperty name="product" property="price" value="12.5"/>
getProperty tag:<br/>
name: <jsp:getProperty name="product" property="name"/><br/>
description: <jsp:getProperty name="product" property="description"/><br/>
price: <jsp:getProperty name="product" property="price"/><br/>
EL:<br/>
name: ${product.name}<br/>
description: ${product.description}<br/>
price: ${product.price}<br/>
</body>
</html>
