<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<style>
.error {
	color: #ff0000;
}

.errorblock{
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding:8px;
	margin:16px;
}
</style>
</head>
 
<body> 

<h2>Please upload a CSV file.. </h2>
	<form:form method="POST" commandName="file"	enctype="multipart/form-data">
 		<form:errors path="*" cssClass="errorblock" element="div"/>
		Upload your file please: 
		<input type="file" name="file" />
		<input type="submit" value="upload" />
		<form:errors path="file" cssClass="error" />
	</form:form>
 
</body>
</html>