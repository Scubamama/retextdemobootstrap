<html>

<head>

<title>Retext New User Created</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form method=GET action="/retextdemo/manage/browse">
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3>User Logged In</h3>
				<h3>Welcome ${theUser}</h3>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					
					<tr><td>What would you like to do?</td></tr> 
					<tr><td>Look for books</td></tr> 
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manage/browse">Browse for Books</a>  </td></tr>
					
					<tr></tr>   
					<tr><td>Manage your profile</td></tr> 
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manageUsers">Manage User Info</a>  </td></tr>
					<tr></tr>
				</table>

			</div>
			
			<div id="copyright">
				<small>&copy copyright 2017 Holly Williams</small>
			</div>
			
		</div>
	</form>
</body>

</html>

