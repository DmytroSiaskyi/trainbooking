<!DOCTYPE>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<sec:authentication var="loggedInUser" property="principal"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title><decorator:title/></title>
    <link rel="icon" href="/static/images/logo.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="/static/css/style.css">
    <script type="text/javascript" src="/static/scripts/jquery-1.11.1.min.js"></script>
</head>
<body>
    <header>
        <div id="centerNavigation">
        <security:authorize access="isAnonymous()">
            <a href="/login?form=1" class="authButton">Вхід</a>
        </security:authorize>
        <security:authorize access="!isAnonymous()">
            <a href="/sales" class="authButton">Продаж квитків</a>
            <a href="/return" class="authButton">Повернення квитків</a>
            <a href="/statistic" class="authButton">Статистика</a>
            <a href="/logout" class="authButton">Вихід</a>
        </security:authorize>
        </div>
    </header>
    <decorator:body/>
    <footer>Сяський Дмитро, Київ 2015</footer>
</body>
</html>