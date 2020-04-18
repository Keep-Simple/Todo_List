<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="academy.learnprogramming.util.Mappings" %>
<html>
<head>
    <title>View TodoItem</title>
</head>
<body>
<div align="center">

    <table border="1" cellpadding="=5">
        <caption><h2>Todo-Item</h2></caption>

        <tr>
            <th>Title</th>
            <th>Deadline</th>
            <th>Details</th>
        </tr>

        <tr>
            <td><c:out value="${todoItem.title}"/></td>
            <td><c:out value="${todoItem.deadline}"/></td>
            <td><c:out value="${todoItem.details}"/></td>
        </tr>
    </table>

    <c:url var="backUrl" value="${Mappings.ITEMS}"/>
    <a href="${backUrl}">Back to the Todo-items</a>
</div>
</body>
</html>
