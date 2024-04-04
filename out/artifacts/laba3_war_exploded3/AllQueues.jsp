<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Queues</title>
</head>
<body>
<h1>All Queues</h1>

<form action="ViewSelectedQueue" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="View">
</form>

</body>
</html>
