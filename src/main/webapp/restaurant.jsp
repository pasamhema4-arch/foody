<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.foody.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurants</title>

<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:'Poppins',sans-serif;
}

body{
background:#f8f8f8;
}

/* Navbar */

nav{
height:80px;
background:white;
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
color:#333;
font-weight:500;
margin-left:35px;
transition:.3s;
}

.nav-links a:hover{
color:#fc8019;
}

/* Hero */

.hero{

height:260px;
background:linear-gradient(135deg,#fc8019,#ffb347);
display:flex;
flex-direction:column;
justify-content:center;
align-items:center;
color:white;
}

.hero h1{

font-size:40px;
margin-bottom:20px;

}

/* Caption */

.caption{

font-size:20px;
font-weight:400;
text-align:center;
color:white;
letter-spacing:.5px;
opacity:.95;

}

/* Heading */

.heading{

padding:40px 70px 20px;

}

.heading h2{

font-size:32px;

}

/* Cards */

.container{

padding:0 70px 50px;
display:grid;
grid-template-columns:repeat(auto-fit,minmax(270px,1fr));
gap:30px;

}

.card{

background:white;
border-radius:15px;
overflow:hidden;
transition:.4s;
cursor:pointer;

}

.card:hover{

transform:translateY(-8px);
box-shadow:0 15px 30px rgba(0,0,0,.15);

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

font-size:22px;
margin-bottom:8px;

}

.type{

color:gray;
font-size:14px;
margin-bottom:12px;

}

.info{

display:flex;
justify-content:space-between;
margin-bottom:15px;
font-size:15px;

}

.rating{

background:#48c479;
color:white;
padding:4px 10px;
border-radius:8px;

}

.offer{

color:#fc8019;
font-weight:600;
margin-bottom:15px;

}

button{

width:100%;
padding:12px;
border:none;
background:#fc8019;
color:white;
font-size:16px;
border-radius:8px;
cursor:pointer;
transition:.3s;

}

button:hover{

background:#e86f0d;

}

/* Responsive */

@media(max-width:768px){

nav{

padding:20px;

}

.hero h1{

font-size:28px;

}

.caption{

font-size:16px;
padding:0 20px;

}

.container{

padding:20px;

}

.heading{

padding:30px 20px;

}

}

</style>

</head>

<body>

<nav>

<div class="logo">FoodExpress</div>

<div class="nav-links">

<a href="restaurants">Home</a>
<a href="login.html">Sign In</a>
<a href="register.html">Sign Up</a>
<a href="cart.jsp">Cart</a>
<a href="profile">Profile</a>

</div>

</nav>



<div class="hero">

    <h1>Welcome to FoodExpress</h1>

    <p class="caption">
        Order from your favorite restaurants and enjoy fresh food, fast delivery, and exclusive offers—right at your doorstep. 🍕🍔🍜
    </p>

</div>



<div class="heading">

<h2>Top Restaurants</h2>

</div>

<div class="container">

<%
@SuppressWarnings("unchecked")

List<Restaurant> restaurantList = (List<Restaurant>)request.getAttribute("restaurantList");

for (Restaurant restaurant : restaurantList) {
%>

<div class="card">

    <img src=<%=restaurant.getImagePath() %>>

    <div class="details">

        <h3><%= restaurant.getName() %></h3>

        <p class="type">Pizza • Italian</p>

        <div class="info">
            <span class="rating">★ <%= restaurant.getRating() %></span>
            <span class="time"><%= restaurant.getDeliveryTime() %> mins</span>
        </div>

        <p class="cuisine"><%= restaurant.getCuisineType() %></p>

        <p class="address"><%= restaurant.getAddress() %></p>

        <p class="offer">50% OFF up to ₹120</p>

        <button onclick="location.href='menu?restaurantId=<%=restaurant.getRestaurantId()%>'">
        View Menu
        </button>>
    </div>

</div>

<%
}
%>

</div>

</body>
</html>         