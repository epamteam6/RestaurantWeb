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
    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css"/>

    <script type="text/javascript" src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker-bootstrap/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="resources/vendor/animsition/dist/css/animsition.min.css" />
    <link rel="stylesheet" type="text/css" href="resources/css/animate.css" />
    <script type="text/javascript" src="resources/vendor/wow/wow.min.js"></script>
    <script type="text/javascript" src="resources/vendor/animsition/dist/js/animsition.min.js"></script>
    <script type="text/javascript" src="resources/vendor/bootstrap/js/popper.min.js"></script>
    <script src="resources/js/slide-custom.js"></script>
    <script type="text/javascript" src="resources/vendor/select2/select2.min.js"></script>
    <script type="text/javascript" src="resources/vendor/daterangepicker-bootstrap/moment.min.js"></script>
    <script type="text/javascript" src="resources/vendor/daterangepicker-bootstrap/daterangepicker.js"></script>
    <!--===============================================================================================-->
    <script src="resources/js/main.js"></script>

    <!--Mobile Specific Metas-->
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body class="animsition">

<header>
    <!-- Header desktop -->
    <div id="wrap_header">
        <!-- Logo -->
        <div class="logo col_header">
            <a href="user_create_order"><img alt="logo-epam" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- Menu -->
        <div class="main_nav">
            <nav class="menu col_header">
                <ul class="main_menu">

                    <li><a href="user_create_order">Create New Order</a>
                    </li>

                    <li><a href="#">Your Order Status</a>
                        <ul class="sub_menu">
                            <li><a href="user_created_orders">Orders For Confirmation</a></li>
                            <li><a href="user_confirmed_orders">Cooking Orders</a></li>
                            <li><a href="user_payment">Waiting For Payment Orders</a></li>
                            <li><a href="user_done_orders">Paid Orders</a></li>
                        </ul>
                    </li>

                    <li><a href="about">About us</a>
                    </li>


                    <li><a href="#">You are welcome,  ${username}!</a>
                        <ul class="sub_menu">
                            <li><a href="session_logout" onclick="">Log Out</a></li>
                        </ul>
                    </li>

                </ul>
            </nav>
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
            <a href="user_create_order"><img alt="logo-epam" src="resources/images/icons/logo-mobile.png"/></a>
        </div>

        <!-- Button show menu -->
        <div class="btn-show-menu">
            <button class="btn-show-menu-mobile hamburger hamburger--squeeze" type="button">
					<span class="hamburger-box">
						<span class="hamburger-inner"></span>
					</span>
            </button>
        </div>
    </div>

    <!-- Menu Mobile -->
    <div class="wrap-side-menu">
        <nav class="side-menu">
            <ul class="main-menu">
                <li><a href="user_create_order">Create New Order</a>
                </li>
                <li>
                    <a href="#">Your Order Status</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="user_created_orders">Orders For Confirmation</a></li>
                        <li><a href="user_confirmed_orders">Cooking Orders</a></li>
                        <li><a href="user_payment">Waiting For Payment Orders</a></li>
                        <li><a href="user_done_orders">Paid Orders</a></li>
                    </ul>
                <li><a href="about">About us</a>
                </li>
                <li><a href="#">You are welcome, ${username}</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="session_logout" onclick="">Log Out</a></li>
                    </ul>
                </li>

                <li>
                    <a href="#">Language</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li> <a href="${pageContext.request.contextPath}?lang=ru">Ru</a></li>
                        <li><a href="${pageContext.request.contextPath}?lang=en">Eng</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</header>





