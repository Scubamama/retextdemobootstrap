<html>

<head>
	<title>Retext Manage Profile</title>
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	
		<div id="container">
			<div id="wrapper">
				<h2>ReText Not Found</h2>
				<h3> ${message} </h3>
				
				<span id="tagline">You can buy, sell, and trade your text books on your campus</span>
				<table>
				
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/listings">Manage Listings</a>  </td></tr>					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/profile">Manage Profile</a>  </td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/actions">More</a>  </td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/logOut">Logout</a>  </td></tr>
					
				</table>
			</div>
		</div>
	
	
		<div id="copyright">
			&copy copyright 2017 Holly Williams
		</div>
	
</body>

</html>