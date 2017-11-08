
<%@include file="header.jsp"%>

<head><title>Log in</title></head>

<section>
    <div class="bg-title-sub-page bg-menu-page-02 ">
        <div class="wrap-title-sub-page">
            <h2 class="title-l">Login</h2>
            <h6 class="title-s">Home / Login</h6>
        </div>
    </div>
</section>



<section class="content-checkout-page">
    <div class="container">

        <!-- 01/Check out method -->
        <form method="post" action="login">
            <div class="wrap-check-out-method">
                <h4 class="medium-text-2">Login</h4>
                <p>
                    Enter your username and password to sign in.
                </p>

                <div class="row">
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="text" name="username" placeholder="Username" />
                    </div>
                    <div class="col-res-check-out-method col-md-5">
                        <input class="input-check-out small-text" type="password" name="password" placeholder="Password" />
                    </div>
                    <div class="wrap-btn-login col-md-2">
                        <button class="btn-login-extend btn-with-bg" type="submit">LOG IN</button>
                    </div>
                </div>
            </div>
        </form>

    </div>
</section>

<%@include file="footer.jsp"%>

</body>
</html>

