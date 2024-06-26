<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Queue Is Already Created</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
      }

      .container {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        padding: 20px;
        text-align: center;
      }

      h1 {
        color: #333;
        margin-bottom: 20px;
      }

      p {
        color: #666;
        margin-bottom: 20px;
      }

      .link a {
        color: #007bff;
        text-decoration: none;
      }

      .link a:hover {
        text-decoration: underline;
      }
    </style>
</head>
<body>
<div class="container">
    <h1>Queue Is Already Created</h1>
    <p>The queue you tried to create already exists.</p>
    <div class="link">
        <a href="CreateQueue.jsp">try another name</a>
    </div>
</div>
</body>
</html>
