<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	  <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Product Details</title>
</head>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="home"></a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="home">Home</a></li>
   
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="Logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
      <div class="container">
            <table class="table table-hover table-bordered">
            <tr><td rowspan="5">
            <img src='<x:url value="/resources/images/${product.image}"/>'/></td></tr>
            <tr><td>${product.id}</td></tr>
            <tr><td>${product.name}</td></tr>
            <tr><td>${product.price}</td></tr>
            <tr><td>${product.description}</td></tr>
            
            <sec:authorize access="hasRole('ROLE_USER')">             
            <tr><td></td><td><a href="viewcart?id=${product.id}"class="btn btn-primary">Add to Cart</a>
              </td></tr>
              </sec:authorize>               
            </table>
      </div>
      </div>
		<footer class="container-fluid text-center">
		<p>�MuZiKa SVeT-All Rights Reserved</p>
		<P>2016</P>
	</footer>
	
</body>
</html>
      
   </body>
</html>
