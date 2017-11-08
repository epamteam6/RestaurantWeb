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


    <h1><font size="7" color="black">Epam Restaurant:</font></h1>

    <h1><font size="4" color="black">to continue choose:</font></h1>
    <br>
    <form method="post" action="login">
        <input class="button" style="width: 30%" type="submit" value="Login in system">
    </form>

    <form method="post" action="registration">
        <input class="button" style="width: 30%" type="submit" value="Join">
    </form>


</body>
</html>

