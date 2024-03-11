<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Student</title>
</head>
<body>
<h1>Add Student</h1>
<form action="AddStudentControllerServlet" method="post">
    <label for="studentName">Name:</label>
    <input type="text" id="studentName" name="studentName"><br>
    <label for="studentSurname">Surname:</label>
    <input type="text" id="studentSurname" name="studentSurname"><br>
    <label for="departmentName">Department:</label>
    <input type="text" id="departmentName" name="departmentName"><br>
    <label for="instituteName">Institute:</label>
    <input type="text" id="instituteName" name="instituteName"><br>
    <input type="submit" value="Add Student">
</form>
</body>
</html>
