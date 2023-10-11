<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String regData = (String) request.getAttribute("reg-data");
    if (regData == null) {
        regData = "";
    }
%>

<h2>Sign Up</h2>

<%=regData%>

<form action="signup" method="post">
    <input placeholder="email" name="email">
    <input placeholder="password" name="password">
    <input type="date" placeholder="birthDate" name="date">

    <input type="submit">
</form>

