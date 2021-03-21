<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul>
            <li><a href="/" class="btn btn--without-border">Strona główna</a></li>
            <li><a href="#con" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <h3>Nazwa użytkownika</h3>
            <form:input path="username"/>
        </div>
        <div>
            <form:errors path="username"/>
        </div>
        <div class="form-group">
            <h3>Imię</h3>
            <form:input path="name"/>
        </div>
        <div>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <h3>Nazwisko</h3>
            <form:input path="surname"/>
        </div>
        <div>
            <form:errors path="surname"/>
        </div>
        <div class="form-group">
            <h3>Email</h3>
            <form:input path="email"/>
        </div>
        <div>
            <form:errors path="email"/>
        </div>
        <div class="form-group">
            <h3>Hasło</h3>
            <form:password path="password"/>
        </div>
        <div>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <h3>Powtórz hasło</h3>
            <input type="password" id="repass" name="repass">
        </div>
        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>

    </form:form>
</section>

<footer>
    <div class="contact" id="con">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form>
            <div class="form-group form-group--50">
                <input type="text" name="name" placeholder="Imię"/>
            </div>
            <div class="form-group form-group--50">
                <input type="text" name="surname" placeholder="Nazwisko"/>
            </div>

            <div class="form-group">
            <textarea
                    name="message"
                    placeholder="Wiadomość"
                    rows="1"
            ></textarea>
            </div>

            <button class="btn" type="submit">Wyślij</button>
        </form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2021</span>
        <div class="bottom-line--icons">
            <a href="https://pl-pl.facebook.com" class="btn btn--small"
            ><img src="images/icon-facebook.svg"
            /></a>
            <a href="https://www.instagram.com" class="btn btn--small"
            ><img src="images/icon-instagram.svg"
            /></a>
        </div>
    </div>
</footer>
</body>
</html>
