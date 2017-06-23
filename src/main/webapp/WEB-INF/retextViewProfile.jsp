<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

<title>Your Profile</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

  	<form action="<%=request.getContextPath() %>/manageUsers/updateProfile" method=POST>  
		
		<div id="container">
			<h2>ReText</h2>
			
			<table id="located_table">
			<!--	<tr><td >  Title Located:  ${title} </td></tr>  -->
				<tr>   
					<th>Email</th>
					<th>Username</th>
					<th>Password</th>
					<th>Take Cards</th>
					<th>School</th>
 										
					<th>Action</th>
				</tr>
				<!--  -->
			
					<c:url var="tempLink" value="RetextManageUserInfoServlet"> 
						<c:param name="command"  value="updateProfile" />
						<c:param name="userId"   value="${tempUser.id}" />
						<c:param name="tempUser" value="${thisUser}"/>
						
					</c:url>
								
					<tr>
						<td>  ${thisUser.userEmail} </td>
						<td>  ${thisUser.userName}</td>
						<td>  ${thisUser.userPassword}</td>
						<td>  ${thisUser.takeCards}</td>
						<td>  ${thisUser.userSchool}</td>
						
				<!-- 		<td><a href="${tempLink}">Contact Seller</a></td>  -->
							<td><a href="<%=request.getContextPath() %>/manageUsers/updateProfile?id=${thisUser.id}">Update</a> </td>
							<td><a href="<%=request.getContextPath() %>/manageUsers/deleteProfile?id=${thisUser.id}">Delete</a> </td>
					</tr>
					
			</table>	
		
			<div class="copyright">
				<small>&copy copyright 2017 Holly Williams</small>
			</div>
		</div>
	</form>
</body>

</html>