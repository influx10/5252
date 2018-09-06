<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style type="text/css">
.error{
color:red;
}
</style>
<c:if test="${erroMessage != null }">
	<p style="color:red">${erroMessage}</p>
</c:if>
<fmt:formatDate value="${passengerDetailsVO.doj}" type="date" pattern="dd/MM/yyyy"/>
<script type = "text/javascript">
         <!--
            function WriteCookie()
            {
               if( document.loginForm.userName.value != "" && document.loginForm.userName.value != null )
               {
                 	cookievalue= escape(document.loginForm.userName.value) + ";";
	               	document.cookie="name=" + cookievalue;	               	
               }               
            }
         //-->
      </script>

</head>
<body>

<h1><spring:message code="app.title" /></h1><br>
<h2><spring:message code="login.welcome" /> ${cookie.name.value} </h2><br>
<h3><spring:message code="booking.details" /></h3>

<sf:form name="bookingForm" action="./submitBook" method="POST" modelAttribute="passengerDetailsVO">

<table>
<tr>
<td><spring:message code="booking.name"  /></td>
<td><sf:input path="name" /></td>
<td><sf:errors path="name" cssClass="error"/></td>
</tr>
<tr>
<td><spring:message code="booking.phoneNumber"  /></td>
<td><sf:input path="phoneNumber" /></td>
<td><sf:errors path="phoneNumber" cssClass="error"/></td>
</tr>

<tr>
<td><spring:message code="booking.source"  /></td>
<td>
<sf:select path="source">
<sf:options items="${cityList}"/>
</sf:select>
</td>
</tr>

<tr>
<td><spring:message code="booking.destination"  /></td>
<td>
<sf:select path="destination">
<sf:options items="${cityList}"/>
</sf:select>
</td>
</tr>

<tr>
<td><spring:message code="booking.doj"  /></td>
<td><sf:input path="doj" /></td>
<td><sf:errors path="doj" cssClass="error"/></td>
</tr>

<tr>
<td><spring:message code="booking.gender"  /></td>
<td><sf:radiobuttons path="gender" items="${genderList}"/></td>
<td><sf:errors path="gender" cssClass="error" /></td>
</tr>

<tr>
<td><spring:message code="booking.cardNumber"  /></td>
<td><sf:input path="cardNumber" /></td>
<td><sf:errors path="cardNumber" cssClass="error"/></td>
</tr>

<tr>
<td><spring:message code="booking.pinNumber"  /></td>
<td><sf:input path="pinNumber" /></td>
<td><sf:errors path="pinNumber" cssClass="error"/></td>
</tr>

<tr>
<td colspan="2"><sf:checkbox path="isAgreed" title="I understand terms and conditions."/>
<spring:message code="booking.isAgreed" />
<sf:errors path="isAgreed" cssClass="error"/>
</td>
</tr>

<tr>
<td><input type="submit" value="<spring:message code="booking.bookTicket" />"/></td>
<td><input type="reset" value="<spring:message code="booking.cancel" />"/></td>
</tr>
</table>

</sf:form>

</body>
</html>