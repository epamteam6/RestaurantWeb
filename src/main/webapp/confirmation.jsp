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
<h3>Hello, Admin! Confirm orders!</h3>
<form method="post" action="confirmation">
    <table>
        <tr>
            <td width="112px"><c:out value="UserName" /></td>
            <td width="112px"><c:out value="Order ID" /></td>
            <td width="112px"><c:out value="Dish and Amount" /></td>
            <td width="112px"><c:out value="Sum" /></td>
        </tr>
        <c:forEach var="item1" items="${usersOrders}" varStatus="i">
        <tr>
            <td width="112px"><c:out value="${item1[0]}" /></td>
            <td width="112px"><c:out value="${item1[1]}" /></td>
            <td width="112px">
                <c:forEach var="item2" items="${item1[2]}">

                    <c:out value="${item2.key} :" />
                    <c:out value="${item2.value}"/>
                    </br>
                </c:forEach>
            </td>
            <td width="112px"><c:out value="${item1[3]}" /></td>

            <td><input type="checkbox" name="${item1[1]}" ></td>
        </tr>
        </c:forEach>

        <tr>
        <td colspan="3" align="right"><input name="Confirm" type="submit" value="Confirm"></td>
        </tr>
        <tr>
            <td colspan="3" align="right"><input name="Cancel" type="submit" value="Cancel"></td>
        </tr>

    </table>
</form>
</body>
</html>

