<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>View Selected Queue</title>
</head>
<body>
<h1>View Selected Queue</h1>

<c:if test="${selectedQueue ne null}">
    <h1>Queue Name: ${selectedQueue.name}</h1>
    <p>Created by: ${selectedQueue.creator.username}</p>
    <ul>
        <c:forEach var="item" items="${selectedQueue.items}">
            <li>${item}</li>
        </c:forEach>
    </ul>
</c:if>

</body>
</html>
