<!DOCTYPE html>
<html lang="en">

<head>

	<title>Retext Login</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="css/reTextStyle.css">
</head>

<body>
  	<form action="<%=request.getContextPath() %>/loginOut/save" method=POST>  
		
        <div class="container" style="background-color: #002147;padding:10px;">
            <div class="well well-lg" style="background-color: #EEEEEE; style="padding:10px">
                    <div class="row justify-content-center" style="padding:10px"><h2>ReText</h2></div>
                    <div class="row justify-content-center">
                        <p class="small">You can buy, sell, and trade text books on campus</p>
                    </div>

                    <div class="row justify-content-center "><h3>Sign In</h3></div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label style="padding-left:20px">User Name:</label>
                            <div class="col-sm-4 "><input type="input" name="userName" placeholder="User Name" padding:10px/> </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label style="padding-left:20px">Password:</label>
                            <div class="col-sm-4" ><input type="password" name="password" placeholder="Password"/> </div>
                        </div>
                    </div>
                        <p style="padding:10px"></p>
                        <div class="row justify-content-center">
                                <div class="center" style="padding:10px">
                                        <input type="submit" class="btn btn-success center-block" name="Login" value="Sign Me In" style="padding:10px"/>
                                </div>
                        </div>

                        <div class="row justify-content-center">
                            <div class="center" style="padding:10px">
                                -- OR --
                            </div>
                        </div>

                        <div class="row justify-content-center">
                            <div class="center" style="padding:10px">
                                <a href="<%=request.getContextPath() %>/newUser/*" class="btn btn-outline-primary">Create New User Id</a>
                            </div>
                        </div>

                    <input type="hidden" id="condition" name="isbn" value="${isbn}">

                    <p style="padding:10px"></p>

                    <div class="small">
                        <p class="small">&copy copyright 2017 Holly Williams</p>
                    </div>

			</div> <!-- end well -->
		</div>  <!-- end container -->
	</form>
	
</body>
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	

</html>