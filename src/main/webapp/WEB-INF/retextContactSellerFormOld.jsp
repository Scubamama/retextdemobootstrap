<html>

<head>
	<style>

	</style>
	<title>Contact Seller</title>
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form action="<%=request.getContextPath() %>/messages/send?id=${sellerId}" method=POST>
		<div id="container">
			<div id="wrapper">	
				<table id="contact_table" >
					
					<tr><td id="cntr">Contact a Seller</td></tr>
					<tr><td >  Seller:  ${sellerName} </td></tr>
					<tr><td id="loc_meet"> Message the Seller </td></tr>
					<tr><td id="loc_meet"> Location on Campus and time to Meet perhaps: </td></tr>
					<tr><td id="loc_meet"><textarea name="message" cols = "40" rows="5"/></textarea> </td></tr>
					
					<tr><td id="cntr"><input type="submit" name="Submit" value="Send"  /> </td></tr>
				
				</table>
			</div>
		</div>
	</form>

</body>

</html>