<!DOCTYPE html>
<html lang="en">

<head>

<title>Retext Profile Updated</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Retext Profile Updated</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
</head>

<body>

<!-- <form method=GET action="/retextdemo/manage/browse">  -->	
	<form action="<%=request.getContextPath() %>/manage/browse" method=GET>
	
		<div class="container" style="background-color: #002147; padding:10px;">
 			<div class="well well-lg" style="background-color: #EEEEEE">
 			
				<div class="row justify-content-center"><h2>ReText</h2></div>
				<div class="row justify-content-center">
					<p class="small">You can buy, sell, and trade text books on campus</p>
				</div>
				
				<div class="row justify-content-center"> <h4>Your Profile has been updated<h4></div>
				<div class="row justify-content-center"> <h4>You are logged in as ${theUser}<h4></div>
				<div class="row justify-content-center"> <h4>What would you like to do?<h4></div>
				
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">
						<a href="<%=request.getContextPath() %>/manage/browse" class="btn btn-info" role="button">Look for Books</a>
					</div>
				</div>
				
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">
						<a href="<%=request.getContextPath() %>/messages/view?id=${userId}" class="btn btn-info" role="button">Read My Messages</a>
					</div>
				</div>
					
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">					
			<!-- 		<a href="<%=request.getContextPath() %>/manageUsers">Manage User Info</a>   -->
						<a href="<%=request.getContextPath() %>/manageUsers" class="btn btn-info" role="button">Manage My Info</a>
					</div>
				</div>
					
				<div>
					<small>&copy copyright 2017 Holly Williams</small>
				</div>

			</div>
			
		</div>
	</form>
		    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
</body>

</html>

