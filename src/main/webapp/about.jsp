<%@include file="user_header.jsp"%>

<head>
<title>About</title>
</head>


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
                    <img src="${pageContext.request.contextPath}/resources/images/img-event-04.jpg" alt="video-about-us" />
                </div>
            </div>

            <div class="text-intro col-sm-10 col-md-8 col-lg-7 wow fadeInDown">
                <h3>introduction</h3>
                <h2>Epam Restraunt</h2>
                <p>
                    System Restaurant.
                </p>
                <p>
                    The client makes orders from Menu. </p>
                <p>Administrator confirms orders and sends them to the kitchen for performance.
                    </p>
                <p>Administrator makes bills for orders. </p>
                <p>The client pays for his orders.
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
                    <img alt="picture-about-us" src="resources/images/menu01.jpg" />
                </div>
                <h3><a class="hover-link-color">Sergei Albrant</a></h3>
                <h6 class="medium-text">Main Researcher</h6>
                <p>
                </p>
            </div>

            <div class="content-event col-sm-10 col-md-8 col-lg-4 wow fadeInUp" data-wow-delay="0.4s">
                <div class="img-event">
                   <img alt="picture-about-us" src="resources/images/menu02.jpg" />
                </div>
                <h3><a class="hover-link-color" >Iuliia Kliueva</a></h3>
                <h6 class="medium-text">Main Mastermind</h6>
                <p>
                </p>
            </div>

            <div class="content-event col-sm-10 col-md-8 col-lg-4 wow fadeInUp" data-wow-delay="0.6s">
                <div class="img-event">
                    <img alt="picture-about-us" src="resources/images/menu03.jpg" />
                </div>
                <h3><a class="hover-link-color" >Agarustam Kurdov</a></h3>
                <h6 class="medium-text">Main Innovator</h6>
                <p>
                </p>
            </div>
        </div>
    </div>
</section>

<%@include file="footer.jsp"%>
