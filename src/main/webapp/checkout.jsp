<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.foody.model.cart"%>
<%@ page import="com.foody.model.cartItem"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodExpress | Checkout</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:'Poppins',sans-serif;
}

body{
    background:linear-gradient(135deg,#fff3e0,#ffe0b2);
    min-height:100vh;
}
/* Navbar */

.navbar{
     background:linear-gradient(90deg,#ff5722,#ff9800);
    color:white;
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:18px 8%;
    box-shadow:0 2px 10px rgba(0,0,0,.08);
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
    margin-left:25px;
    font-weight:500;
}

.nav-links a:hover{
    color:#ff5722;
}

/* Main */

.checkout-container{
    width:95%;
    max-width:1500px;
    margin:30px auto;
}
#checkoutForm{
    width:100%;
    display:grid;
    grid-template-columns:2fr 1fr;
    gap:30px;
    align-items:start;
}

@media(max-width:900px){
    #checkoutForm{
        grid-template-columns:1fr;
    }
}
/* Left */

.checkout-form{
    background:#fff;
    padding:35px;
    border-radius:15px;
    box-shadow:0 5px 15px rgba(0,0,0,.1);
    width:100%;
    min-height:650px;
}

.checkout-form h2{
    margin-bottom:25px;
    color:#333;
}

.input-group{
    margin-bottom:22px;
}

.input-group label{
    display:block;
    margin-bottom:8px;
    font-weight:600;
    color:#444;
}

.input-group input,
.input-group textarea,
.input-group select{
    width:100%;
    padding:14px;
    border:1px solid #ccc;
    border-radius:8px;
    font-size:15px;
    outline:none;
}

.input-group textarea{
    resize:none;
    height:120px;
}

.input-group input:focus,
.input-group textarea:focus,
.input-group select:focus{
    border-color:#ff5722;
}

/* Right */
.order-summary{
    background:linear-gradient(135deg,#ffffff,#fff8f0);
    border:2px solid #ff9800;
    border-radius:15px;
    padding:30px;
    box-shadow:0 5px 15px rgba(0,0,0,.1);
    width:100%;
    min-height:650px;
}
.order-summary h2{
    margin-bottom:25px;
    color:#333;
}

.item{
    display:flex;
    justify-content:space-between;
    margin-bottom:18px;
    padding-bottom:12px;
    border-bottom:1px solid #eee;
}

.item-name{
    font-weight:600;
    color:#444;
}

.item-price{
    color:#ff5722;
    font-weight:bold;
}

.summary-row{
    display:flex;
    justify-content:space-between;
    margin:14px 0;
    color:#555;
    font-size:16px;
}

.total{
    margin-top:20px;
    border-top:2px dashed #ddd;
    padding-top:18px;
    display:flex;
    justify-content:space-between;
    font-size:22px;
    font-weight:bold;
    color:#222;
}

.place-order{
    width:100%;
    margin-top:30px;
    padding:16px;
    background:#ff5722;
    color:white;
    border:none;
    border-radius:10px;
    font-size:18px;
    cursor:pointer;
    transition:.3s;
}

.place-order{
    background:linear-gradient(90deg,#ff5722,#ff9800);
    color:white;
    font-weight:bold;
    box-shadow:0 5px 15px rgba(255,87,34,0.4);
}
.place-order:hover{
    transform:scale(1.03);
    background:linear-gradient(90deg,#e64a19,#ff5722);
}

/* Responsive */

@media(max-width:900px){

.checkout-container{
    grid-template-columns:1fr;
}

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



<div class="checkout-container">

<form id="checkoutForm" action="checkout" method="post">

<!-- LEFT -->

<div class="checkout-form">

<h2>Delivery Details</h2>


<div class="input-group">
<label>Full Name</label>
<input type="text" name="fullName" placeholder="Enter your name" required>
</div>


<div class="input-group">
<label>Delivery Address</label>
<textarea name="address" placeholder="Enter your complete address" required></textarea>
</div>


<div class="input-group">
<label>Phone Number</label>
<input type="tel" name="phone" placeholder="Enter your phone number" required>
</div>


<div class="input-group">

<label>Payment Mode</label>

<select name="paymentMode" required>

<option value="">Select Payment Mode</option>

<option value="UPI">UPI</option>

<option value="Cash On Delivery">
Cash On Delivery
</option>

<option value="Credit Card">
Credit Card
</option>

<option value="Debit Card">
Debit Card
</option>

<option value="Net Banking">
Net Banking
</option>

</select>

</div>


</div>



<!-- RIGHT -->

<div class="order-summary">


<h2>Order Summary</h2>


<%
cart Cart = (cart) session.getAttribute("Cart");

double itemTotal=0;
double dc=40;
double pf=10;
double gst=44;


if(Cart != null && !Cart.getItems().isEmpty())
{
    for(cartItem item: Cart.getItems().values())
    {
        itemTotal += item.getPrice() * item.getQty();
    }
}


double grandTotal=itemTotal + dc + pf + gst;

session.setAttribute("grandTotal", grandTotal);

%>



<%
if(Cart != null && !Cart.getItems().isEmpty())
{
    for(cartItem item: Cart.getItems().values())
    {
%>


<div class="item">

<div class="item-name">

<%= item.getName() %><br>

Quantity: <%= item.getQty() %>

</div>


<div class="item-price">

&#8377;<%= item.getPrice() * item.getQty() %>

</div>


</div>


<%
    }
}
%>




<div class="summary-row">

<span>
Items Total
</span>

<span>
&#8377;<%=itemTotal%>
</span>

</div>



<div class="summary-row">

<span>
Delivery Charge
</span>

<span>
&#8377;<%=dc%>
</span>

</div>



<div class="summary-row">

<span>
GST (5%)
</span>

<span>
&#8377;<%=gst%>
</span>

</div>



<div class="summary-row">

<span>
Platform Fee
</span>

<span>
&#8377;<%=pf%>
</span>

</div>



<div class="total">

<span>
Grand Total
</span>

<span>
&#8377;<%=grandTotal%>
</span>

</div>
<%
System.out.println("Grand Total in JSP = " + grandTotal);
System.out.println("Session Grand Total = " + session.getAttribute("grandTotal"));
%>



<button type="submit" class="place-order">
    Place Order
</button>


</div>


</form>

</div>



</body>
</html>