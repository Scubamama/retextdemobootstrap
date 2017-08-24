<!DOCTYPE html>
<html lang="en">

<head>
	    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
	<title>Retext Listings</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	
<!-- 	<form method=POST action="/retextdemo/manageUsers">  -->
		 <form action="<%=request.getContextPath() %>/manageUsers" method=POST>
	
		<div class="container" style="background-color: #002147;padding:10px;">
 	 		<div class="well well-lg" style="background-color: #EEEEEE">

					<div class="row justify-content-center"><h2>ReText</h2></div>
					<div class="row justify-content-center">
						<p class="small">You can buy, sell, and trade text books on campus</small>
					</div>
					<div class="row justify-content-center"><h4>Manage Your Profile</h4></div>
				
					<div class="row justify-content-center">
						<div class="center" style="padding:10px">
							<a href="<%=request.getContextPath() %>/listings" class="btn btn-info" role="button">Manage My Listings</a> 					
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="center" style="padding:10px">
							<a href="<%=request.getContextPath() %>/profile" class="btn btn-info" role="button">Manage MY Profile</a>  
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="center" style="padding:10px">					
							<a href="<%=request.getContextPath() %>/loginOut/actions" class="btn btn-info" role="button">More</a> 
						</div>
					</div>
						
					<div class="row justify-content-center">
						<div class="center" style="padding:10px">
							<a href="<%=request.getContextPath() %>/loginOut/logOut">Sign Out</a>  
						</div>
					</div>
					
				<div class="small">
					<p class="small">&copy copyright 2017 Holly Williams</p>
				</div>
				
			</div> <!-- end well -->
		</div> <!-- end container -->
	</form>
	
</body>

</html>