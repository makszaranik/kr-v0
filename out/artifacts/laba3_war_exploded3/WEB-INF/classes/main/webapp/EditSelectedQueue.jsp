<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Queue</title>
    <style>
      body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
      }

      h1 {
        text-align: center;
        margin-top: 50px;
        color: #333;
      }

      form {
        text-align: center;
        margin-top: 20px;
      }

      select, input[type="text"] {
        width: 300px;
        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
      }

      .red-button {
        background-color: #ff0000;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      .blue-button {
        background-color: #007bff;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        transition: background-color 0.3s ease;
      }

      .red-button:hover {
        background-color: #d60000;
      }

      .blue-button:hover {
        background-color: #0056b3;
      }
    </style>
</head>
<body>

<h1>Edit Queue</h1>

<form action="AddToQueueServlet" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="newItem" placeholder="Enter new item">
    <input type="submit" class="blue-button" value="Add">
</form>

<form action="RemoveFromQueue" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="itemToRemove" placeholder="Enter item to remove">
    <input type="submit" class="blue-button" value="Remove">
</form>

<form action="RemoveFromBegin" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" class="blue-button" value="RemoveFromBegin">
</form>

<form action="BlockUnblockQueue" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" class="red-button" name="action" value="Block/Unblock Queue">
</form>

<form action="DeleteQueueServlet" method="post">
    <select name="selectedQueue">
        <c:forEach var="queue" items="${queues}">
            <option value="${queue.name}">${queue.name}</option>
        </c:forEach>
    </select>
    <input type="submit" class="red-button" value="Delete Queue">
</form>

</body>
</html>
