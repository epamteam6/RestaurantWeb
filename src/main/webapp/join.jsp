<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Sing in</title>
</head>

<form method="post" action="registration">
    <h1>
        <font size="5" color="black">Create an account, please</font>
        <br>
    </h1>
    <table border="2" width="2" bgcolor="#d3d3d3" style="color:black">
        <tr>
            <td><b>Username</b></td>
            <td><input class="button" type="text" name="username"></td>
        </tr>
        <tr>
            <td><b>Password</b></td>
            <td><input class="button" type="password" name="password"></td>
        </tr>
        <tr>
            <td><input class="button" type="submit" value="Join"></td>
        </tr>
    </table>

</form>

</body>
</html>

