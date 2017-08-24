<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>Retext Update a Listing<</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
  </head>

<body>

	<form action="<%=request.getContextPath() %>/manageUsers/updateListing" method=POST>
	
		<div class="container" style="background-color: #002147;padding:10px;">
		 	<div class="well well-lg" style="background-color: #EEEEEE">
 	
				<div class="row justify-content-center"><h2>ReText</h2></div>
				<div class="row justify-content-center">
					<p class="small">You can buy, sell, and trade text books on campus</p>
				</div>
				<div class="row justify-content-center"><h3>Update this Listing</h3></div>
				
				
				<input type="hidden" name="listingId" value="${thisListing.id}"/>
				
				<div class="row justify-content-center ">
					<div class="center" style="padding:10px">
						Price: <input type="input" name="price" value="${thisListing.price}" />
					</div>
				</div>
					
				<div class="row justify-content-center ">
					 Condition: 
						<select name="bookCondition">
							<option>Poor</option>
							<option>Fair</option>
							<option>Good</option>
							<option>Very Good</option>
							<option>Like New</option>
							<option>New</option>
						</select>
				</div>	

				<div class="row justify-content-center">
					<div class="center" style="padding:10px">
						<input type="submit" class="btn btn-success center-block" name="addbutt" value="Update the Listing"  />
					</div>
				</div>
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">					
						<a href="<%=request.getContextPath() %>/loginOut/actions" class="btn btn-info" role="button">More Actions</a> 
					</div>
				</div>

				<div class="small">
					<p class="small">&copy copyright 2017 Holly Williams</p>
				</div>

			</div> <!-- end well -->
			
		</div> <!-- end container -->
	</form>
		   <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	
</body>

</html>

