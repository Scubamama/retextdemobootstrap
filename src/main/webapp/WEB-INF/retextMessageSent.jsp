<html>

<head>

<title>Message Sent to Seller</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form method=GET action="/retextdemo/manage/browse">
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3>Message Sent</h3>
	<!--  		<h3>Message Sent To ${sellerId}</h3>  -->
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					
					<tr><td>What would you like to do?</td></tr> 
					<tr><td>Look For More Books</td></tr> 
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manage/browse">Browse for Books</a>  </td></tr>
					
					<tr></tr>   
					<tr><td>Go Back to Message Other Users</td></tr> 
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manage/browse">Message Other Users</a>  </td></tr>
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

