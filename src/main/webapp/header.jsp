<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<head>
    <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css"/>

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
            <a href="index.jsp"><img alt="logo-rest" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- Menu -->
        <div class="main_nav">
            <nav class="menu col_header">
                <ul class="main_menu">

                    <li><a href="make_order.jsp">Menu</a>
                        <ul class="sub_menu">
                            <li><a href="menu.html">Our Menu</a></li>
                            <li><a href="product-detail.html">Product Detail</a></li>
                            <li><a href="shop-page.html">Shop page</a></li>
                        </ul>
                    </li>

                    <li><a href="shopping-cart.html">Shopping Cart</a>
                        <ul class="sub_menu">
                            <li><a href="check-out.html">Check Out</a></li>
                            <li><a href="shopping-cart.html">Shopping Cart</a></li>
                        </ul>
                    </li>
                    <li><a href="new-order.html">Order</a>
                        <ul class="sub_menu">
                            <li><a href="new-order.html">New order</a></li>
                            <li><a href="make-bill.html">Make bill</a></li>
                        </ul>
                    </li>
                    <li><a href="about.jsp">About us</a>
                    </li>

                    <li><a href="index.html">${username}</a>
                        <ul class="sub_menu">
                            <li><a href="login.html">My orders</a></li>
                            <li><a href="#" onclick="">Log Out</a></li>
                        </ul>
                    </li>

                    <li>
                        <form action="logout" method="post">
                            <input type="submit" value="Logout">
                        </form>
                    </li>


                </ul>
            </nav>
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
                <li>
                    <a href="index.html">Menu</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="menu.html">Our Menu</a></li>
                        <li><a href="product-detail.html">Product Detail</a></li>
                        <li><a href="shop-page.html">Shop page</a></li>
                    </ul>
                </li>
                <li>
                    <a href="shopping-cart.html">Shopping Cart</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="check-out.html">Check Out</a></li>
                        <li><a href="shopping-cart.html">Shopping Cart</a></li>
                    </ul>
                </li>
                <li>
                    <a href="new-order.html">Order</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="new-order.html">New order</a></li>
                        <li><a href="make-bill.html">Make bill</a></li>
                    </ul>
                </li>
                <li>
                    <a href="about.html">About us</a>
                </li>
                <li>
                    <a href="index.html">Your account</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="login.html">Login</a></li>
                        <li><a href="join.html">Join</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</header>

<body class="animsition">



