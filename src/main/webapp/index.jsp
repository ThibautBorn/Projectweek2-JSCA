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

<main class="frontpage">
   <h1>Welkom ${name}!</h1>
   <p>${welcomeText}</p>

   <c:if test="${userrole =='student'}">
      <a class="starttestlink" href="test"><div id="starttest">Start jouw test!</div></a>
   </c:if>
   <c:if test="${userrole =='teacher'}">
      <a class="starttestlink" href="studentResults"><div id="starttest">Bekijk de resultaten</div></a>
   </c:if>
   <c:if test="${userrole =='admin'}">
       <a class="starttestlink" href="competences"><div id="starttest">Beheer de test</div></a>
   </c:if>

</main>

<%@ include file="components/footer.jspf"%>

</body>
</html>