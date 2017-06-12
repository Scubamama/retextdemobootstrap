<html>

<head>
	<style>

	</style>
	<title>Contact Seller</title>
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form action="<%=request.getContextPath() %>/messages/send?id=${sellerId}" method=POST>
			
		<table id="contact_table" >
			
			<tr><td id="cntr">Contact a Seller</td></tr>
			<tr><td >  Seller:  ${sellerName} </td></tr>
			<tr><td id="loc_meet"> Location on Campus to Meet: </td></tr>
			
			<tr><td id="loc_meet"><textarea name="message" cols = "40" rows="5"/></textarea> </td></tr>
			
			<tr><td id="cntr"><input type="submit" name="Submit" value="Send"  /> </td></tr>
		
		</table>
	
	</form>

</body>

</html>