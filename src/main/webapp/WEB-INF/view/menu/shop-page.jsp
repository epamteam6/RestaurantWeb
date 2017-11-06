
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Shop page</jsp:attribute>
    <jsp:body>


        <!-- Title Shop page -->
        <section>
            <div class="bg-title-sub-page bg-menu-page-02 ">
                <div class="wrap-title-sub-page">
                    <h2 class="title-l">Shop Page</h2>
                    <h6 class="title-s">Home / Shop Page</h6>
                </div>
            </div>
        </section>

        <section class="content-shop-page">
            <div class="container">
                <div class="row">
                    <!-- Shop list -->
                    <div class="col-md-12 col-lg-9">
                        <div class="top-shop-list row">
                            <div class="result col-12 col-sm-6">
                                <span class="small-text">Showing 1–12 of 28 results</span>
                            </div>
                            <div class="col-12 col-sm-6">
                                <select class="chose-sort" name="chose-people">
                                    <option />Sort by default
                                    <option />Sort by price
                                    <option />Sort by name
                                </select>
                            </div>
                        </div>

                        <div class="middle-shop-list row">
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-01.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Charred octopus</h4></a>
                                    <p class="price-item">$ 20.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-02.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Potato gnocchi</h4></a>
                                    <p class="price-item">$ 10.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-03.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Pork ribollita</h4></a>
                                    <p class="price-item">$ 15.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                        </div>

                        <div class="middle-shop-list row">
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-04.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Pine nut sbrisalona</h4></a>
                                    <p class="price-item">$ 20.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-05.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Lobster caponata</h4></a>
                                    <p class="price-item">$ 10.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-02.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Chocolate budino</h4></a>
                                    <p class="price-item">$ 15.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                        </div>

                        <div class="middle-shop-list row">
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-07.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Livorno style caccuchino</h4></a>
                                    <p class="price-item">$ 20.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-08.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Baulletti</h4></a>
                                    <p class="price-item">$ 10.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                            <div class="col-item col-sm-7 col-md-4">
                                <div class="item-shop-page">
                                    <div class="img-item">
                                        <a href="product-detail.html"><img src="${pageContext.request.contextPath}/resources/images/shop-page-img-item-01.jpg" alt="img-food" /></a>
                                    </div>
                                    <a href="product-detail.html"><h4 class="name-item">Livorno style caccuchino</h4></a>
                                    <p class="price-item">$ 10.00</p>
                                    <button class="btn-with-bg add-to-cart">ADD TO CART</button>
                                </div>
                            </div>
                        </div>

                        <div class="bottom-shop-list row">
                            <div class="col-item col-sm-7 col-md-12">
                                <span class="small-text"><i class="fa fa-angle-left" aria-hidden="true"></i></span>
                                <span class="small-text">1</span>
                                <span class="small-text">2</span>
                                <span class="small-text"><i class="fa fa-angle-right" aria-hidden="true"></i></span>
                            </div>
                        </div>
                    </div>

                    <!-- Side bar -->
                    <div class="col-md-12 col-lg-3">
                        <div class="wrap-side-bar row">
                            <div class="wrap-search-box col-12">
                                <div class="search-box">
                                    <input class="search small-text" type="search" placeholder="Search..." />
                                    <i class="btn-search fa fa-search" aria-hidden="true"></i>
                                </div>
                            </div>

                            <div class="categories col-12">
                                <h4 class="medium-text-2 ">Categories</h4>
                            </div>
                            <div class="categories-content col-12">
                                <ul>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Vegan Food</a>
                                        <span>(10)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">America Restaurants</a>
                                        <span>(25)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Healthy Diet</a>
                                        <span>(15)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Salad Recipes</a>
                                        <span>(10)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Food Tips</a>
                                        <span>(25)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Fresh Products</a>
                                        <span>(15)</span>
                                    </li>
                                    <li class="small-text clearfix">
                                        <a href="#" class="small-text">Vegan Food</a>
                                        <span>(10)</span>
                                    </li>
                                </ul>
                            </div>

                            <div class="filter-price col-12">
                                <h4 class="medium-text-2 ">Filter By Price</h4>
                            </div>
                            <div class="wrap-filter-price-bar col-12">
                                <div class="filter-price-bar">
                                    <div class="line-bar"></div>

                                    <div id="containment-wrapper-2" class="wrap-point">
                                        <div id="draggable2" class="draggable ui-widget-content"></div>
                                    </div>

                                    <div id="containment-wrapper-3" class="wrap-point">
                                        <div id="draggable3" class="draggable ui-widget-content"></div>
                                    </div>
                                    <span>Price: $<span class="show-pos-begin">2</span> — $<span class="show-pos">50</span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


    </jsp:body>
</page:template>