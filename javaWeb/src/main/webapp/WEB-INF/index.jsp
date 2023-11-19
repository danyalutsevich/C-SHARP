<html>
<body>

<h1>Call me</h1>

<form method="POST" action="callme" enctype="multipart/form-data">
    <input name="name" placeholder="Name">
    <input name="phoneNumber" placeholder="Phone Number">
    <input name="email" placeholder="Email">
    <input type="submit" value="Call me!">
</form>

<jsp:include page="fragment.jsp"/>

<%for (int i = 0; i < 200; i++) {%>

<p><%=i%>
</p>

<%}%>



</body>
</html>
