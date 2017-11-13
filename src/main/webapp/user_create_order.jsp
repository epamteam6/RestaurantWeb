<%@include file="user_header.jsp" %>

<head><title>Create Order</title></head>


<!-- Title Menu page -->
<section>
    <div class="bg-title-sub-page bg-menu-page-01">
        <div class="wrap-title-sub-page">
            <h2 class="title-l">Create Order</h2>
            <h6 class="title-s">Home / Create Order</h6>
            <h6 class="title-s">${message}</h6>
        </div>
    </div>
</section>

<section class="restyle-menu-03 home-onepage-menu content-reservation-03 pad-bt-80">
<form method="post" action="user_create_order">

    <!-- Our menu 1-->
    <c:forEach var="menu" items="${menu}">


        <section class="restyle-menu-03 home-onepage-menu content-reservation-03 pad-bt-80">


            <div class="container">
                <div class="row">
                    <div class="col-content col-sm-10 col-md-8 col-lg-5">
                        <div class="img-reservation-03 hover-img">
                            <img src="${pageContext.request.contextPath}/resources/images/menu-03-img-01.jpg"
                                 alt="img-menu"/>
                        </div>
                    </div>

                    <div class="col-content col-sm-10 col-md-8 col-lg-7">
                        <div class="col-right-reservation-03">
                            <!-- title our menu -->
                            <div class="wrap-title-our-menu row">
                                <div class="col-12 title-our-menu">
                                    <h6>try &amp; discover</h6>
                                    <h2><c:out value="${menu.key}"/></h2>
                                </div>
                            </div>


                            <!-- list food -->
                            <div class="wrap-list-food">
                                <div class="row list-food">
                                    <div class="col-12" >
                                        <!-- item food -->
                                        <c:forEach var="dishType" items="${menu.value}">

                                            <div class="item-food row wow fadeInRight" data-wow-delay="0.2s">
                                                <div class="col-12 col-sm-10 text-list-food ">
                                                    <div class="name-price row">
                                                        <div class="name-food col-12 col-sm-auto">
                                                            <a class="hover-link-color"
                                                                href="./product-detail.html"><c:out value="${dishType.key}"/></a></div>
                                                        <div class="line-food col">
                                                            <div class="add-line-run"></div>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-12 col-sm-12 info-food">
                                                            About
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="price-food col-12 col-sm-2">&#36;<c:out
                                                        value="${dishType.value}"/>
                                                    <input type="number" name="${dishType.key}"
                                                           value="0" min="0" max="100"/>
                                                </div>
                                            </div>

                                        </c:forEach>

                                    </div>


                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </c:forEach>

    <div class="container">
    <div class="wrap-total clearfix" >

        <div class="style-pos col-right">
            <input class="btn-with-bg" name="Create Order" type="submit" value="Create Order">
        </div>

    </div></div>

</form>

</section>

<%@include file="footer.jsp" %>

</body>
</html>

