<html>

<head>

<title>Retext Update User Info</title>
<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	<form action="<%=request.getContextPath() %>/manageUsers/updateProfile" method="POST">
			<div id="container">
				<div id="wrapper">
					<h2>ReText</h2>
					<h3>Change fields </h3>
					<input type="hidden" name="currUserId" value="${thisUser.id}"/>
					<table id="login_table">
					
						<tr><td id="in_field">Email Address: <input name="email"  type="email" value="${thisUser.userEmail}" /> </td></tr>
						
						<tr><td>User Name: <input type="text" name="userName" value="${thisUser.userName}" /></td></tr>
						<tr><td>(user name must be at least 10 characters)</td></tr>
						<tr><td>School Name: <input type="text" name="schoolName" value="${thisUser.userSchool}"/> </td></tr>
						<tr><td>Password: <input type="password" name="password" value="${thisUser.userPassword}"/> </td></tr>
						<tr><td id="in_field">(password must be at least 10 characters)</td></tr>
						<tr><td id="in_field">Confirm Password: <input type="password" name="${passwordconfirm}" /> </td></tr>
						<tr><td>Are you able to take credit/debit cards? (y/n): <input type="text" name="takeCardsYN" value="${thisUser.takeCardsYN}"/> </td></tr>
						
						<tr><td id="browse-button"><input type="submit" name="Create" value="Update User Info" /></td></tr>
							
					</table>
				</div>
			</div>
	</form>

</body>

</html>