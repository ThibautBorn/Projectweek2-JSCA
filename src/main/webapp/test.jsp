<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sint-Jozefscollege Aarschot</title>
    <link rel="stylesheet" href="static/css/reset.css">
    <link rel="stylesheet" href="static/css/style.css">
</head>
<body>

<%@ include file="components/header.jspf"%>

<main class="test">
    <article>
        <h1>Test voor: <c:out value="${tentName}" /> (graad <c:out value="${grade.value}" />)</h1>
        <div class="container">
            <form method="post" action="test">
                <c:forEach items="${tentQuestions}" var="question">
                    <h2>
                        <c:out value="${question.value}" />
                    </h2>
                    <div class="answercontainer">
                        <c:forEach items="${service.getAnswers(question.getId())}" var="answer">
                            <input type="radio" name="${question.id}" value="${answer.id}"><c:out value="${answer.value}" /><br>
                        </c:forEach>
                    </div>
                    <br>
                </c:forEach>
                <input type="hidden" id="tent" name="tent" value="${tentNumber}">
                <input type="submit" value="${buttonLabel}">
            </form>

        </div>
    </article>
</main>

<%@ include file="components/footer.jspf"%>

</body>
</html>
