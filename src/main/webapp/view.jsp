<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Page</title>
</head>
<body>
<h1>List Page</h1>
<form action="ShowStudentsServlet" method="get">
    <label for="institute">Enter Institute:</label>
    <input type="text" id="institute" name="institute"><br>

    <label for="department">Enter Department:</label>
    <input type="text" id="department" name="department"><br>

    <input type="submit" value="Show Students">
</form>
</body>
</html>
