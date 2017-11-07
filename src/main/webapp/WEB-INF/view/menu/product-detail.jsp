
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Product detail</jsp:attribute>
    <jsp:body>


        <!-- Title -->
        <section>
            <div class="bg-title-sub-page bg-menu-page-02 ">
                <div class="wrap-title-sub-page">
                    <h2 class="title-l">Product Details</h2>
                    <h6 class="title-s">Home / Product Details</h6>
                </div>
            </div>
        </section>

        <section class="content-shop-page">
            <div class="container">
                <div class="row">
                    <!-- Slide -->
                    <div class="col-responsive-product col-sm-10 col-md-7">
                        <div class="wrap-slide-img-product row" id="slide100-01">
                            <div class="col-12">
                                <div class="main-frame">
                                    <div class="fix-frame wrap-pic">
                                        <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-01.jpg" alt="IMG-SLIDE" />
                                    </div>

                                    <div class="wrap-main-pic">
                                        <div class="main-pic wrap-pic">
                                            <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-01.jpg" alt="IMG-SLIDE" />
                                        </div>
                                    </div>

                                    <span class="my-arrow back"><i class="fa fa-chevron-left" aria-hidden="true"></i></span>
                                    <span class="my-arrow next"><i class="fa fa-chevron-right" aria-hidden="true"></i></span>
                                </div>
                            </div>
                            <div class="col-sub-img-1 col-4">
                                <div class="sub-frame sub-1">
                                    <div class="fix-frame wrap-pic">
                                        <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-02.jpg" alt="img-product" />
                                    </div>

                                    <div class="wrap-main-pic">
                                        <div class="main-pic wrap-pic">
                                            <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-02.jpg" alt="IMG-SLIDE" />
                                        </div>
                                    </div>

                                    <div class=" btn-sub-frame btn-1"></div>
                                </div>
                            </div>
                            <div class="col-sub-img-2 col-4">
                                <div class="sub-frame sub-2">
                                    <div class="fix-frame wrap-pic">
                                        <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-02.jpg" alt="img-product" />
                                    </div>

                                    <div class="wrap-main-pic">
                                        <div class="main-pic wrap-pic">
                                            <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-03.jpg" alt="IMG-SLIDE" />
                                        </div>
                                    </div>

                                    <div class=" btn-sub-frame btn-2"></div>
                                </div>
                            </div>
                            <div class="col-sub-img-3 col-4">
                                <div class="sub-frame sub-3">
                                    <div class="fix-frame wrap-pic">
                                        <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-02.jpg" alt="img-product" />
                                    </div>

                                    <div class="wrap-main-pic">
                                        <div class="main-pic wrap-pic">
                                            <img src="${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-04.jpg" alt="IMG-SLIDE" />
                                        </div>
                                    </div>

                                    <div class=" btn-sub-frame btn-3"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-responsive-product col-sm-10 col-md-5">
                        <div class="product">
                            <h3 class="name-product medium-text-2">Livorno Style Caccuchino</h3>
                            <span class="price-product medium-text">$ 25.00</span>
                            <p>
                                Morbi molestie nisi purus, vitae vestibulum turpis lacinia sit amet. Vestibulum ante ipsum primis in faucibus orci luctuset ultrices posuere.
                            </p>

                            <input class="input-num-product small-text" type="text" name="num-product" value="1" placeholder="1" />

                            <div class="btn-add-to-cart-product btn-with-bg">ADD TO CART</div>
                        </div>

                        <div class="info-product">
                            <h4 class="title-info-product show small-text ">Additionial Information<span class="small-text show-content-info-product">+</span></h4>
                            <div class="content-info-product">
                                <p>
                                    In ultricies arcu ac tellus mattis, rutrum semper felis gravida. Vestibulum dictum ante nibh, nec eleifend purus molestie nec. Vivamus ligula nulla, consectetur ornare velit at, vulputate iaculis nisi. Suspendisse placerat mauris aliquam nulla bibendum, et porta massa fermentum.
                                </p>
                                <p>
                                    Nateger condimentum a ante et molestie. Cras eu ante massa. Sed eget finibus risus. Proin aliquet ex ac imperdiet lobortis.
                                </p>
                            </div>
                        </div>

                        <div class="info-product">
                            <h4 class="title-info-product small-text">Reviews<span class="small-text show-content-info-product">+</span></h4>
                            <div class="content-info-product">
                                <p>
                                    In ultricies arcu ac tellus mattis, rutrum semper felis gravida. Vestibulum dictum ante nibh, nec eleifend purus molestie nec. Vivamus ligula nulla, consectetur ornare velit at, vulputate iaculis nisi. Suspendisse placerat mauris aliquam nulla bibendum, et porta massa fermentum.
                                </p>
                                <p>
                                    Nateger condimentum a ante et molestie. Cras eu ante massa. Sed eget finibus risus. Proin aliquet ex ac imperdiet lobortis.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </section>


        <script src="${pageContext.servletContext.contextPath}/resources/js/slide100.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#slide100-01').slide100({
                    autoPlay: "false",
                    timeAuto: 3000,
                    deLay: 500,

                    linkIMG: [
                        '${pageContext.request.contextPath}/resources/images/product-detail-img-01.jpg','${pageContext.request.contextPath}/resources//images/product-detail-img-02.jpg',
                        '${pageContext.request.contextPath}/resources//images/product-detail-img-03.jpg','${pageContext.request.contextPath}/resources//images/product-detail-img-04.jpg'
                    ],

                    linkThumb: [
                        '${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-01.jpg','${pageContext.request.contextPath}/resources//images/product-detail-img-thumb-02.jpg',
                        '${pageContext.request.contextPath}/resources/images/product-detail-img-thumb-03.jpg','${pageContext.request.contextPath}/resources//images/product-detail-img-thumb-04.jpg'
                    ]
                });
            });
        </script>


    </jsp:body>
</page:template>
