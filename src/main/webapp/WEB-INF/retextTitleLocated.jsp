<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ page import="java.text.DecimalFormat" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>

	<title>Books Located</title>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
	<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>
	<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
	<div class="container" style="background-color: #002147; padding:10px;">
		<div class="well well-lg" style="background-color: #EEEEEE">
	
			<div class="row justify-content-center"><h2>ReText</h2></div>
			<div class="row justify-content-center">
				<p class="small">You can buy, sell, and trade text books on campus</p>
			</div>
			
			
			<%List<DisplayUserInventory> disp =(List<DisplayUserInventory>) request.getAttribute("titleList");%>
			
			<div class="table-responsive">  		
				<table class="table table-striped">
					<tr><td >  Title Located:  ${title} </td></tr>
					<tr>
						<th>Price</th>
						<th>Condition</th>
						<th>Seller</th>
					</tr>
						
					<c:forEach var="tempTitle" items="${titleList}">	
					<!--  -->
						<c:url var="tempLink" value="RetextMessageServlet"> 
							<c:param name="command" value="contactSeller" />
							<c:param name="sellerId" value="${tempTitle.id}" />
						</c:url>
						
						
						<tr>
					<!-- 	<td> $ ${tempTitle.price} </td>   -->	
							<td> $ <fmt:formatNumber pattern="#.00" value="${tempTitle.price}" /></td>
							<td>  ${tempTitle.condition}</td>
							<td>  ${tempTitle.seller}</td>
					<!-- 		<td><a href="${tempLink}">Contact Seller</a></td>  -->
								<td><a href="<%=request.getContextPath() %>/messages/send?id=${tempTitle.id}">Contact Seller</a> </td>
							
						</tr>
					</c:forEach>
				</table>
				
				<div class="row justify-content-center">
					<div class="center" style="padding:10px">					
						<a href="<%=request.getContextPath() %>/loginOut/actions" class="btn btn-primary" role="button">More</a> 
					</div>
				</div>
				
					
			</div>
			<div class="small">
				<p class="small">&copy copyright 2017 Holly Williams</p>
			</div>
		
	</div> <!-- end well -->
</div> <!-- end container -->
		
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	


</body>

</html>