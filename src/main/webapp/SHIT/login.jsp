<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- header -->
<%@ include file="components/header.jspf"%>

<!-- Navigation -->
<%@ include file="components/navigation.jspf"%>


<!-- Page Content -->
<div class="container">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2 id="pageTitle" class="text-center login-title">Please sign in</h2>
            <div class="account-wall">
                <img class="profile-img" src="/static/css/user.png" alt="">
                <form class="form-signin" method="post">
                <input id="usernameInput" type="text" name="username" class="form-control" placeholder="Username" value="${username}" required autofocus>
                <input id="passwordInput" type="password" name="password" class="form-control" placeholder="Password" required>
                <button id="submitButton" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                    <p id="errorMessage" class="error">${error}</p>
                </form>
            </div>
            <a href="./signup" class="text-center new-account">Create an account</a><br><br>
            <blockquote class="blockquote">demo accounts: <br>user/user<br>support/support<br>admin/admin</blockquote>
        </div>
    </div>
</div>

<!-- footer -->
<%@ include file="components/footer.jspf"%>
