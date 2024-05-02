<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nothing to Show</title>
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

      a {
        color: #007bff;
        text-decoration: none;
      }

      a:hover {
        text-decoration: underline;
      }

      .position {
        color: #333;
        font-size: 24px;
      }
    </style>
</head>
<body>
<div class="container">
    <h1>Your Position</h1>
    <% Integer position = (Integer) request.getAttribute("UserPositionInQueue");
        if (position != null) { %>
    <p class="position">Your position in the queue: <%= position %></p>
    <% } else { %>
    <p>There is no position to show.</p>
    <% } %>
</div>
</body>
</html>
