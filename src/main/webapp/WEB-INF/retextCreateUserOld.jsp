<html>

<head>

<title>Retext Create User Id</title>
<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	<form action="WEB-INF/retextUserCreated.jsp" method="POST">
			<div id="container">
				<div id="wrapper">
					<h2>ReText</h2>
					<h3>Welcome! Please create a User Id</h3>
					
					<table id="login_table">
						<tr><td id="in_field">Email Address: <input type="email" name="email" /> </td></tr>
						
						<tr><td>User Name: <input type="input" name="userName" /> </td></tr>
						<tr><td>(user name must be at least 10 characters)</td></tr>
						<tr><td>School Name: <input type="input" name="schoolName" /> </td></tr>
						<tr><td>School Nick Name: <input type="input" name="schoolNickName" /> </td></tr>
						<tr><td>Password: <input type="password" name="password" /> </td></tr>
						<tr><td id="in_field">(password must be at least 10 characters)</td></tr>
						<tr><td id="in_field">Confirm Password: <input type="password" name="passwordconfirm" /> </td></tr>
						<tr><td>Are you able to take credit/debit cards? (y/n): <input type="input" name="takeCards" /> </td></tr>
						
						<tr><td id="browse-button"><input type="submit" name="Create" value="Create new User Id" /></td></tr>
							
					</table>
				</div>
			</div>
	</form>

</body>

</html>