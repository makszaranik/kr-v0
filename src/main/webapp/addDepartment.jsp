<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Department</title>
</head>
<body>
<h1>Add Department</h1>
<form action="AddDepartmentControllerServlet" method="post">
    <label for="instituteName"> Institute Name:</label>
    <input type="text" id="instituteName" name="instituteName"><br>
    <label for="departmentName">Department Name:</label>
    <input type="text" id="departmentName" name="departmentName"><br>
    <input type="submit" value="Add Department">
</form>
</body>
</html>
