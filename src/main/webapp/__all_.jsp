<h3>Hello, Admin! Confirm orders!</h3>
<form method="post" action="admin_confirmation">
    <table>
        <tr>
            <td width="112px"><c:out value="Number" /></td>
            <td width="112px"><c:out value="UserName" /></td>
            <td width="112px"><c:out value="Order ID" /></td>
            <td width="112px"><c:out value="Dish and Amount" /></td>
            <td width="112px"><c:out value="Sum" /></td>
        </tr>
        <c:forEach var="item1" items="${usersOrders}" varStatus="i">
            <tr>
                <td width="112px"><c:out value="${i.count}" /></td>
                <td width="112px"><c:out value="${item1[0]}" /></td>
                <td width="112px"><c:out value="${item1[1]}" /></td>


                <td width="112px">
                    <c:forEach var="item2" items="${item1[2]}">

                        <c:out value="${item2.key} :" />
                        <c:out value="${item2.value}"/>
                        </br>
                    </c:forEach>
                </td>


                <td width="112px"><c:out value="${item1[3]}" /></td>

                <td><input type="checkbox" name="${item1[1]}" ></td>
            </tr>
        </c:forEach>

        <tr>
            <td colspan="4" align="right"><input name="Confirm" type="submit" value="Confirm"></td>
        </tr>
        <tr>
            <td colspan="4" align="right"><input name="Cancel" type="submit" value="Cancel"></td>
        </tr>

    </table>
</form>

<td class="column-6">
    <input class="input-num-product small-text" type="text" name="num-product" value="1" placeholder="1" />
</td>


<div class="style-pos col-right">
    <button class="btn-with-bg" name="Confirm" type="submit">Confirm</button>

</div>

<div class="style-pos col-right">
    <button class="btn-with-bg-white" name="Cancel" type="submit">Cancel</button> &nbsp;&nbsp;&nbsp;
</div>