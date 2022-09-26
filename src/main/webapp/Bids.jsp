<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "com.example.*" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2> List of Current Bids</h2>
<%List<Bid> b = (List<Bid>)request.getAttribute("bidlist"); %>
<table border = "1">
<tr><th>Stock</th><th>Quantity</th><th>Price</th></tr>
<%for(Bid bb:b){ %>
<tr>
<td><%=bb.getSymbol()%></td>
<td><%=bb.getQuantity()%></td>
<td><%=bb.getPrice()%></td>
</tr><%} %>
</table>
</body>
</html>