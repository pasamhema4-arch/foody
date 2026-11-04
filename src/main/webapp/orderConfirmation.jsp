<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
double grandTotal = 0;
Object total = session.getAttribute("grandTotal");

if(total != null){
    grandTotal = (Double)total;
}

String paymentMode = (String) session.getAttribute("paymentMode");
if(paymentMode == null || paymentMode.trim().equals("")){
    paymentMode = "Cash On Delivery";
}

String orderId = "FD" + System.currentTimeMillis()%1000000;
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FoodExpress | Order Confirmation</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:linear-gradient(135deg,#fff3e0,#ffe0b2);
}

/* Navbar */

.navbar{
background:linear-gradient(90deg,#ff5722,#ff9800);
padding:18px 8%;
display:flex;
justify-content:space-between;
align-items:center;
color:white;
box-shadow:0 2px 10px rgba(0,0,0,.15);
}

.logo{
font-size:30px;
font-weight:bold;
}

.logo span{
color:#222;
}

.nav-links a{
color:white;
text-decoration:none;
margin-left:25px;
font-weight:500;
}

.nav-links a:hover{
text-decoration:underline;
}

/* Card */

.container{
display:flex;
justify-content:center;
align-items:center;
padding:60px 20px;
}

.card{
width:650px;
background:white;
border-radius:20px;
padding:45px;
text-align:center;
box-shadow:0 10px 30px rgba(0,0,0,.15);
}

.success-icon{
width:100px;
height:100px;
background:#4CAF50;
border-radius:50%;
margin:auto;
display:flex;
justify-content:center;
align-items:center;
font-size:55px;
color:white;
margin-bottom:20px;
}

.card h1{
color:#2e7d32;
margin-bottom:10px;
}

.card p{
color:#555;
margin-bottom:25px;
}

.details{
background:#fff8f2;
border:2px solid #ff9800;
border-radius:12px;
padding:25px;
text-align:left;
margin-bottom:30px;
}

.row{
display:flex;
justify-content:space-between;
padding:12px 0;
border-bottom:1px solid #eee;
}

.row:last-child{
border:none;
}

.label{
font-weight:600;
color:#444;
}

.value{
font-weight:bold;
color:#ff5722;
}

.btns{
display:flex;
justify-content:center;
gap:20px;
}

.btn{
padding:14px 28px;
border:none;
border-radius:8px;
cursor:pointer;
font-size:16px;
font-weight:bold;
text-decoration:none;
transition:.3s;
}

.home{
background:linear-gradient(90deg,#ff5722,#ff9800);
color:white;
}

.track{
background:white;
border:2px solid #ff5722;
color:#ff5722;
}

.btn:hover{
transform:scale(1.05);
}

@media(max-width:700px){

.card{
width:100%;
padding:30px;
}

.btns{
flex-direction:column;
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
<a href="orders.jsp">My Orders</a>
</div>

</div>

<div class="container">

<div class="card">

<div class="success-icon">
✓
</div>

<h1>Order Placed Successfully!</h1>

<p>
Thank you for ordering with FoodExpress.
Your delicious food is being prepared.
</p>

<div class="details">

<div class="row">
<span class="label">Order ID</span>
<span class="value"><%=orderId%></span>
</div>

<div class="row">
<span class="label">Estimated Delivery</span>
<span class="value">30 - 40 Minutes</span>
</div>

<div class="row">
<span class="label">Payment Mode</span>
<span class="value"><%=paymentMode%></span>
</div>

<div class="row">
<span class="label">Amount Paid</span>
<span class="value">&#8377;<%=grandTotal%></span>
</div>

<div class="row">
<span class="label">Order Status</span>
<span class="value">Preparing</span>
</div>

</div>

<div class="btns">

<a href="restaurants" class="btn home">
Back to Home
</a>

<a href="orders.jsp" class="btn track">
Track Order
</a>

</div>

</div>

</div>

</body>
</html>