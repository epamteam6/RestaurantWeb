<%@include file="admin_header.jsp" %>

<head><title>Payment Waiting</title></head>

<section>
    <div class="bg-title-sub-page bg-menu-page-01">
        <div class="wrap-title-sub-page">
            <h2 class="title-l"><fmt:message key="admin_ready.title" /></h2>
            <h6 class="title-s"><fmt:message key="admin_ready.slide" /></h6>
        </div>
    </div>
</section>

<section class="content-shopping-cart-page">
    <div class="container">
        <h6 class="title-s">${message}</h6>

        <form method="post" action="admin_ready_orders">
            <div class="wrap-table-shopping-cart">

                <table class="table-shopping-cart">
                    <tr class="table_head">
                        <th class="column-1"></th>
                        <th class="column-3"><fmt:message key="admin_confirmation.number" /></th>
                        <th class="column-3"><fmt:message key="admin_confirmation.name" /></th>
                        <th class="column-2"><fmt:message key="admin_confirmation.desc" /></th>
                        <th class="column-4"><fmt:message key="admin_confirmation.total" /></th>
                        <th class="column-6"></th>
                    </tr>

                    <c:forEach var="item1" items="${usersOrders}" varStatus="i">
                        <tr>
                            <td class="column-1">
                                <div class="img-product">
                                    <img src="resources/images/shopping-cart-img-product-in-table-02.jpg" alt="img-product" />

                                </div>
                            </td>

                            <td class="column-3"><c:out value="${item1[1]}" /> </td>
                            <td class="column-3"><c:out value="${item1[0]}" /> </td>

                            <td class="column-2">
                                <c:forEach var="item2" items="${item1[2]}">
                                    <c:out value="${item2.key} :" />
                                    <c:out value="${item2.value}"/>
                                    </br>
                                </c:forEach>
                            </td>

                            <td class="column-4">$ <c:out value="${item1[3]}" /></td>

                            <td class="column-6">
                                <input type="checkbox" name="${item1[1]}" >
                            </td>
                        </tr>
                    </c:forEach>

                </table>
            </div>

            <br>
            <div class="wrap-total clearfix" >


                <div class="style-pos col-right">
                    <input class="btn-with-bg" name="Cancel" type="submit" value="<fmt:message key="admin_confirmation.delete" />">

                </div>


            </div>
        </form>
    </div>
</section>

<%@include file="footer.jsp" %>

</body>
</html>




