<%@ page import="java.util.*, com.luv2code.web.jdbc.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>

<html>

<head>
<title>Student tracker app</title>
</head>

<%
	//get the students from request object (sent by StudentController Servlet)

	List<Student> theStudents = (List<Student>) request.getAttribute("STUDENT_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>

	<table border="1">

		<tr>
		<thead>First Name
		</thead>
		<thead>Last Name
		</thead>
		<thead>Email
		</thead>
		</tr>


		<c:forEach var="tempStudent" items="${theStudents}">

			<tr>
				<td>${tempStudent.firstName}</td>
				<td>${tempStudent.lastName}</td>
				<td>${tempStudent.email}</td>
			</tr>

		</c:forEach>

	</table>

</body>

</html>
