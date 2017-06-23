<html>

<head>

<title>Retext New User Created</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3> ${theUser} has been deleted</h3>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					
					<tr><td>We are sorry to see you go.</td></tr> 
					<tr><td>We will be here if you need us in the future.</td></tr> 
					
					<tr><td>Look for books</td></tr> 
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manage/browse">Browse for Books</a>  </td></tr>
					
					<tr></tr>   
					
				</table>

			</div>
			
			<div id="copyright">
				<small>&copy copyright 2017 Holly Williams</small>
			</div>
			
		</div>

</body>

</html>

