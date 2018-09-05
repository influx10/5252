
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page session="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Retail Management Offers</title>
<link rel="stylesheet" href="<spring:theme code='css'/>" type="text/css" />
</head>
<body>
	<form method="POST" enctype="multipart/form-data"
		modelAttribute="fileupload">
		<table>

			<tr>
				<td>Employee First Name </td> 
				<td><input type="text">
				</td>
			</tr>


			<tr>
				<td>Contact Number </td>
				<td> <input type="text"></td>
			</tr>


			<tr>
				<td>Number of years of Experience </td> 
				<td><input type="text"> 
				</td>
			</tr>

			<tr>
				<td>Resume Upload</td>
				<td><input type="file" name="file" /></td>
			</tr>

			<tr>
				<td>
				<td><input type="submit" value="Submit"></td>
			</tr>
		</table>

	</form>

</body>
</html>