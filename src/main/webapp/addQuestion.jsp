<%--
  Created by IntelliJ IDEA.
  User: David VC
  Date: 19/12/2018
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Question</title>
    <link rel="stylesheet" href="static/css/reset.css">
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body>

<%@ include file="components/header.jspf"%>
<main class="addquestion">
    <h1>Maak een vraag voor de competentie '${tent.name}'</h1>
    <form method="post" action="submitQuestion">
        <input type="text" id="questionvalue" name="questionvalue" placeholder="Vraag" required /> <br>
        <label for="answer1">Antwoord 1: </label><input type="text" id="answer1" name="answer1" placeholder="Eerste antwoord"> is <input type="number" id="point1" name="point1" min="0" placeholder="..."> punten waard<br>
        <label for="answer2">Antwoord 2: </label><input type="text" id="answer2" name="answer2" placeholder="Tweede antwoord"> is <input type="number" id="point2" name="point2" min="0" placeholder="..."> punten waard<br>
        <label for="answer3">Antwoord 3: </label><input type="text" id="answer3" name="answer3" placeholder="Derde antwoord"> is <input type="number" id="point3" name="point3" min="0" placeholder="..."> punten waard<br>
        <label for="answer4">Antwoord 4: </label><input type="text" id="answer4" name="answer4" placeholder="Vierde antwoord"> is <input type="number" id="point4" name="point4" min="0" placeholder="..."> punten waard<br>
        <label for="answer5">Antwoord 5: </label><input type="text" id="answer5" name="answer5" placeholder="Vijfde antwoord"> is <input type="number" id="point5" name="point5" min="0" placeholder="..."> punten waard<br>
        <label for="answer6">Antwoord 6: </label><input type="text" id="answer6" name="answer6" placeholder="Zesde antwoord"> is <input type="number" id="point6" name="point6" min="0" placeholder="..."> punten waard<br>

        <select name="grade" id="grade">
        <option value="Grade1" selected>Graad 1</option>
        <option value="Grade2">Graad 2</option>
        <option value="Grade3">Graad 3</option>
        </select>
        <input type="hidden" id="placenumber" name="placenumber" value="${tent.id}"/>
        <input type="submit" value="Voeg vraag toe" onclick="alert('De vraag werd toegevoegd.');">

    </form>
</main>
<%@ include file="components/footer.jspf"%>
</body>
</html>
