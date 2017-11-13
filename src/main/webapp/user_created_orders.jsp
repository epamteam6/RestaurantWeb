<%@include file="user_header.jsp" %>

<head><title>Created Order</title></head>

<section>
    <div class="bg-title-sub-page bg-menu-page-01">
        <div class="wrap-title-sub-page">
            <h2 class="title-l">New Order</h2>
            <h6 class="title-s">Home / Your New Orders</h6>
            <h6 class="title-s">${message}</h6>
        </div>
    </div>
</section>

<section class="content-shopping-cart-page">
    <div class="container">
        <form />
        <div class="wrap-table-shopping-cart">
            <table class="table-shopping-cart">
                <tr class="table_head">
                    <th class="column-1">Order</th>
                    <th class="column-2">Order Number</th>
                    <th class="column-3">Name & Description</th>
                    <th class="column-4">Price</th>
                    <th class="column-5">Quantity</th>
                    <th class="column-6">Total</th>
                    <th class="column-6"></th>
                </tr>


                <tr>
                    <td class="column-1">
                        <div class="img-product">
                            <img src="resources/images/shopping-cart-img-product-in-table-01.jpg" alt="img-product" />
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
                            <img src="resources/images/shopping-cart-img-product-in-table-02.jpg" alt="img-product" />
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


        <div class="wrap-total clearfix">
            <div class="style-pos col-right">
                <h6 class="larg-text">Total: $100.00</h6>
                <button class="btn-with-bg">Proceed To Checkout</button>
            </div>
            <div class="style-pos col-left">
                <a href="user_create_order.jsp" class="small-text"><i class="fa fa-angle-left" aria-hidden="true"></i> Continue to shopping</a>
            </div>
        </div>
        </form>
    </div>
</section>


<form method="post" action="user_created_orders">
    <table>
        <tr>
            <td width="112px"><c:out value="Order ID" /></td>
            <td width="112px"><c:out value="Dish and Amount" /></td>
            <td width="112px"><c:out value="Sum" /></td>
        </tr>
        <c:forEach var="item1" items="${usersOrders}" varStatus="i">
            <tr>
                <td width="112px"><c:out value="${item1[0]}" /></td>
                <td width="112px">
                    <c:forEach var="item2" items="${item1[1]}">

                        <c:out value="${item2.key} :" />
                        <c:out value="${item2.value}"/>
                        </br>
                    </c:forEach>
                </td>
                <td width="112px"><c:out value="${item1[2]}" /></td>
            </tr>
        </c:forEach>

    </table>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
