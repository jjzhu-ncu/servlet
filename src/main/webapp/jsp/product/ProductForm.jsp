<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhujiajunup
  Date: 2017/6/12
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>add product form</title>
</head>
<body>
<div id="global">
    <h3>add a product</h3>
    <c:if test="${requestScope.errors != null }">
        <p id="errors">
        Error(s)!
        <ul>
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
        </ul>
        </p>
    </c:if>

    <form method="post" action="product_save">
        <table>
            <tr>
                <td>Product Name:</td>
                <td><input type="text" name="name" value="${form.name}"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type="text" name="description" value="${form.description}"/></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" value="${form.price}"/></td>
            </tr>
            <tr>
                <td><input type="reset" /></td>
                <td><input type="submit" value="add product"/></td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>
