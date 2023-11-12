<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<String> regData = (ArrayList<String>) request.getAttribute("reg-data");
    String message = (String) request.getAttribute("reg-message");
%>

<h2>Sign Up</h2>

<div class="messages">

    <%if (regData != null) {%>
    <%for (String data : regData) {%>
    <p><%=data%>
    </p>
    <%}%>
    <%}%>
    <p><%=message%>
    </p>
</div>

<form action="signup" method="post" enctype="multipart/form-data">
    <input placeholder="email" name="email">
    <input placeholder="password" name="password">
    <input type="file" name="avatar">
    <input type="submit" value="SignUp">
</form>

