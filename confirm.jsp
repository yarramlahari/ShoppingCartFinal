<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file='css/styles.css'%>

<html>
<head>
    <title>TestTask</title>
</head>

<body>
    <table align = "center" class="table" cellspacing='0'>
    <tr><th valign = "center">${message}</th></tr>
    </table>

    <table align = "center" class="table" cellspacing='0'>
    <tr><td><a href="./add">Add new organization</a></td>
    <td><a href="./">Home</a></td></tr>
    </table>
    <br />

</body>
</html>