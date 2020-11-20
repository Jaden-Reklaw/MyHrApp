<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Aston Technologies HR Application</title>

    <%--FAVICON--%>

    <%--JQuery through maven dependency--%>
    <c:url value="/webjars/jquery/2.1.4/jquery.min.js" var="jquery" />
    <script src="${jquery}"></script>

    <%--Bootstrap--%>
    <c:url value="/webjars/bootstrap/3.3.4/js/bootstrap.min.js" var="bootstrapJS" />
    <script src="${bootstrapJS}"></script>
    <c:url value="/webjars/bootstrap/3.3.4/css/bootstrap.min.css" var="bootstrapCSS" />
    <link href="${bootstrapCSS}" rel="stylesheet" media="screen" />

    <%--Bootswatch free bootstrap themes--%>
<%--    <c:url value="/static/css/bootswatch.css" var="bootstrapCSS" />--%>
<%--    <link href="${bootstrapCSS}" rel="stylesheet" media="screen" />--%>

    <%--Custom JS--%>
    <c:url value="/static/js/common.js" var="common" />
    <script src="${bootstrapJS}"></script>

    <%--Custom CSS--%>
    <c:url value="/static/css/astonengineer.css" var="bootstrapCustom" />
    <link href="${bootstrapCustom}" rel="stylesheet" media="screen" />
</head>
<body>

