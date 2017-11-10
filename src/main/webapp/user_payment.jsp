<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Order Page</title>
</head>
<body>
<h3>Hi! Here you can pay for your orders!</h3>
<form method="post" action="user_payment">
    <table>
        <tr>
            <td width="112px"><c:out value="Order ID" /></td>
            <td width="112px"><c:out value="Dish and Amount" /></td>
            <td width="112px"><c:out value="Total price" /></td>
        </tr>
        <c:forEach var="item1" items="${usersOrders}">
        <tr>
            <c:forEach var="item2" items="${item1.value}">
                <td width="112px"><c:out value="${item2.key}" /></td>
                <td width="112px">
                <c:forEach var="item3" items="${item2.value}">

                        <c:out value="${item3.key} :" />
                        <c:out value="${item3.value}"/>
                        </br>
                </c:forEach>

                </td>

                <td><input type="checkbox" name="${item2.key}" ></td>
                </tr>
            </c:forEach>

        </c:forEach>

        <tr>
        <td colspan="3" align="right"><input name="Pay" type="submit" value="Pay"></td>
        </tr>

    </table>
</form>
</body>
</html>
