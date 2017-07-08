<html>

<head>

<title>Retext Update A Listing</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form action="<%=request.getContextPath() %>/manageUsers/updateListing" method=POST>
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<div id="find">Update this Listing</div>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				<input type="hidden" name="listingId" value="${thisListing.id}"/>
				
				<table id="find_table">
					
			<!-- 	<tr><td>ISBN: <input type="text" name="isbn" value="${isbn}"/> </td></tr>   -->	
			<!-- 		<tr><td>Condition: <input type="input" name="condition"  value="${condition}"/> </td></tr>  -->
					<tr><td>Price: <input type="input" name="price" value="${thisListing.price}"/> </td></tr>
					<tr><td id="in_field"> Condition: 
						<select name="bookCondition">
							<option>Poor</option>
							<option>Fair</option>
							<option>Good</option>
							<option>Very Good</option>
							<option>Like New</option>
							<option>New</option>
						
						</select>
</td></tr>
					
					<tr><td>    </td></tr>
					<tr><td id="browse-button"> <input type="submit" name="addbutt" value="Update the Listing" >   </td></tr>
					
					<tr></tr>   
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/actions">More Things To Do</a>  </td></tr>
<!-- 				<h3><a href="<%=request.getContextPath() %>/loginOut/actions">More</a>  </h3>  -->
					
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

