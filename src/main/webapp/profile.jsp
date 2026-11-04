<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="com.foody.model.user" %>
<%
user User = (user) session.getAttribute("User");

if(User == null){
    response.sendRedirect("login.html");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>

<style>

*{
margin:0;
padding:0;
box-sizing:border-box;
font-family:Arial,sans-serif;
}

body{
background:#f5f5f5;
}

.container{
width:450px;
margin:60px auto;
background:white;
padding:30px;
border-radius:10px;
box-shadow:0 4px 10px rgba(0,0,0,.1);
}

h2{
text-align:center;
margin-bottom:25px;
color:#ff5722;
}

table{
width:100%;
}

td{
padding:12px;
font-size:18px;
}

.label{
font-weight:bold;
}

.back{
display:block;
margin-top:25px;
text-align:center;
text-decoration:none;
background:#ff5722;
color:white;
padding:12px;
border-radius:6px;
}

</style>

</head>

<body>

<div class="container">

<h2>My Profile</h2>

<table>

<tr>
<td class="label">Name</td>
<td><%= User.getName() %></td>
</tr>

<tr>
<td class="label">Email</td>
<td><%= User.getEmail() %></td>
</tr>

<tr>
<td class="label">Address</td>
<td><%= User.getAddress() %></td>
</tr>

<tr>
<td class="label">Role</td>
<td><%= User.getRole() %></td>
</tr>

</table>

<a href="restaurants" class="back">Back to Home</a>

</div>

</body>
</html>