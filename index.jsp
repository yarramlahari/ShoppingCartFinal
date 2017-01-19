<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file='css/styles.css'%>

<html>
<head>
    <title>TestTask</title>
</head>

<body>
    <table align = "center" class="table" cellspacing='0'>
    <tr><th valign = "top">List of Organizations</th>
    <th valign = "top">Country</th>
    <th valign = "top">Address</th>
    <th valign = "top">Phone</th>
    <th valign = "top">Market Cap, $</th>
    <th valign = "top"> </th>
    <th valign = "top"> </th></tr>
    <c:forEach items="${organizations}" var="organization">
    <c:url var="edit" value="/edit/${organization.id}" />
    <c:url var="remove" value="/remove/${organization.id}" />
    <tr><td><c:out value="${organization.name}" /></td>
    <td><c:out value="${organization.country.name}" /></td>
    <td><c:out value="${organization.address}" /></td>
    <td><c:out value="${organization.phone}" /></td>
    <td><c:out value="${organization.market_cap}" /></td>
    <td valign = "top"><a href="${edit}">Edit</a></td>
    <td valign = "top"><a href="${remove}">Remove</a></td></tr>
    </c:forEach>
    </table>

    <table align = "center" class="table" cellspacing='0'>
    <tr><c:url var="add" value="/add"/>
    <td><a href="${add}">Add</a> an organization</td></tr>
    </table>
    <br>
</body>
</html>
