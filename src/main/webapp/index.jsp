<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Restaurant</title>

    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/main.css" />
    <!--Mobile Specific Metas-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<body class="animsition">

<header>
    <!-- Header desktop -->
    <div id="wrap_header">
        <!-- Logo -->
        <div class="logo col_header">
            <a href="index.jsp"><img alt="logo-deli" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- language -->
        <div class="icon-header col_header">
            <a href="#">Ru</a> <a href="#">Eng</a>
        </div>
    </div>

    <!-- Header Mobile -->
    <div id="wrap_header_mobile">

        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="index.jsp"><img alt="logo-deli" src="resources/images/icons/logo-mobile.png"/></a>
        </div>

    </div>

</header>


<section>
    <div id="main-section">
        <!-- Slider -->
        <div id="btn-slide-index">
            <h2 class="title-l">Epam Restaurant</h2>
            <h6 class="title-s">the most delicious dishes you've ever enjoyed</h6>
            <h6>&nbsp;</h6>
            <a href="session_join.jsp" class="btn1">JOIN</a>
            <a href="session_login.jsp" class="btn2">LOG IN</a>

            <a href="main.jsp" class="btn2">main</a>
            <a href="cart.jsp" class="btn2">cart</a>

        </div>

    </div>
</section>

</body>
</html>

