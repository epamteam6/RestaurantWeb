<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang : 'en'}" scope="session"/>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="lang.lang" scope="session"/>

<html lang="${lang}">
<head>
    <title><fmt:message key="epam.page_title" /></title>
    <meta charset="utf-8">

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
            <a href="${pageContext.request.contextPath}"><img alt="logo-epam" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- language -->
        <div class="icon-header col_header">
            <a href="${pageContext.request.contextPath}?lang=en">ENG</a>
            <a href="${pageContext.request.contextPath}?lang=ru">RU</a>
        </div>
    </div>

    <!-- Header Mobile -->
    <div id="wrap_header_mobile">

        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="${pageContext.request.contextPath}"><img alt="logo-epam" src="resources/images/icons/logo-mobile.png"/></a>
        </div>

    </div>

</header>


<section>
    <div id="main-section">
        <!-- Slider -->
        <div id="btn-slide-index">
            <h2 class="title-l"><fmt:message key="epam.title" /></h2>
            <h6 class="title-s"><fmt:message key="epam.welcome_title" /></h6>
            <h6>&nbsp;</h6>
            <a href="session_join" class="btn1"><fmt:message key="epam.join" /></a>
            <a href="session_login" class="btn2"><fmt:message key="epam.signin" /></a>

        </div>
    </div>
</section>

</body>
</html>

