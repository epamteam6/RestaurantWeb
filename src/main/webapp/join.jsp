
<!DOCTYPE html>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Join us</title>

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
    <div class="bg-title-sub-page bg-menu-page-02 ">
        <div class="wrap-title-sub-page">
            <h2 class="title-l">Join</h2>
            <h6 class="title-s">Home / Join</h6>
        </div>
    </div>
</section>



<section class="content-checkout-page">
    <div class="container">

        <!-- 01/Check out method -->
        <form method="post" action="join">
            <div class="wrap-check-out-method">
                <h4 class="medium-text-2">Join</h4>
                <p>
                    Enter your username and password to join us.
                </p>

                <div class="row">
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="text" name="username" placeholder="Username" />
                    </div>
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="password" name="password" placeholder="Password" />
                    </div>
                    <div class="wrap-btn-login col-md-2">
                        <button class="btn-login-extend btn-with-bg" type="submit">JOIN</button>
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
            <div class="col-12 col-sm-7">© 2017 All rights reserved</div>
            <div class="col-12 col-sm-5"><span>Privacy policy</span><span>Terms of use</span></div>
        </div>
    </div>
</div>
</footer>
</body>
</html>

