<html>

<head>
	<title>Retext Manage Listings</title>
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form method=POST action="/retextdemo/manageUsers">
		<div id="container">
			<div id="wrapper">
				<h2>ReText Manage Your Profile</h2>
				<span id="tagline">You can buy, sell, and trade your text books on your campus</span>
				<table>
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/listings">Delete A Listing</a>  </td></tr>					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/profile">Edit A Listing </a>  </td></tr>
				</table>
			</div>
		</div>
	</form>
	
		<div id="copyright">
			&copy copyright 2017 Holly Williams
		</div>
	
</body>

</html>