<html>

<head>
	<title>Retext Welcome</title>
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form method=GET action="/retextdemo/manage/browse">
		<div id="container">
			<div id="wrapper">
				<h2>Welcome to ReText</h2>
				<span id="tagline">You can buy, sell, and trade your text books on your campus</span>
				<table>
					
					<tr><td><input id="browse-button" type="submit" name="Browse" value="Browse Books"  /></td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/login">Login</a>  </td></tr>
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/newUser/*">Create New User Id</a>  </td></tr>
				</table>
			</div>
		</div>
	</form>
	
		<div id="copyright">
			&copy copyright 2017 Holly Williams
		</div>
	
</body>

</html>