<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List of Students</title>
</head>
<body>
<h1>List of Students</h1>

<c:if test="${not empty students}">
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Scorecard Number</th>
            <th>Average Mark</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td><c:out value="${student.name}" /></td>
                <td><c:out value="${student.surname}" /></td>
                <td><c:out value="${student.numberScorecard}" /></td>
                <td><c:out value="${student.averageMark}" /></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty students}">
    <p>No students found.</p>
</c:if>

</body>
</html>
