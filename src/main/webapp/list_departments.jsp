<%@ page import="java.util.Set" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Departments</title>
</head>
<body>
<h1>List of Departments</h1>
<ul>
    <% for (String departmentName : (Set<String>) request.getAttribute("departmentNames")) { %>
    <li><%= departmentName %></li>
    <% } %>
</ul>
</body>
</html>
