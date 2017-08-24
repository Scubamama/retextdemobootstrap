<html>

<head>

<title>Confirm Delete Listing</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form method=POST action="/retextdemo/manageUsers/deleteListing?listingId=${listingId}">
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h3>Confirm Delete Listing</h3>
	<!--  		<h3>Message Sent To ${sellerId}</h3>  -->
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					
					<tr><td>Are you sure you want to delete this listing?</td></tr> 
					<tr><td id="browse-button"><input type="submit" name="submit" value="Delete Listing" />  </td></tr>

				<input type="hidden" id="currUserId" name="currUserId" value="${currUserId}">  
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/manageUsers/listings">Cancel</a>  </td></tr>
					<tr></tr>   
					
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

