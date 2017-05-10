<html>

<head>

<title>Retext Login</title>
<link type="text/css" rel="stylesheet" href="css/reTextStyle.css">
</head>

<body>
	<form action="retext-login-response.jsp">
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3>Log In</h3>
						
				<table id="login_table">
					<tr><td><span id="tagline">You can buy, sell, and trade your text books on your campus</span></td></tr>
				
					<tr><td id="cntr">User Name: <input type="email" name="userName" placeholder="User Name"/> </td></tr>
					
					<tr><td id="cntr">Password: <input type="password" name="password" placeholder="Password"/> </td></tr>
					
					<tr><td class="center_button"><input type="button" name="Create" value="Create New User Id" /></td></tr>
					
					<tr><td class="center_button""><input type="submit" name="Login" value="Login"/></td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/located/titleLocated">Login</a>  </td></tr>
				</table>
			</div>
		</div>
	</form>
	
	<div id="copyright">
		&copy copyright 2017 Holly Williams
	</div>
</body>

</html>