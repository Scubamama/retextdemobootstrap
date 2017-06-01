<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

<title>Books Located</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

<div id="container">
	<h2>ReText</h2>
	
	<%List<DisplayUserInventory> disp =(List<DisplayUserInventory>) request.getAttribute("titleList");%>

	
	
	<table id="located_table">
		<tr><td >  Title Located:  ${title} </td></tr>
		<tr>
			<th>Price</th>
			<th>Condition</th>
			<th>Seller</th>
		</tr>
			
		<c:forEach var="tempTitle" items="${titleList}">	
		<!-- this url part lists correctly, but goes back to TitleLocatedServlet -->
			<c:url var="tempLink" value="RetextMessageServlet"> 
				<c:param name="command" value="contactSeller" />
				<c:param name="sellerId" value="${tempTitle.id}" />
			</c:url>
						
			<tr>
				<td>  ${tempTitle.price} </td>
				<td>  ${tempTitle.condition}</td>
				<td>  ${tempTitle.seller}</td>
		<!-- 		<td><a href="${tempLink}">Contact Seller</a></td>  -->
					<td><a href="<%=request.getContextPath() %>/messages/send?id=${tempTitle.id}">Contact Seller</a> </td>
				
			</tr>
		</c:forEach>
	</table>	

	<div class="copyright">
		<small>&copy copyright 2017 Holly Williams</small>
	</div>
</div>

</body>

</html>