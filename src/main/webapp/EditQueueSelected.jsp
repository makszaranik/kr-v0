<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Queue</title>
</head>
<body>
<h1>Edit Queue</h1>

<form action="DeleteQueueServlet" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Delete Queue">
</form>

<form action="AddToQueueServlet" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="newItem" placeholder="Enter new item">
    <input type="submit" value="Add">
</form>

<form action="RemoveFromQueueServlet" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="itemToRemove" placeholder="Enter item to remove">
    <input type="submit" value="Remove">
</form>

</body>
</html>
