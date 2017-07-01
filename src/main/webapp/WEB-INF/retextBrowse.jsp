<html>

<head>

<title>Retext Browse</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

	<form action="<%=request.getContextPath() %>/located/titleLocated" method=POST>
		<div id="container">
			<div id="wrapper">
				<h2>ReText</h2>
				<div id="find">Find a Book</div>
				<div id="buy-sell">
					<small>You can buy, sell, and trade text books on campus</small>
				</div>
				
				<table id="find_table">
					<tr><td>School: <input type="text" name="school" /> </td></tr>
					
					<tr><td>ISBN: <input type="input" name="isbn" /> </td></tr>
					<tr><td>    </td></tr>
					<tr><td id="browse-button"> <input type="submit" name="browsebutt" value="search" >   </td></tr>
			<!-- 	<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/located/titleLocated">Browse for Book</a>  </td></tr> -->
					
					<tr></tr>   
					
					<tr><td id="browse-button"><a href="<%=request.getContextPath() %>/loginOut">Login</a>  </td></tr>
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

