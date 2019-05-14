<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<%@ include file="components/header.jspf" %>

<main class="studentResults">
    <article>
        <h1>Resultaten van klas</h1>
        <div class="container">
            <c:forEach items="${classGroups}" var="group">
                <div class="resultTableDiv">
                    <h2>${group.name}</h2>
                    <table class="resultTable">
                        <tr>
                            <th>Naam</th>
                            <c:forEach items="${competences}" var="tent">
                                <th>${tent.name}</th>
                            </c:forEach>
                        </tr>
                        <c:forEach items="${group.students}" var="student">
                            <tr>
                                <td>${student.fullName}</td>
                                <c:forEach items="${competences}" var="tent">
                                    <td><fmt:formatNumber value="${resultsPerStudents[group][student][tent]}"
                                                          type="number" maxFractionDigits="2"/></td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>Gemiddelde</td>
                            <c:forEach items="${competences}" var="tent">
                                <td><fmt:formatNumber value="${resultsAvarageOfStudents[group][tent]}" type="number"
                                                      maxFractionDigits="2"/></td>
                            </c:forEach>
                        </tr>
                    </table>
                </div>
            </c:forEach>
        </div>
    </article>
</main>

<%@ include file="components/footer.jspf" %>

</body>
</html>
