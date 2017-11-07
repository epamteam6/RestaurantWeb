
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">New order</jsp:attribute>
    <jsp:body>



        <!-- Title Menu page -->
        <section>
            <div class="bg-title-sub-page bg-menu-page-01">
                <div class="wrap-title-sub-page">
                    <h2 class="title-l">New Order</h2>
                    <h6 class="title-s">Home / New order</h6>
                </div>
            </div>
        </section>

        <section class="content-shopping-cart-page">
            <div class="container">
                <form />
                <div class="wrap-table-shopping-cart">
                    <table class="table-shopping-cart">
                        <tr class="table_head">
                            <th class="column-1">Product</th>
                            <th class="column-2">Name & Description</th>
                            <th class="column-3">Price</th>
                            <th class="column-4">Quantity</th>
                            <th class="column-5">Total</th>
                            <th class="column-6"></th>
                        </tr>

                        <tr>
                            <td class="column-1">
                                <div class="img-product">
                                    <img src="${pageContext.request.contextPath}/resources/images/shopping-cart-img-product-in-table-01.jpg" alt="img-product" />
                                </div>
                            </td>
                            <td class="column-2">Pork ribollita</td>
                            <td class="column-3">$ 10.50</td>
                            <td class="column-4">
                                <input class="input-num-product small-text" type="text" name="num-product" value="1" placeholder="1" />
                            </td>
                            <td class="column-5">$ 20.00</td>
                            <td class="column-6">
                                <i class="btn-remove-product fa fa-times" aria-hidden="true"></i>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-1">
                                <div class="img-product">
                                    <img src="${pageContext.request.contextPath}/resources/images/shopping-cart-img-product-in-table-02.jpg" alt="img-product" />
                                </div>
                            </td>
                            <td class="column-2">Potato gnocchi</td>
                            <td class="column-3">$ 20.00</td>
                            <td class="column-4">
                                <input class="input-num-product small-text" type="text" name="num-product" value="2" placeholder="1" />
                            </td>
                            <td class="column-5">$ 40.00</td>
                            <td class="column-6">
                                <i class="btn-remove-product fa fa-times" aria-hidden="true"></i>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="wrap-coupon">
                    <div class="row">
                        <div class="wrap-title-coupon col-responsive-shopping-cart col-md-10 col-lg-10">
                            <h4 class="title-coupon">Coupon Code</h4>
                        </div>

                        <div class="wrap-input-coupon col-responsive-shopping-cart col-md-10 col-lg-10">
                            <input type="text" class="input-coupon small-text" name="your-coupon" placeholder="Enter your coupon here" />
                        </div>
                        <div class="wrap-btn-coupon col-responsive-shopping-cart col-md-10 col-lg-2">
                            <button type="button" class="btn-coupon btn-with-bg">Apply Coupon</button>
                        </div>
                    </div>
                </div>

                <div class="wrap-estimate-shipping-extend wrap-coupon">
                    <div class="row">
                        <div class="wrap-title-coupon col-responsive-shopping-cart col-md-10 col-lg-10">
                            <h4 class="title-coupon">Estimate Shipping</h4>
                        </div>

                        <div class="wrap-chose-address-extend wrap-input-coupon col-responsive-shopping-cart col-md-10 col-lg-10">
                            <div class="row">
                                <div class="col-responsive-chose-address left col-md-12 col-lg-6">
                                    <select class="chose-people" name="chose-people">
                                        <option />City
                                        <option />City 2
                                        <option />City 3
                                        <option />City 4
                                        <option />City 5
                                        <option />City 6
                                    </select>
                                </div>

                                <div class="col-responsive-chose-address right  col-md-12 col-lg-6">
                                    <select class="chose-time" name="chose-time">
                                        <option />State
                                        <option />State 2
                                        <option />State 3
                                        <option />State 4
                                        <option />State 5
                                        <option />State 6
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="wrap-input-coupon col-responsive-shopping-cart col-md-10 col-lg-10">
                            <input type="text" class="input-coupon small-text" name="your-coupon" placeholder="Enter your post code here" />
                        </div>

                        <div class="wrap-btn-coupon col-responsive-shopping-cart col-md-10 col-lg-2 ">
                            <button type="button" class="btn-coupon btn-with-bg">Update Total</button>
                        </div>
                    </div>
                </div>

                <div class="wrap-total clearfix">
                    <div class="style-pos col-right">
                        <h6 class="larg-text">Total: $100.00</h6>
                        <button class="btn-with-bg">Proceed To Checkout</button>
                    </div>
                    <div class="style-pos col-left">
                        <a href="shop-page.html" class="small-text"><i class="fa fa-angle-left" aria-hidden="true"></i> Continue to shopping</a>
                    </div>
                </div>
                </form>
            </div>
        </section>


    </jsp:body>
</page:template>