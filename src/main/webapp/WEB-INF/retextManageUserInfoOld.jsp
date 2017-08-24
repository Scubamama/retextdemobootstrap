<html>

<head>
	<title>Retext Manage My Stuff</title>
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form method=GET action="/retextdemo/manageUsers">
		<div id="container">
			<div id="wrapper">
				<h2>ReText Manage Your Listings And Profile</h2>
				<span id="tagline">You can buy, sell, and trade your text books on your campus</span>
				<table>
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manageUsers/listings">Manage My Listings</a>  </td></tr>					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manageUsers/profile">Manage My Profile</a>  </td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/actions">More</a>  </td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/logOut">Logout</a>  </td></tr>
				
				</table>
			</div>
		</div>
	</form>
	
		<div id="copyright">
			&copy copyright 2017 Holly Williams
		</div>
	
</body>

</html>