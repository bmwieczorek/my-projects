<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Hello :: Spring Application</title>
    </head>
    <body>
        <h1>Hello - Spring Application!</h1>
        <p>Hello <c:out value="${inputValue}" /></p>
		<p>Hello <c:out value="${selectValue}" /></p>

		<form:form  method="POST" commandName="processPostRequest">
			<form:input path="inputValue"/>
			<form:select path="selectValue" items="${selectValues}"/>
			<input type="submit" />
		</form:form>
    </body>
</html>