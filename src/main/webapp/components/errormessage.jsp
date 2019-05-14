<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null && !errors.isEmpty()}">
    <c:forEach items="${errors}" var="error">
        <div class="submission_error">
            <c:out value="${error}" />
        </div>
    </c:forEach>
</c:if>