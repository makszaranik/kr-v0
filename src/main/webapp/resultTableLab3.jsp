<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lab23.service.formula.FormulaCalculatorFactory" %>
<%@ page import="lab23.service.formula.calculator.FormulaCalculator" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form and Table Example</title>
</head>
<body>
<form action="CalculationServletLab3" method="get">
    <br>


    <br>
    <div>
        <p>Choose your formula and enter your data:</p>
    </div>
    <div>
        <img alt="formula1" src="./img/form1.png">
        <img alt="formula2" src="./img/form2.png">
        <img alt="formula3" src="./img/form3.png">
    </div>
    <form action="CalculationServletLab3" method="get">
        <div class="radio">
            <input type="radio" id="form1" name="formula" value="form1">
            <label for="form1">Formula 1</label>
            <input type="radio" id="form2" name="formula" value="form2">
            <label for="form2">Formula 2</label>
            <input type="radio" id="form3" name="formula" value="form3">
            <label for="form3">Formula 3</label>
        </div>
        <br>


    <label for="aFrom">a: from</label>
    <input type="number" id="aFrom" name="aFrom" placeholder="from" value="<%= (int)request.getAttribute("aFrom") %>">
    <label for="aTo">to</label>
    <input type="number" id="aTo" name="aTo" placeholder="to" value="<%= (int)request.getAttribute("aTo") %>">
    <label for="aStep">step</label>
    <input type="number" id="aStep" name="aStep" placeholder="step" value="<%= (int)request.getAttribute("aStep") %>"> <br>


    <label for="bFrom">b: from</label>
    <input type="number" id="bFrom" name="bFrom" placeholder="from" value="<%= (int)request.getAttribute("bFrom") %>">
    <label for="bTo">to</label>
    <input type="number" id="bTo" name="bTo" placeholder="to" value="<%= (int)request.getAttribute("bTo") %>">
    <label for="bStep">step</label>
    <input type="number" id="bStep" name="bStep" placeholder="step" value="<%= (int)request.getAttribute("bStep") %>"> <br>


    <label for="cFrom">c: from</label>
    <input type="number" id="cFrom" name="cFrom" placeholder="from" value="<%= (int)request.getAttribute("cFrom") %>">
    <label for="cTo">to</label>
    <input type="number" id="cTo" name="cTo" placeholder="to" value="<%= (int)request.getAttribute("cTo") %>">
    <label for="cStep">step</label>
    <input type="number" id="cStep" name="cStep" placeholder="step" value="<%= (int)request.getAttribute("cStep") %>"> <br>


    <label for="dFrom">d: from</label>
    <input type="number" id="dFrom" name="dFrom" placeholder="from" value="<%= (int)request.getAttribute("dFrom") %>">
    <label for="dTo">to</label>
    <input type="number" id="dTo" name="dTo" placeholder="to" value="<%= (int)request.getAttribute("dTo") %>">
    <label for="dStep">step</label>
    <input type="number" id="dStep" name="dStep" placeholder="step" value="<%= (int)request.getAttribute("dStep") %>"> <br>


    <br>
    <input type="submit" value="Calculate">
    <br/>
</form>


<%
    FormulaCalculator formula = (FormulaCalculator) request.getAttribute("formula");
    if (formula != null) {
        int aFrom = (int) request.getAttribute("aFrom");
        int aTo = (int) request.getAttribute("aTo");
        int aStep = (int) request.getAttribute("aStep");

        int bFrom = (int) request.getAttribute("bFrom");
        int bTo = (int) request.getAttribute("bTo");
        int bStep = (int) request.getAttribute("bStep");

        int cFrom = (int) request.getAttribute("cFrom");
        int cTo = (int) request.getAttribute("cTo");
        int cStep = (int) request.getAttribute("cStep");

        int dFrom = (int) request.getAttribute("dFrom");
        int dTo = (int) request.getAttribute("dTo");
        int dStep = (int) request.getAttribute("dStep");
%>
<br>
<h2>Results:</h2>
<table border="1">
    <thead>
    <tr>
        <th>a</th>
        <th>b</th>
        <th>c</th>
        <th>d</th>
        <th>Result</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (int a = aFrom; a <= aTo; a += aStep) {
            for (int b = bFrom; b <= bTo; b += bStep) {
                for (int c = cFrom; c <= cTo; c += cStep) {
                    for (int d = dFrom; d <= dTo; d += dStep) {
    %>
    <tr>
        <td><%= a %></td>
        <td><%= b %></td>
        <td><%= c %></td>
        <td><%= d %></td>
        <td><%= formula.calculate(a, b, c, d) %></td>
    </tr>
    <%
                    }
                }
            }
        }
    %>
    </tbody>
</table>
<%
    }
%>
</body>
</html>
