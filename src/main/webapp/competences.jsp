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
    <link rel="stylesheet" href="static/css/linebars.css">
</head>

<body>

<%@ include file="components/header.jspf"%>

<main class="competences">
    <h1>Overzicht competenties</h1>
    <article>
        <ul>
            <c:forEach items="${competences}" var="competence">
                <li><h2 class="comp">${competence.name}</h2>
                    <c:if test="${userrole == 'student'}">
                    <div class="container">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="70" aria-valuemin="0" aria-valuemax="100" style="width:<c:out value="${LijstTentenPercentage[competence]}"/>%">
                                <span class="sr-only">70% Complete</span>
                            </div>
                        </div>
                    </div>
                    </c:if>
                <c:if test="${isAdmin == true}">
                    <a class="linkbutton" href="addQuestion?question=${competence.id}"><div class="linkbutton">Add Question</div></a>
                </c:if>
                </li>
            </c:forEach>
        </ul>
    </article>
    <c:if test="${userrole == 'student'}">
        <a class="starttestlink" href="test"><div id="starttest">Start een nieuwe test!</div></a>
    </c:if>
</main>

<%@ include file="components/footer.jspf"%>

</body>
</html>