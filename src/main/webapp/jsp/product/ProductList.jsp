<%--
  Created by IntelliJ IDEA.
  User: hzzhujiajun
  Date: 2017/6/12
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<c:if test="${requestScope.products.size() == 0}">
    <h1>Not products!!</h1>
</c:if>
<c:if test="${requestScope.products.size() != 0}">
    <table>
        <tr>
            <td>name</td>
            <td>description</td>
            <td>price</td>
        </tr>
    <c:forEach var="product" items="${requestScope.products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<a href="product_input"> add product </a>


</body>
</html>
