<%@ include file="Header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>You were deleted from queue</title>
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

      p {
        text-align: center;
        margin-top: 20px;
        color: #666;
      }

      .link {
        text-align: center;
        margin-top: 20px;
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
<h1>You were removed from the queue, or you were not added</h1>
<p>Your request to join the queue has been removed.</p>
<div class="link">
    <a href="AddUserInQueueByHimself">Return to Main Page</a>
</div>
</body>
</html>
