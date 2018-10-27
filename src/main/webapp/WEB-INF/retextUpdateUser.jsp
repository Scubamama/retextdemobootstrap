<!DOCTYPE html>
<html lang="en">

<head>
	    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Retext Manage My Info</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
<!-- 	<form action="<%=request.getContextPath() %>/manageUsers/updateProfile" method="POST">  -->
	<form action="<%=request.getContextPath() %>/manageUsers/saveUpdateProfile" method="POST">

		<div class="container" style="background-color: #002147;padding:10px;">
 	 		<div class="well well-lg" style="background-color: #EEEEEE">

					<div class="row justify-content-center"><h2>ReText</h2></div>
					<div class="row justify-content-center">
						<p class="small">You can buy, sell, and trade text books on campus</small>
					</div>
					<div class="row justify-content-center"><h4>Update Your Profile</h4></div>


					<input type="hidden" name="currUserId" value="${thisUser.id}"/>

		 			<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							Email Address: <input name="email"  type="email" value="${thisUser.userEmail}" />

						</div>
					</div>

	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							User Name: <input type="text" name="userName" value="${thisUser.userName}" />
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							(user name must be at least 10 characters)
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							School Name: <input type="text" name="schoolName" value="${thisUser.userSchool}"/>
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							Campus: <input type="input" name="campus"  value="${thisUser.userCampus}"/>
						</div>
					</div>
                    <div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							Current Password: <input type="password" name="curPassword" />
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							(password must be at least 10 characters)
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							New Password: <input type="password" name="password" />
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							Confirm Password: <input type="password" name="passwordconfirm" />
						</div>
					</div>
	 				<div class="row justify-content-center ">
						<div class="center" style="padding:10px">
							Are you able to take non-cash payments? (y/n): <input type="text" name="takeCardsYN" value="${thisUser.takeCardsYN}"/> </td></tr>
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="center" style="padding:10px">
							<input type="submit" class="btn btn-success center-block" name="Create" value="Update User Info"  />
						</div>
					</div>

					<div class="row justify-content-center">
						<div class="center" style="padding:10px">
<!--							<a href="<%=request.getContextPath() %>/manageUsers/profile" class="btn btn-info" role="button">Cancel</a>    -->
							<a href="<%=request.getContextPath() %>/manageUsers" class="btn btn-info" role="button">Cancel</a>
						</div>
					</div>

					<div class="small">
						<p class="small">&copy copyright 2017 Holly Williams</p>
					</div>

				</div> <!--  end well -->
			</div> <!--  end container -->
	</form>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>

</html>
