<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>

	<title>Retext Your Listings</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<div class="container" style="background-color: #002147; padding:10px;">
		<div class="well well-lg" style="background-color: #EEEEEE">
	
			<div class="row justify-content-center"><h2>ReText</h2></div>
			<div class="row justify-content-center">
				<p class="small">You can buy, sell, and trade text books on campus</small>
			</div>
	<div class="row justify-content-center"><h4>${message}</h4></div>
			<%List<DisplayUserInventory> disp =(List<DisplayUserInventory>) request.getAttribute("titleList");%>
			<div class="table-responsive">  
				<table class="table table-striped">
					<tr>
						<th>Isbn</th>
						<th>Title</th>
						<th>Author</th>
						<th>Edition</th>
						<th>Price</th>
						<th>Condition</th>
						<th>Sold</th>
						
						<th>Action</th>
					</tr>
						
					<c:forEach var="tempListingsList" items="${listingList}">	
					<!--  -->
						<c:url var="tempLink" value="RetextManageUserInfoServlet"> 
							<c:param name="command" value="viewMessages" />
							<c:param name="senderId" value="${tempListingsList.listingId}" />
						</c:url>
									
						<tr>
							<td>  ${tempListingsList.isbn} </td>
							<td>  ${tempListingsList.title}</td>
							<td>  ${tempListingsList.author}</td>
							<td>  ${tempListingsList.edition}</td>
		<!--  				<td>  ${tempListingsList.price}</td>		-->	
							<td> $ <fmt:formatNumber pattern="#.00" value="${tempListingsList.price}" /></td>

							<td>  ${tempListingsList.condition}</td>
							<td>  ${tempListingsList.sold}</td>

				  				<td><a href="<%=request.getContextPath() %>/manageUsers/updateListingForm?listingId=${tempListingsList.listingId}">Edit</a> </td>
				            	<td><a href="<%=request.getContextPath() %>/manageUsers/deleteListingConfirm?listingId=${tempListingsList.listingId}">Delete</a> </td>
						</tr>
					</c:forEach>
				</table>	
			</div>
			
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">

						<a href="<%=request.getContextPath() %>/manageUsers/addListingForm" class="btn btn-info">Add a Listing</a> 
					</div>
				</div>
			
<!-- 				<a href="<%=request.getContextPath() %>/manageUsers/addListingForm">Add a Listing</a>  -->
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">
			<!--  			<a href="<%=request.getContextPath() %>/loginOut">Login</a>  -->
				   			<a href="<%=request.getContextPath() %>/loginOut/actions" class="btn btn-primary">More</a>  
					</div>
				</div>

	<!--  			<a href="<%=request.getContextPath() %>/loginOut/actions">More</a>    --> 
			
			<div class="small">
				<p class="small">&copy copyright 2017 Holly Williams</p>
			</div>
	
			
		</div> <!-- end well -->
	
	</div> <!-- end container -->
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>

</html>