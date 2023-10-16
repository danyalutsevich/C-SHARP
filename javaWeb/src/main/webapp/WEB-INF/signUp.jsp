<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<String> regData = (ArrayList<String>) request.getAttribute("reg-data");
%>

<h2>Sign Up</h2>

<div class="messages">

    <%if (regData != null) {%>
    <%for (String message : regData) {%>
    <p><%=message%>
    </p>
    <%}%>
    <%}%>

</div>

<form action="signup" method="post">
    <input placeholder="email" name="email">
    <input placeholder="password" name="password">
    <input type="date" placeholder="birthDate" name="date">
    <input type="submit">
</form>

