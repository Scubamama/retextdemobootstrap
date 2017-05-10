<html>

<head>

<title>Retext Browse</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form method=GET action="/retextdemo/manage/titleLocated">
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<div id="find">Find a Book</div>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					<tr><td id="in_field">School: <input type="text" name="school" /> </td></tr>
					
					<tr><td id="in_field">ISBN: <input type="text" name="isbn" /> </td></tr>
					<tr><td>    </td></tr>
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/located/titleLocated">Browse for Book</a>  </td></tr>
					
					<tr><td id="other_button"><input type="submit" name="addABook" value="Add a Book"  /> </td>   
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/login">Login</a>  </td></tr>
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

