<html>

<head>

<title>Retext Add A Book Title</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form action="<%=request.getContextPath() %>/manageUsers/addTitle" method=POST>
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<h2>That title is not in our database. Please add it.</h2>
				
				<div id="find">Add a Book Title</div>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					
					<tr><td>ISBN: <input type="input" name="isbn" value="${isbn}"/> </td></tr>
					<tr><td>Title: <input type="input" name="title" /> </td></tr>
					<tr><td>Author: <input type="input" name="author" /> </td></tr>  
					<tr><td>Edition: <input type="input" name="edition" /> </td></tr>  
					<tr><td>    </td></tr>
					<tr><td id="browse-button"> <input type="submit" name="addbutt" value="Add the Title" >   </td></tr>
					<input type="hidden" id="condition" name="condition" value="${condition}">  
					
					<input type="hidden" id="price" name="price" value="${price}">  
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

