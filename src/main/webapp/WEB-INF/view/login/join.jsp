
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Join</jsp:attribute>
    <jsp:body>

        <!-- Title page -->
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
                <form />
                <div class="wrap-check-out-method">
                    <h4 class="medium-text-2">01 / Checkout method</h4>
                    <p>
                        If you have shopped with us before, please enter your details in the boxes below. If you are a new customer, please proceed to the Billing & Shipping section.
                    </p>

                    <div class="row">
                        <div class="col-res-check-out-method col-md-5">
                            <input class="input-check-out small-text" type="text" name="username" placeholder="Your name" />
                        </div>
                        <div class="col-res-check-out-method col-md-5">
                            <input class="input-check-out small-text" type="password" name="password" placeholder="Password" />
                        </div>
                        <div class="wrap-btn-login col-md-2">
                            <button class="btn-login-extend btn-with-bg">Join</button>

                        </div>
                    </div>
                </div>
                </form>

            </div>
        </section>


    </jsp:body>
</page:template>