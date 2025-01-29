<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Sign Up</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/login.css">
</head>
<body>

<%
    // Check if user is already authenticated via session
    if (session != null && session.getAttribute("isAuthenticated") != null &&
        (Boolean) session.getAttribute("isAuthenticated")) {
        response.sendRedirect("ItemController?action=LOAD_ITEMS");
        return;
    }

    // Check if cookie exists for username
    Cookie[] cookies = request.getCookies();
    String usernameFromCookie = null;
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                usernameFromCookie = cookie.getValue();
                break;
            }
        }
    }

    // If a cookie exists, recreate the session
    if (usernameFromCookie != null) {
        session = request.getSession(true); // Create a new session
        session.setAttribute("username", usernameFromCookie);
        session.setAttribute("isAuthenticated", true);
        response.sendRedirect("ItemController?action=LOAD_ITEMS");
        return;
    }
%>

<div class="login-wrap">
    <div class="login-html">
        <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
        <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
        <div class="login-form">
            <form class="sign-in-htm" action="ItemController" method="post">
                <div class="group">
                    <label for="user1" class="label">Username</label>
                    <input id="user1" type="text" name="usernamein" class="input" required>
                </div>
                <div class="group">
                    <label for="pass2" class="label">Password</label>
                    <input id="pass2" type="password" name="passwordin" class="input" data-type="password" required>
                </div>
                <div class="group">
                    <input id="check" type="checkbox" class="check" checked>
                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                </div>
                <input type="hidden" name="action" value="SIGN_IN">
                <div class="group">
                    <input type="submit" class="button" value="Sign In">
                </div>
                <div class="hr"></div>
                <c:if test="${param.error == 'invalid'}">
                    <div class="error-message">Invalid username or password</div>
                </c:if>
            </form>
            
            <form class="sign-up-htm" action="ItemController" method="post">
                <div class="group">
                    <label for="user" class="label">Username</label>
                    <input id="user" type="text" name="usernameup" class="input" required>
                </div>
                <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" name="passwordup" class="input" data-type="password" required>
                </div>
                <input type="hidden" name="action" value="SIGN_UP">
                <div class="group">
                    <input type="submit" class="button" value="Sign Up">
                </div>
                <div class="hr"></div>
            </form>
        </div>
    </div>
</div>
</body>
</html>