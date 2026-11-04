<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List,com.foody.model.Menu" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Menu</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:#f5f5f5;
}

/* Navbar */

nav{
height:80px;
background:#fff;
display:flex;
justify-content:space-between;
align-items:center;
padding:0 70px;
box-shadow:0 2px 15px rgba(0,0,0,.1);
position:sticky;
top:0;
z-index:100;
}

.logo{
font-size:30px;
font-weight:bold;
color:#fc8019;
}

.nav-links a{
text-decoration:none;
margin-left:35px;
font-weight:500;
color:#333;
transition:.3s;
}

.nav-links a:hover{
color:#fc8019;
}

/* Banner */

.banner{
height:220px;
background:linear-gradient(135deg,#fc8019,#ffb347);
display:flex;
justify-content:center;
align-items:center;
flex-direction:column;
color:white;
}

.banner h1{
font-size:40px;
margin-bottom:10px;
}

.banner p{
font-size:18px;
}

/* Heading */

.heading{
padding:40px 70px 20px;
}

.heading h2{
font-size:30px;
}

/* Cards */

.container{
padding:0 70px 50px;
display:grid;
grid-template-columns:repeat(auto-fit,minmax(280px,1fr));
gap:30px;
}

.card{
background:white;
border-radius:15px;
overflow:hidden;
transition:.3s;
cursor:pointer;
}

.card:hover{
transform:translateY(-8px);
box-shadow:0 12px 25px rgba(0,0,0,.15);
}

.card img{
width:100%;
height:220px;
object-fit:cover;
}

.details{
padding:18px;
}

.details h3{
font-size:24px;
margin-bottom:10px;
}

.rating{
display:inline-block;
background:#48c479;
color:white;
padding:5px 10px;
border-radius:8px;
font-size:14px;
margin-bottom:12px;
}

.price{
font-size:22px;
font-weight:600;
color:#fc8019;
margin-bottom:10px;
}

.desc{
font-size:15px;
color:#666;
line-height:24px;
margin-bottom:20px;
}
.available{
    display:inline-block;
    padding:6px 12px;
    background:#48c479;
    color:white;
    border-radius:6px;
    font-size:14px;
    font-weight:600;
    margin-bottom:15px;
}

.not-available{
    display:inline-block;
    padding:6px 12px;
    background:#e53935;
    color:white;
    border-radius:6px;
    font-size:14px;
    font-weight:600;
    margin-bottom:15px;
}       

button{
width:100%;
padding:12px;
background:#fc8019;
border:none;
color:white;
font-size:16px;
border-radius:8px;
cursor:pointer;
transition:.3s;
}

button:hover{
background:#e76f00;
}

/* Responsive */

@media(max-width:768px){

nav{
padding:0 20px;
}

.heading{
padding:30px 20px;
}

.container{
padding:20px;
}

.banner h1{
font-size:28px;
}

}

</style>

</head>

<body>

<nav>

<div class="logo">
FoodExpress
</div>

<div class="nav-links">

<a href="restaurants">Home</a>
<a href="cart.jsp">Cart</a>
<a href="orders">Orders</a>

</div>

</nav>

<div class="banner">

<h1>Restaurant Menu</h1>

<p>Fresh • Delicious • Delivered Fast 🍔🍕🍜</p>

</div>

<div class="heading">

<h2>Explore Menu</h2>

</div>

<div class="container">

<%

@SuppressWarnings("unchecked")
List<Menu> menuList=(List<Menu>)request.getAttribute("menuList");

if(menuList!=null){

for(Menu menu:menuList){

%>

<div class="card">

<img src="<%=menu.getImagePath()%>">

<div class="details">

<h3><%= menu.getItemName() %></h3>



<div class="price">
₹ <%=menu.getPrice()%>
</div>

<p class="desc">
<%=menu.getDescription()%>
</p>

<p class="<%= menu.getisAvailable() ? "available" : "not-available" %>">
    <%= menu.getisAvailable() ? "Available" : "Not Available" %>
</p>

<form action="CartServlet" method="post">
<input type=hidden name=menuId value=<%=menu.getMenuId()%>>
<input type=hidden name=restaurantId value=<%=menu.getRestaurantId()%>>
<input type=hidden name=qty value=1>
<input type=hidden name=action value=add>
<input type="submit" value=" Add to cart">

</form>

</div>

</div>

<%

}

}


else{
%>

<h2>No Menu Items Found</h2>

<%
}
%>

</div>

</body>
</html>