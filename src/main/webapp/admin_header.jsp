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

    <script type="text/javascript" src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>


    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/revolution/css/layers.css" />
    <link rel="stylesheet" type="text/css" href="resources/vendor/revolution/css/navigation.css" />
    <link rel="stylesheet" type="text/css" href="resources/vendor/revolution/css/settings.css" />
    <!--===============================================================================================-->

    <link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker-bootstrap/daterangepicker.css" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/vendor/animsition/dist/css/animsition.min.css" />
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="resources/css/animate.css" />

    <!--===============================================================================================-->

    <!--===============================================================================================-->
    <script type="text/javascript" src="resources/vendor/wow/wow.min.js"></script>

    <!--===============================================================================================-->
    <script type="text/javascript" src="resources/vendor/animsition/dist/js/animsition.min.js"></script>
    <!--===============================================================================================-->
    <script type="text/javascript" src="resources/vendor/bootstrap/js/popper.min.js"></script>
    <!--===============================================================================================-->
    <script type="text/javascript" src="resources/vendor/revolution/js/jquery.themepunch.tools.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/jquery.themepunch.revolution.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.video.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.carousel.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.slideanims.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.actions.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.layeranimation.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.kenburn.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.navigation.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.migration.min.js"></script>
    <script type="text/javascript" src="resources/vendor/revolution/js/extensions/revolution.extension.parallax.min.js"></script>
    <script src="resources/js/slide-custom.js"></script>
    <!--===============================================================================================-->
    <script type="text/javascript" src="resources/vendor/select2/select2.min.js"></script>
    <!--===============================================================================================-->
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
            <a href="index.jsp"><img alt="logo-rest" src="resources/images/icons/logo.png"/></a>
        </div>
        <!-- Menu -->
        <div class="main_nav">
            <nav class="menu col_header">
                <ul class="main_menu">

                    <li><a href="admin_confirmation">New orders</a>
                    </li>

                    <li><a href="admin_bill_creation">Confirm orders</a>
                    </li>

                    <li><a href="admin_ready_orders">Ready orders</a>
                    </li>

                    <li><a href="admin_paid_orders ">Paid orders</a>
                    </li>

                    <li><a href="about">About us</a>
                    </li>

                    <li><a href="#">${username} </a>
                        <ul class="sub_menu">
                                <li><a href="session_logout" >Log Out</a></li>

                        </ul>
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
                        <li><a href="admin_bill_creation">Make bill</a></li>
                    </ul>
                </li>
                <li>
                    <a href="about.html">About us</a>
                </li>
                <li>
                    <a href="index.html">Your account</a>
                    <i class="arrow-main-menu fa fa-angle-right" aria-hidden="true"></i>
                    <ul class="sub-menu">
                        <li><a href="session_login">Login</a></li>
                        <li><a href="session_join">Join</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </div>
</header>





