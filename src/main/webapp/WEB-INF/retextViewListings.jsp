<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

<title>Your Listings</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

<div id="container">
	<h2>ReText</h2>
	
	
	<table id="located_table">
	<!--	<tr><td >  Title Located:  ${title} </td></tr>  -->
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
				<c:param name="listingId" value="${tempListingsList.listingId}" />
			</c:url>
						
			<tr>
				<td>  ${tempListingsList.isbn} </td>
				<td>  ${tempListingsList.title}</td>
				<td>  ${tempListingsList.author}</td>
				<td>  ${tempListingsList.edition}</td>
				<td>  ${tempListingsList.price}</td>
				<td>  ${tempListingsList.condition}</td>
				<td>  ${tempListingsList.sold}</td>
				
		<!-- 		<td><a href="${tempLink}">Contact Seller</a></td>  -->
					<td><a href="<%=request.getContextPath() %>/manageUsers/updateListingForm?listingId=${tempListingsList.listingId}">Edit</a> </td>
					<td><a href="<%=request.getContextPath() %>/manageUsers/deleteListingConfirm?listingId=${tempListingsList.listingId}">Delete</a> </td>
			</tr>
		</c:forEach>
	</table>	

	<h3><a href="<%=request.getContextPath() %>/loginOut/actions">More</a>  </h3>

	<div class="copyright">
		<small>&copy copyright 2017 Holly Williams</small>
	</div>
</div>

</body>

</html>