<!DOCTYPE html>
<html lang="en">

<head>
	<title>Retext Welcome</title>
	 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="/retextdemoBootStrap/css/reTextStyle.css">
</head>

<body>

	<form action="<%=request.getContextPath() %>/manage/browse" method=GET>
	
<!-- <form method=GET action="/retextdemoBootStrap/manage/browse">   -->	
	
		<div class="container" style="background-color: #002147;padding:10px;">
		  <div class="well well-lg" style="background-color: #EEEEEE; style="padding:10px;">
		  
	<!-- 	  	<div class="btn-group-vertical center">
		  		<input type="submit" class="btn btn-success center-block" name="Browse" value="Browse Books"  />
		  		<a href="<%=request.getContextPath() %>/loginOut" class="btn btn-outline-success center-block">Login</a> 
		  		<a href="<%=request.getContextPath() %>/newUser/*" class="btn btn-outline-primary">Create New User Id</a> 
		  	</div>
	 -->	  
		  	<div class="row"  style="padding:10px">  </div>
		  	<p></p>
			<div class="row justify-content-center"><h2>Welcome to ReText</h2></div>
			<div class="row justify-content-center" style="padding:10px"><span>You can buy, sell, and trade your text books on your campus</span></div>
			
			<div class="row justify-content-center">
	<!-- 	    <div class="col-4">
    			</div>   -->	
    			
				<div class="center" style="padding:10px">
					<input type="submit" class="btn btn-success center-block" name="Browse" value="Browse Books"  />
				</div>
			</div>
			
			<div class="row justify-content-center ">
				<div class="center" style="padding:10px">
					<a href="<%=request.getContextPath() %>/loginOut" class="btn btn-outline-success center-block">Login</a> 
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="center" style="padding:10px">
					<a href="<%=request.getContextPath() %>/newUser/*" class="btn btn-outline-primary">Create New User Id</a> 
				</div>
			</div>
			
	<!-- vertical button group example	
			<div class="btn-group-vertical">
			    <button type="button" class="btn btn-primary">Apple</button>
			    <button type="button" class="btn btn-primary">Samsung</button>
			    <button type="button" class="btn btn-primary">Sony</button>
			</div>
	 -->			
	 		<p></p>
			<div class="small">
				<p class="small">&copy copyright 2017 Holly Williams</p>
			</div>
			
		  </div> <!-- end well -->
		  		  	<p></p>
		  
		</div> <!-- end container -->
		
	</form>
	

	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
</body>

</html>