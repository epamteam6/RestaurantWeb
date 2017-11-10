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
<h3>All your paid orders!</h3>
<form method="post" action="user_done_orders">
    <table>
        <tr>
            <td width="112px"><c:out value="Order ID" /></td>
            <td width="112px"><c:out value="Dish and Amount" /></td>
            <td width="112px"><c:out value="Sum" /></td>
        </tr>
        <c:forEach var="item1" items="${usersOrders}" varStatus="i">
            <tr>
                <td width="112px"><c:out value="${item1[0]}" /></td>
                <td width="112px">
                    <c:forEach var="item2" items="${item1[1]}">

                        <c:out value="${item2.key} :" />
                        <c:out value="${item2.value}"/>
                        </br>
                    </c:forEach>
                </td>
                <td width="112px"><c:out value="${item1[2]}" /></td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>
</html>

