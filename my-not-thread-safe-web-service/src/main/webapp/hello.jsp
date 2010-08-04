<html>
<body>
<h2>Hello World (my simple calculator web service)!</h2>
<% 
String myHeader = request.getHeader("myHeader");
out.println("Request header myHeader=" + myHeader);

String myAttribute = (String)request.getAttribute("myAttribute");
out.println("Request attribute myAttribute=" + myAttribute);

String myParameter = (String)request.getParameter("myParameter");
out.println("Request parameter myParameter=" + myParameter);

%>

</body>
</html>
