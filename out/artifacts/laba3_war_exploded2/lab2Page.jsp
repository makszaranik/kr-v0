<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 21.02.2024
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style_labs.css">
    <meta charset="UTF-8">
    <title>Lab2 - MAY Team</title>
    <link rel="icon" href="./img/MAY-icon-without-bg.png" type="image/x-icon">
</head>
<body>
<a href="index.html">
    <img src="./img/MAY-icon-with-bg.png" alt="">
</a>
<br>
<div>
    <p>Choose your formula and enter your data:</p>
</div>
<div>
    <img alt="formula1" src="./img/form1.png">
    <img alt="formula2" src="./img/form2.png">
    <img alt="formula3" src="./img/form3.png">
</div>
<form action="CalculationServlet" method="get">
    <div class="radio">
        <input type="radio" id="form1" name="formula" value="form1">
        <label for="form1">Formula 1</label>
        <input type="radio" id="form2" name="formula" value="form2">
        <label for="form2">Formula 2</label>
        <input type="radio" id="form3" name="formula" value="form3">
        <label for="form3">Formula 3</label>
    </div>

    <br>

    <p>Data: </p>

    <label for="a">a</label>
    <input type="text" id="a" name="a" value=${a}> <br>

    <label for="b">b</label>
    <input type="text" id="b" name="b" value=${b}><br>

    <label for="c">c</label>
    <input type="text" id="c" name="c" value=${c}><br>

    <label for="d">d</label>
    <input type="text" id="d" name="d" value=${d}><br>

    <input type="submit" value="Submit" class="sub">
</form>
</body>
</html>
