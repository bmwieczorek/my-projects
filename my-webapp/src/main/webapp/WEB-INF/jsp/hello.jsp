<%@ include file="/WEB-INF/jsp/include.jsp" %>

<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1>Hello - Spring Application</h1>
    <p>Greetings, it is now <c:out value="${model.now}"/></p>
    <table>
    	<c:forEach var="product" items="${model.products}">
    		<tr>
    			<td><c:out value="${product.name}"/></td>
    			<td><c:out value="${product.price}"/></td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>