<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
 <!-- put this liberary in Pom.xml -->   
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <div>
  <h1>Company Details</h1>

<table border="1">
  <tr><td>Company ID</td><td>Company Name</td><td>Company Inception Date</td><td>Company Share Price</td>
  </tr>
  
  <c:forEach items="${companies}" var="company"> 
  <tr><td>${company.companyid }</td><td>${company.companyname }</td><td>${company.company_inception }</td><td>${company.shareprice}</td>
  </tr>
  </c:forEach>
  
  
  
  
  

</table>
</div>



		 

		   

</body>
</html>


	 
	
	 