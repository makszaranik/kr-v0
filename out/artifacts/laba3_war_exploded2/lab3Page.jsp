<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Form</title>
</head>

<body>
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
    <input type="number" id="aFrom" name="aFrom" placeholder="from" value="${aFrom != null ? aFrom : 1}">
    <label for="aTo">to</label>
    <input type="number" id="aTo" name="aTo" placeholder="to" value="${aTo != null ? aTo : 5}">
    <label for="aStep">step</label>
    <input type="number" id="aStep" name="aStep" placeholder="step" value="${aStep != null ? aStep : 1}"> <br>

    <label for="bFrom">b: from</label>
    <input type="number" id="bFrom" name="bFrom" placeholder="from" value="${bFrom != null ? bFrom : 1}">
    <label for="bTo">to</label>
    <input type="number" id="bTo" name="bTo" placeholder="to" value="${bTo != null ? bTo : 5}">
    <label for="bStep">step</label>
    <input type="number" id="bStep" name="bStep" placeholder="step" value="${bStep != null ? bStep : 1}"> <br>

    <label for="cFrom">c: from</label>
    <input type="number" id="cFrom" name="cFrom" placeholder="from" value="${cFrom != null ? cFrom : 1}">
    <label for="cTo">to</label>
    <input type="number" id="cTo" name="cTo" placeholder="to" value="${cTo != null ? cTo : 5}">
    <label for="cStep">step</label>
    <input type="number" id="cStep" name="cStep" placeholder="step" value="${cStep != null ? cStep : 1}"> <br>


    <label for="dFrom">d: from</label>
    <input type="number" id="dFrom" name="dFrom" placeholder="from" value="${dFrom != null ? dFrom : 1}">
    <label for="dTo">to</label>
    <input type="number" id="dTo" name="dTo" placeholder="to" value="${dTo != null ? dTo : 5}">
    <label for="cStep">step</label>
    <input type="number" id="dStep" name="dStep" placeholder="step" value="${dStep != null ? dStep : 1}"> <br>


    <br>
    <input type="submit" value="Calculate">
    <br/>
</form>
</body>
</html>
