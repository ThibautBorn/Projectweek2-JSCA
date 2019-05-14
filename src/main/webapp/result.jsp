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

    <title>Result</title>

</head>

<html>
<%@ include file="components/header.jspf"%>
<body>

    <main class="resultaat">
        <h1>Resultaat</h1>
        <p>Je hebt de test tot een succesvol einde gebracht. Je hebt daarbij ${attemptTotalAttempted} vragen beantwoord. Hieronder vind je een gedetailleerd overzicht van je prestatie. </p>


        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
        <canvas id="myChart" width="400" height="400"></canvas>
        <script>
            var ctx = document.getElementById("myChart");
            var myChart = new Chart(ctx, {
                type: 'radar',
                data: {
                    labels: [<c:forEach items="${competences}" var="competence">"${competence.name}",</c:forEach>],
                    datasets: [{
                        label: 'Jouw prestatie',
                        data: [<c:forEach items="${competences}" var="competence">${LijstTentenPercentage[competence]},</c:forEach>],
                        backgroundColor: 'rgba(35, 112, 237, 0.2)',
                        borderColor: 'rgba(35,112,237,1)',
                        borderWidth: 4,
                        pointBorderWidth: 7,
                        pointStyle: 'rect',
                        pointBackgroundColor: 'rgba(35,112,237,1)',
                        pointBorderColor: 'rgba(35,112,237,1)'
                    }]
                },
                options: {
                    legend: {
                        labels: {
                            // This more specific font property overrides the global property
                            fontSize: 16
                        }
                    }}
            });
        </script>

    </main>

</body>

<%@ include file="components/footer.jspf"%>
</html>
