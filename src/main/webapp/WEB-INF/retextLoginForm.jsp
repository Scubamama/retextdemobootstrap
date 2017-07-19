<html>

<head>

<title>Retext Login</title>
<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
<!-- 	<form action="WEB-INF/retextUserLoggedIn.jsp" method=POST> -->	
  	<form action="<%=request.getContextPath() %>/loginOut/save" method=POST>  
	
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3>Log In</h3>
						
				<table id="login_table">
					<tr><td><span id="tagline">You can buy, sell, and trade your text books on your campus</span></td></tr>
				
					<tr><td id="cntr">User Name: <input type="input" name="userName" placeholder="User Name"/> </td></tr>
					
					<tr><td id="cntr">Password: <input type="password" name="password" placeholder="Password"/> </td></tr>
					
			<!-- 		<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut">Login</a> </td></tr>   -->
					
					<tr><td id="browse-button"><input type="submit" name="Login" value="Log Me In" /></td></tr>
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/newUser/*">Create New User Id</a>  </td></tr>
				
				
				</table>
	<!--  			<p style="color:red; font-size:24px;"> "${warning}" </p>  -->
	<!-- 				<div id="warning"> "${warning}" </div>  -->
				<input type="hidden" id="condition" name="isbn" value="${isbn}">  
				
			</div>
		</div>
	</form>
	
	<div id="copyright">
		&copy copyright 2017 Holly Williams
	</div>
</body>

</html>