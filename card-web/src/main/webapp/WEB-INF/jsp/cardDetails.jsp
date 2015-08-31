<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Card Details </h2>
Your file is successfully uploaded.
<h2>Below is a list of your cards in descending order</h1>

<table>
	<tr>
   		<th>Bank</th>
   		<th>Masked Card No</th>
   		<th>Card No</th>
   		<th>Expiry Date</th>
 	</tr>
 	<tr></tr>
	<c:forEach items="${cards}" var="card">
    <tr>
        <td>${card.bankName}</td>
        <td>${card.maskedCardNo}</td> 
        <td>${card.cardNo}</td>
        <td>${card.expiryDate}</td>
    </tr>
</c:forEach>


</table>
</body>
</html>