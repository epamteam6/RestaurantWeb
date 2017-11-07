
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">Check out</jsp:attribute>
    <jsp:body>



        <!-- Title page -->
        <section>
            <div class="bg-title-sub-page bg-menu-page-02 ">
                <div class="wrap-title-sub-page">
                    <h2 class="title-l">Check out</h2>
                    <h6 class="title-s">Home / Check out</h6>
                </div>
            </div>
        </section>

        <section class="content-checkout-page">
            <div class="container">


                <form />
                <!-- 02/Billing & shipping detail -->
                <div class="wrap-billing-shipping-detail">
                    <div class="row">
                        <div class="col-sm-12 col-md-6">
                            <!-- -->
                            <div class="input-billing-shipping-detail row">
                                <div class="col-12">
                                    <h4 class="medium-text-2">02 / Billing & Shipping details</h4>
                                </div>

                                <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="first-name" placeholder="First name" />
                                </div>
                                <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="last-name" placeholder="Last name" />
                                </div>
                                <div class="col-12">
                                    <input class="input-check-out small-text" type="text" name="company" placeholder="Company (optinal)" />
                                </div>
                                <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="address" placeholder="Address" />
                                </div>
                                <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="apt-suit" placeholder="Apt, suite, etc. (optinal)" />
                                </div>
                            </div>

                            <div class="wrap-chose-address-extend for-check-out-page">
                                <div class="row">
                                    <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                        <select class=" chose-people" name="chose-people">
                                            <option />City
                                            <option />City 2
                                            <option />City 3
                                            <option />City 4
                                            <option />City 5
                                            <option />City 6
                                        </select>
                                    </div>

                                    <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
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

                            <div class="input-billing-shipping-detail row">
                                <div class="col-12">
                                    <input class="input-check-out small-text" type="text" name="postcode" placeholder="Postcode / ZIP" />
                                </div>
                                <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="phone" placeholder="Phone" />
                                </div>
                                <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                    <input class="input-check-out small-text" type="text" name="email" placeholder="Email" />
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-12 col-md-6">
                            <div class="row">
                                <!-- -->
                                <div class="wrap-checkbox-different-address col-12">
                                    <input type="checkbox" id="different-address" name="different-address" />
                                    <label for="different-address">
                                        <span class="btn-check"></span>
                                        <span class="text-checkbox small-text">Ship to a different address?</span>
                                    </label>
                                </div>

                                <div class="col-12 wrap-input-different-address">
                                    <!-- -->
                                    <div class="input-billing-shipping-detail row">

                                        <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="first-name" placeholder="First name" />
                                        </div>
                                        <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="last-name" placeholder="Last name" />
                                        </div>
                                        <div class="col-12">
                                            <input class="input-check-out small-text" type="text" name="company" placeholder="Company (optinal)" />
                                        </div>
                                        <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="address" placeholder="Address" />
                                        </div>
                                        <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="apt-suit" placeholder="Apt, suite, etc. (optinal)" />
                                        </div>
                                    </div>

                                    <div class="wrap-chose-address-extend for-check-out-page">
                                        <div class="row">
                                            <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                                <select class=" chose-people" name="chose-people">
                                                    <option />City
                                                    <option />City 2
                                                    <option />City 3
                                                    <option />City 4
                                                    <option />City 5
                                                    <option />City 6
                                                </select>
                                            </div>

                                            <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
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

                                    <div class="input-billing-shipping-detail row">
                                        <div class="col-12">
                                            <input class="input-check-out small-text" type="text" name="postcode" placeholder="Postcode / ZIP" />
                                        </div>
                                        <div class="col-res-input-billing-shipping-detail left col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="phone" placeholder="Phone" />
                                        </div>
                                        <div class="col-res-input-billing-shipping-detail right col-md-12 col-lg-6">
                                            <input class="input-check-out small-text" type="text" name="email" placeholder="Email" />
                                        </div>
                                    </div>
                                </div>

                                <div class="wrap-order-note input-billing-shipping-detail col-12">
                                    <textarea class="order-note small-text" name="order-note" placeholder="Order notes"></textarea>
                                </div>


                            </div>
                        </div>

                        <div class="wrap-checkbox-different-address col-12">
                            <input type="checkbox" id="save-info" name="save-info" />
                            <label for="save-info">
                                <span class="btn-check"></span>
                                <span class="text-checkbox small-text">Save this information for next time</span>
                            </label>
                        </div>
                        <!-- -->
                    </div>
                </div>

                <!-- Your order -->
                <div class="wrap-your-order">
                    <div class="title-your-order">
                        <h4 class="medium-text-2">Your order</h4>
                    </div>

                    <div class="table-your-order-extend wrap-table-shopping-cart">
                        <table class="table-shopping-cart">
                            <tr class="table_head">
                                <th class="column-2">Product</th>
                                <th class="column-3">Total</th>
                            </tr>

                            <tr class="table-row">
                                <td class="column-2">Pork Grilled x 2</td>
                                <td class="column-3">$ 5.00</td>
                            </tr>

                            <tr class="table-row">
                                <td class="column-2">Subtotal</td>
                                <td class="column-3">$ 3.00</td>
                            </tr>

                            <tr class="table-row">
                                <td class="column-2">Shipping</td>
                                <td class="column-3">Flat rate: $ 2.00</td>
                            </tr>

                            <tr class="table-row">
                                <td class="column-2">Total</td>
                                <td class="column-3">$ 10.00</td>
                            </tr>
                        </table>
                    </div>
                </div>

                <!-- 03 / Payment information -->
                <div class="wrap-payment">
                    <h4 class="medium-text-2">03 / Payment information</h4>
                    <p>
                        Credit card (PayPal)
                        <span class="wrap-icon-pay first">
							<img src="${pageContext.request.contextPath}/resources/images/icons/visa.png" class="img-no-color" alt="img-paypal " />
							<img src="${pageContext.request.contextPath}/resources/images/icons/visa-img.png" class="img-color" alt="img-paypal " />
						</span>
                        <span class="wrap-icon-pay">
							<img src="${pageContext.request.contextPath}/resources/images/icons/paypal.png" class="img-no-color" alt="img-paypal" />
							<img src="${pageContext.request.contextPath}/resources/images/icons/paypal-img.png" class="img-color" alt="img-paypal" />
						</span>
                        <span class="wrap-icon-pay">
							<img src="${pageContext.request.contextPath}/resources/images/icons/master-card.png" class="img-no-color" alt="img-paypal" />
							<img src="${pageContext.request.contextPath}/resources/images/icons/master-card-img.png" class="img-color" alt="img-paypal" />
						</span>
                        <span class="wrap-icon-pay">
							<img src="${pageContext.request.contextPath}/resources/images/icons/american-express.png" class="img-no-color" alt="img-paypal" />
							<img src="${pageContext.request.contextPath}/resources/images/icons/american-express-img.png" class="img-color" alt="img-paypal" />
						</span>
                        <span class="wrap-icon-pay">
							<img src="${pageContext.request.contextPath}/resources/images/icons/discover.png" class="img-no-color" alt="img-paypal" />
							<img src="${pageContext.request.contextPath}/resources/images/icons/discover-img.png" class="img-color" alt="img-paypal" />
						</span>
                    </p>
                    <p>
                        Pay with your credit card via PayPal Website Payments Pro.
                    </p>

                    <div class="row">
                        <div class="col-res-payment col-sm-12 col-md-6 col-lg-3">
                            <input class="input-check-out small-text" type="text" name="card-number" placeholder="Card Number" />
                        </div>
                        <div class="col-res-payment col-sm-12 col-md-6 col-lg-3">
                            <input class="input-check-out small-text" type="text" name="expiry" placeholder="Expiry (MM/YY)" />
                        </div>
                        <div class="col-res-payment col-sm-12 col-md-6 col-lg-3">
                            <input class="input-check-out small-text" type="text" name="card-code" placeholder="Card Code" />
                        </div>
                        <div class="col-res-payment col-sm-12 col-md-6 col-lg-3">
                            <button class="btn-with-bg">Place Order</button>
                        </div>
                    </div>
                </div>
                </form>
            </div>
        </section>


    </jsp:body>
</page:template>