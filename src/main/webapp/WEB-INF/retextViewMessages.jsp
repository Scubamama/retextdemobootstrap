<%@ page import="java.util.List,model2.UserInventoryDisplay,model2.DisplayUserInventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>

<title>Your Messages</title>

<link type="text/css" rel="stylesheet" href="/retextdemo/css/reTextStyle.css">
</head>

<body>

<div id="container">
	<h2>ReText</h2>
	
	<%List<DisplayUserInventory> disp =(List<DisplayUserInventory>) request.getAttribute("titleList");%>

	
	
	<table id="located_table">
	<!--	<tr><td >  Title Located:  ${title} </td></tr>  -->
		<tr>
			<th>Sender</th>
			<th>Viewed</th>
			<th>Message</th>
			<th>Action</th>
		</tr>
			
		<c:forEach var="tempMessList" items="${messageList}">	
		<!--  -->
			<c:url var="tempLink" value="RetextMessageServlet"> 
				<c:param name="command" value="viewMessages" />
				<c:param name="senderId" value="${tempMessList.senderId}" />
			</c:url>
						
			<tr>
				<td>  ${tempMessList.senderName} </td>
				<td>  ${tempMessList.viewed}</td>
				<td>  ${tempMessList.message}</td>
		<!-- 		<td><a href="${tempLink}">Contact Seller</a></td>  -->
					<td><a href="<%=request.getContextPath() %>/messages/send?id=${tempMessList.senderId}">Reply</a> </td>
					<td><a href="<%=request.getContextPath() %>/messages/delete?id=${tempMessList.messageId}">Delete</a> </td>
			</tr>
		</c:forEach>
	</table>	

	<div class="copyright">
		<small>&copy copyright 2017 Holly Williams</small>
	</div>
</div>

</body>

</html>