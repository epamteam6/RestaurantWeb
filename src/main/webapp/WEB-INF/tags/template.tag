<!DOCTYPE html>
<%@tag description="Template Site tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@attribute name="title" fragment="true" %>

<head>
    <title><jsp:invoke fragment="title"/></title>

    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/css-hamburgers/hamburgers.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/select2/select2.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/daterangepicker-bootstrap/daterangepicker.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/animsition/dist/css/animsition.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/main.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/animate.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/slide100.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/vendor/jqueryui/jquery-ui.min.css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/animsition/dist/js/animsition.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/popper.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/select2/select2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/daterangepicker-bootstrap/moment.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/daterangepicker-bootstrap/daterangepicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/vendor/wow/wow.min.js"></script>


    <meta charset="UTF-8" />
    <meta name="description" content=" " />
    <meta name="author" content=" " />
    <meta name="keywords" content=" " />
    <!--Mobile Specific Metas-->
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>

<body class="animsition">

<header>
    <!-- Header desktop -->
    <div id="wrap_header">
        <!-- Logo -->
        <div class="logo col_header">
            <a href="index.html"><img alt="logo-deli" src="${pageContext.request.contextPath}/resources/images/icons/logo.png" /></a>
        </div>
        <!-- Menu -->
        <div class="main_nav">
            <nav class="menu col_header">
                <ul class="main_menu">

                    <li><a href="menu.html">Menu</a>
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
                    <li><a href="about.html">About Us</a>
                    </li>

                    <li><a href="index.html">Your account</a>
                        <ul class="sub_menu">
                            <li><a href="login.html">Login</a></li>
                            <li><a href="join.html">Join</a></li>
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
            <a href="index.html"><img alt="logo-deli" src="${pageContext.request.contextPath}/resources/images/icons/logo-mobile.png" /></a>
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

<jsp:doBody/>



    <footer>
        <div class="container">
            <div class="content-footer row">
                <div class="column-footer col-lg-5 col-md-8 col-sm-7">
                    <h3>Contact information</h3>
                    <ul>
                        <li>ADDRESS: 100 Tenth Avenue, New York City, NY 1001</li>
                        <li>FOR BOOKING: (044) 359 0173</li>
                        <li id="follow-us">FOLLOW US ON:
                            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-tripadvisor" aria-hidden="true"></i></a>
                            <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                        </li>
                    </ul>
                </div>

                <div class="column-footer col-lg-4 col-md-5 col-sm-7">
                    <h3>Restaurant hours</h3>
                    <ul id="restaurant-hours">
                        <li>
                            <span class="span-left">LUNCH:</span>
                        </li>
                        <li>
                            <span class="span-left">Monday - Friday</span>
                            <span class="span-right">11:30AM - 2:00PM</span>
                        </li>
                        <li>
                            <span class="span-left">DINNER:</span>
                        </li>
                        <li>
                            <span class="span-left">Monday - Friday</span>
                            <span class="span-right">5:30PM - 11:00PM</span>
                        </li>
                        <li>
                            <span class="span-left">Saturday - Sunday</span>
                            <span class="span-right">4:30PM - 10:00PM</span>
                        </li>
                    </ul>
                    <div class="line-divide first-line"></div>
                </div>

                <div class="column-footer col-lg-3 col-md-3 col-sm-7">
                    <h3>Useful links</h3>
                    <ul id="useful-links">
                        <li>
							<span class="span-left">
								<a href="index.html">Home</a>
							</span>
                            <span class="span-right">
								<a href="menu.html">Menu</a>
							</span>
                        </li>
                        <li>
							<span class="span-left">
								<a href="shop-page.html">Shop page</a>
							</span>
                            <span class="span-right">
								<a href="shopping-cart.html">Shopping Cart</a>
							</span>
                        </li>
                        <li>
							<span class="span-left">
								<a href="check-out.html">Check Out</a>
							</span>
                            <span class="span-right">
								<a href="product-detail.html">Product Detail</a>
							</span>
                        </li>
                        <li><a href="about.html">About us</a></li>
                    </ul>
                    <div class="line-divide second-line"></div>
                </div>
            </div>
        </div>
        <div class="wrap-bottom-footer">
            <div class="container">
                <div class="bottom-footer row justify-content-between">
                    <div class="col-12 col-sm-7">© 2017 All rights reserved</div>
                    <div class="col-12 col-sm-5"><span>Privacy policy</span><span>Terms of use</span></div>
                </div>
            </div>
        </div>
    </footer>

<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
</div>

<script type="text/javascript">
    new WOW().init();
</script>
<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>

</body>
</html>