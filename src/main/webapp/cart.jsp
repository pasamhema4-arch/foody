<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.foody.model.cart"%>
<%@ page import="com.foody.model.cartItem"%>
<%@ page import="java.util.Map"%>

<%
cart cart = (cart) session.getAttribute("Cart");
double grandTotal = 0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FoodExpress | Cart</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}

body{
    background:#f4f6f9;
}

/* Navbar */

.navbar{
    width:100%;
    background:#ffffff;
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:18px 8%;
    box-shadow:0 2px 10px rgba(0,0,0,0.08);
    position:sticky;
    top:0;
    z-index:100;
}

.logo{
    font-size:30px;
    font-weight:bold;
    color:#ff5722;
}

.logo span{
    color:#222;
}

.nav-links a{
    text-decoration:none;
    color:#333;
    margin-left:30px;
    font-weight:500;
    transition:.3s;
}

.nav-links a:hover{
    color:#ff5722;
}

/* Cart */

.container{
    width:90%;
    max-width:1200px;
    margin:40px auto;
}

.container h1{
    text-align:center;
    margin-bottom:30px;
    color:#333;
}

table{
    width:100%;
    border-collapse:collapse;
    background:#fff;
    border-radius:12px;
    overflow:hidden;
    box-shadow:0 5px 15px rgba(0,0,0,.1);
}

th{
    background:#ff5722;
    color:white;
    padding:16px;
}

td{
    text-align:center;
    padding:18px;
    border-bottom:1px solid #ddd;
}

.qty-btn{
    width:35px;
    height:35px;
    border:none;
    border-radius:50%;
    background:#ff5722;
    color:white;
    cursor:pointer;
    font-size:18px;
}

.remove-btn{
    background:#dc3545;
    color:white;
    border:none;
    padding:8px 15px;
    border-radius:6px;
    cursor:pointer;
}

.total-box{
    margin-top:30px;
    text-align:right;
    font-size:24px;
    font-weight:bold;
    color:#333;
}

.buttons{
    display:flex;
    justify-content:space-between;
    margin-top:30px;
}

.btn{
    padding:15px 28px;
    text-decoration:none;
    border-radius:8px;
    color:white;
    font-size:17px;
    font-weight:bold;
}

.add-btn{
    background:#28a745;
}

.checkout-btn{
    background:#ff5722;
}

.empty-cart{
    background:white;
    padding:60px;
    text-align:center;
    border-radius:12px;
    box-shadow:0 5px 15px rgba(0,0,0,.1);
}

.empty-cart h2{
    color:#555;
    margin-bottom:20px;
}

.empty-cart a{
    display:inline-block;
    margin-top:20px;
    background:#ff5722;
    color:white;
    padding:14px 30px;
    text-decoration:none;
    border-radius:8px;
}
.qty-box{
    display:flex;
    justify-content:center;
    align-items:center;
    gap:12px;
}

.qty-box form{
    margin:0;
}

.qty-number{
    font-size:18px;
    font-weight:bold;
    min-width:30px;
    text-align:center;
}

.qty-btn{
    width:35px;
    height:35px;
    border:none;
    border-radius:50%;
    background:#ff5722;
    color:white;
    font-size:20px;
    cursor:pointer;
}
</style>

</head>

<body>

<div class="navbar">

    <div class="logo">
        Food<span>Express</span>
    </div>

    <div class="nav-links">
        <a href="restaurants">Home</a>
        <a href="cart.jsp">Cart</a>
    </div>

</div>

<div class="container">

<h1>Your Cart</h1>

<%
if(cart==null || cart.getItems().isEmpty()){
%>

<div class="empty-cart">
    <h2>Your Cart is Empty</h2>
    <p>Add delicious food to your cart.</p>

    <a href="restaurants">
        Add More Items
    </a>
</div>

<%
}else{
%>

<table>

<tr>
    <th>Item</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Total</th>
    <th>Action</th>
</tr>

<%
for(cartItem item : cart.getItems().values()){

double total=item.getPrice()*item.getQty();
grandTotal+=total;
%>
<tr>

    <td><%= item.getName() %></td>

    <td>₹<%= item.getPrice() %></td>

   <td>

<div class="qty-box">

    <form action="CartServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
        <input type="hidden" name="quantity" value="<%= item.getQty()-1 %>">
        <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
        <button type="submit" class="qty-btn">-</button>
    </form>

    <span class="qty-number"><%= item.getQty() %></span>

    <form action="CartServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
        <input type="hidden" name="quantity" value="<%= item.getQty()+1 %>">
        <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
        <button type="submit" class="qty-btn">+</button>
    </form>

</div>

</td>
    <td>₹<%= total %></td>

    <td>

        <form action="CartServlet" method="post">

            <input type="hidden" name="action" value="remove">

            <input type="hidden"
                   name="menuId"
                   value="<%= item.getMenuId() %>">

            <button class="remove-btn">
                Remove
            </button>

        </form>

    </td>

</tr>

<%
}
%>

</table>

<div class="total-box">

Grand Total : ₹<%= grandTotal %>

</div>

<div class="buttons">

<a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>"
   class="btn add-btn">
    Add More Items
</a>

<a href="checkout.jsp" class="btn checkout-btn">
    Proceed to Checkout
</a>
   

</div>

<%
}
%>

</div>

</body>
</html>