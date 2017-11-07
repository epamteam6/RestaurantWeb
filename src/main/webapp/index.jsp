<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Main Page</title>
</head>
<body>
<form method="post" action="/">
    <table>
        <c:forEach var="item1" items="${menu}">
            <tr>
                <td colspan="3"><center><c:out value="${item1.key}" /></center></td>
            </tr>
            <c:forEach var="item2" items="${item1.value}">
                <tr>
                    <td width="112px"><c:out value="${item2.key}" /></td>
                    <td width="112px"><c:out value="${item2.value}" /></td>

                    <td align="center" width="12px">
                        <input type="number" name="${item2.key}"
                               value="0" min="0" max="100"/>
                    </td>
                </tr>
                <tr></tr>
            </c:forEach>

        </c:forEach>

        <tr>
            <td colspan="3" align="right"><input class="button" type="submit" value="Make order"></td>
        </tr>

    </table>
</form>
</body>
</html>

