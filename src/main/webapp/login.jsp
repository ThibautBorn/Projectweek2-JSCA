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
   <link rel="stylesheet" href="static/css/forms.css">
</head>

<header>
<img src="static/images/logo.png" />
</header>

<body>

<main class="login">

   <form method="post" action="login">
      <h1>Meld je aan op de app</h1>
      <input type="text" id="username" name="username" placeholder="Username" required>
      <input type="password" id="password" name="password"  placeholder="Password" required>
      <input type="submit" value="Aanmelden">
      <%@include file="components/errormessage.jsp"%>
   </form>
</main>

<%@ include file="components/footer.jspf"%>

</body>
</html>