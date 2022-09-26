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
<h2> List of Current Asks</h2>
<%List<Ask> a = (List<Ask>)request.getAttribute("asklist"); %>
<table border = "1">
<tr><th>Stock</th><th>Quantity</th><th>Price</th><th>Buy</th></tr>
<%for(Ask aa:a){ %>
<tr>
<td><%=aa.getSymbol()%></td>
<td><%=aa.getQuantity()%></td>
<td><%=aa.getPrice()%></td>
<td><a href="#">Buy</a></td>
</tr><%} %>
</table>
</body>
</html>