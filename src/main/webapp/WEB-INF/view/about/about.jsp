
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">About</jsp:attribute>
    <jsp:body>



        <!-- Title About Us page -->
        <section>
            <div class="bg-title-sub-page bg-menu-page-02 ">
                <div class="wrap-title-sub-page">
                    <h2 class="title-l">About Us</h2>
                    <h6 class="title-s">Home / About Us</h6>
                </div>
            </div>
        </section>

        <!-- Intro -->

        <div class="wrap-intro for-page for-our-food">
            <div class="container">
                <div class="row">
                    <div class="wrap-pic-intro col-sm-10 col-md-8 col-lg-5">
                        <div class="hover-img wrap-video-about-us">
                            <img src="${pageContext.request.contextPath}/resources/images/img-our-food-about-us.jpg" alt="video-about-us" />
                        </div>
                    </div>

                    <div class="text-intro col-sm-10 col-md-8 col-lg-7 wow fadeInDown">
                        <h3>introduction</h3>
                        <h2>Our Food</h2>
                        <p>
                            Donec quis euismod purus. Donec feugiat ligula rhoncus, varius nisl sed, tincidunt lectus. Nulla vulputate, lectus vel volutpat efficitur, orci lacus sodales sem, at interdum quam ligula sit amet quam.
                        </p>
                        <p>
                            Praesent laoreet malesuada ex, sed blandit sem dictum id. Donec vul-putate ultricies nibh, quis dapibus ex cursus sit amet. Duis non ex pel-lentesque, sollicitudin justo a, porttitor magna. Duis lorem velit, sodales eget posuere in, commodo dictum felis. Aenean leo neque, pharetra vitae dui et, pretium venenatis metus.
                        </p>
                    </div>

                </div>
            </div>
        </div>

        <!-- Team -->
        <section class="team">
            <div class="container">
                <!-- title event -->
                <div class="wrap-title-event row">
                    <div id="title-event" class="col-12">
                        <h6>Discover Our</h6>
                        <h2>Awesome Staffs</h2>
                    </div>
                    <div class="content-event col-sm-10 col-md-8 col-lg-4 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="img-event">
                            <a href="#"><img alt="picture-about-us" src="${pageContext.request.contextPath}/resources/images/img-about-us-01.jpg" /></a>
                        </div>
                        <h3><a class="hover-link-color" href="#">Harry Lucas</a></h3>
                        <h6 class="medium-text">dining room manager</h6>
                        <p>
                            Proin lacinia nisl ut ultricies posuere nulla ut imperdiet nunc. Quisque id tellus vitae mauris feugiat comodone, donec pretium odio nec sagittis euismod.
                        </p>
                    </div>

                    <div class="content-event col-sm-10 col-md-8 col-lg-4 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="img-event">
                            <a href="#"><img alt="picture-about-us" src="${pageContext.request.contextPath}/resources/images/img-about-us-02.jpg" /></a>
                        </div>
                        <h3><a class="hover-link-color" href="#">Matthew Robinson</a></h3>
                        <h6 class="medium-text">Executive Chef</h6>
                        <p>
                            Donec a fringilla tortor. Vestibulum viverra accumsan ipsum nec luctus. Integer tortor dolor, suscipit sed justo et, interdum dignissim arcu.
                        </p>
                    </div>

                    <div class="content-event col-sm-10 col-md-8 col-lg-4 wow fadeInUp" data-wow-delay="0.6s">
                        <div class="img-event">
                            <a href="#"><img alt="picture-about-us" src="${pageContext.request.contextPath}/resources/images/img-about-us-03.jpg" /></a>
                        </div>
                        <h3><a class="hover-link-color" href="#">Angela Johnston</a></h3>
                        <h6 class="medium-text">Event Coordinator</h6>
                        <p>
                            Duis lorem velit, sodales eget posuere in, commodo dictum felis. Aenean leo neque, pharetra vitae dui et, pretium venenatis metus.
                        </p>
                    </div>
                </div>
            </div>
        </section>



    </jsp:body>
</page:template>
