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

<html lang="${lang}" style="height: 100%;">

<head>
    <title><fmt:message key="login.title" /></title>

    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/main.css" />
    <!--Mobile Specific Metas-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>


<body class="animsition" style="height: 100%;">

<header>
    <!-- Header desktop -->
    <div id="wrap_header">
        <!-- Logo -->
        <div class="logo col_header">
            <a href="index"><img alt="logo-epam" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- language -->
        <div class="icon-header col_header">
            <a href="${pageContext.request.contextPath}?lang=ru">Ru</a>
            <a href="${pageContext.request.contextPath}?lang=en">Eng</a>
        </div>
    </div>

    <!-- Header Mobile -->
    <div id="wrap_header_mobile">

        <!-- Logo moblie -->
        <div class="logo-mobile">
            <a href="index"><img alt="logo-deli" src="resources/images/icons/logo-mobile.png"/></a>
        </div>

    </div>

</header>

<section>
    <div class="bg-title-sub-page bg-menu-page-02 ">
        <div class="wrap-title-sub-page">
            <h2 class="title-l"><fmt:message key="login.title" /></h2>
            <h6 class="title-s"><fmt:message key="login.slide" /></h6>
        </div>
    </div>
</section>



<section class="content-checkout-page">
    <div class="container">

        <h6 class="title-s2">${message}</h6>
        <!-- 01/Check out method -->
        <form method="post" action="session_login">
            <div class="wrap-check-out-method">
                <h4 class="medium-text-2"><fmt:message key="login.title" /></h4>
                <p>
                    <fmt:message key="login.string" />
                </p>

                <div class="row">
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="text" name="username" placeholder="<fmt:message key="login.user" />" />
                    </div>
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="password" name="password" placeholder="<fmt:message key="login.password" />" />
                    </div>
                    <div class="wrap-btn-login col-md-2">
                        <button class="btn-with-bg" type="submit"><fmt:message key="login.title" /></button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</section>

<footer>
    <div class="wrap-bottom-footer">
        <div class="container">
            <div class="bottom-footer row justify-content-between">
                <div class="col-12 col-sm-7"><fmt:message key="login.right" /></div>
                <div class="col-12 col-sm-5"><span><fmt:message key="login.left" /></span><span><fmt:message key="login.tems" /></span></div>
            </div>
        </div>
    </div>
</footer>

</body>
</html>

