<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

	<title>Retext Your Profile</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

  	<form action="<%=request.getContextPath() %>/manageUsers/updateProfileForm" method=POST>  
		
	<div class="container" style="background-color: #002147; padding:10px;">
		<div class="well well-lg" style="background-color: #EEEEEE">
	
			<div class="row justify-content-center"><h2>ReText</h2></div>
			<div class="row justify-content-center">
				<p class="small">You can buy, sell, and trade text books on campus</small>
			</div>
			
			<div class="table-responsive">  
				<table class="table table-striped">
			
					<tr>   
						<th>Email</th>
						<th>Username</th>
						<th>Password</th>
						<th>Take Non-Cash</th>
						<th>School</th>
						<th>Campus</th>
	 										
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
							<td>  ${thisUser.takeCardsYN}</td>
							<td>  ${thisUser.userSchool}</td>
							<td>  ${thisUser.userCampus}</td>
							
							<td><a href="<%=request.getContextPath() %>/manageUsers/updateProfileForm?id=${thisUser.id}">Update</a> </td>
							<td><a href="<%=request.getContextPath() %>/manageUsers/deleteProfile?id=${thisUser.id}">Delete</a> </td>
								
						</tr>
						
				</table>
			</div>
			
			<div class="row justify-content-center">
				<div class="center" style="padding:10px">
					<a href="<%=request.getContextPath() %>/loginOut/logOut">Sign Out</a>  
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="center" style="padding:10px">
					<a href="<%=request.getContextPath() %>/manageUsers">Return</a> 
				</div>
			</div>
			
				
			
			
				<div>
					<small>&copy copyright 2017 Holly Williams</small>
				</div>

			</div> <!-- end well -->
			
		</div> <!-- end container -->
	</form>
			    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
</body>

</html>