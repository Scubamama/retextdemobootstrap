<html>

<head>
	<style>

	</style>
	<title>Contact Seller</title>
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
	<form action="<%=request.getContextPath() %>/messages/send" method=POST>
		<table id="contact_table" >
			
			<tr><td id="cntr">Contact a Seller</td></tr>
			
			<tr><td id="rt_field">Date:    <input type="text" name="date" /> </td></tr>
			
			<tr><td id="rt_field">Time:    <input type="text" name="time" /> </td></tr>
			
			<tr><td id="rt_field">School: <input type="text" name="school" /> </td></tr>
			
			<tr><td id="loc_meet"> Location on Campus to Meet: </td></tr>
			
			<tr><td id="loc_meet"><textarea name="notes" cols = "40" rows="5"/></textarea> </td></tr>
			
			
			<tr><td id="cntr"><input type="submit" name="Submit" value="Send"  /> </td></tr>
		
		</table>
	
	</form>

</body>

</html>