<html>

<head>

<title>Retext Add A Listing</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form action="<%=request.getContextPath() %>/manageUsers/addListing" method=POST>
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<div id="find">Add a Listing</div>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					<tr><td>School: <input type="text" name="school" /> </td></tr>
					<tr><td>Campus: <input type="input" name="campus" /> </td></tr>  
					
					<tr><td>ISBN: <input type="input" name="isbn" value="${isbn}"/> </td></tr>
					<tr><td>Price: <input type="input" name="price" value="${price}"/> </td></tr>
					<tr><td id="in_field"> Condition: 
						<select name="condition">
							<option>Poor</option>
							<option>Fair</option>
							<option>Good</option>
							<option>Very Good</option>
							<option>Like New</option>
							<option>New</option>
						
						</select>
</td></tr>
					
					<tr><td>    </td></tr>
					<tr><td id="browse-button"> <input type="submit" name="addbutt" value="Add the Listing" >   </td></tr>
					
					<tr></tr>   
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut/actions">More Actions</a>  </td></tr>
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

