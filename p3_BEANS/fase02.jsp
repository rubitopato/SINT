<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Files discarded</title>
    <link rel="stylesheet" type="text/css" href="./p3.css"/>
</head>
<body>
    <h1>Music Information Service</h1>
    <br>
    <h2><b>Files with errors: </b>${bean.errorFiles.size()}</h2>
    <ol>
        <c:forEach var="i" begin="0" end="${bean.errorFiles.size() - 1}">
            <li><c:out value="${bean.errorFiles.get(i)}"/></li>
        </c:forEach>
    </ol>
    <br>
    <br>
    <h2><b>Files with fatal errors: </b>${bean.fatalFiles.size()}</h2>
    <ol>
        <c:forEach var="i" begin="0" end="${bean.fatalFiles.size() - 1}">
            <li><c:out value="${bean.fatalFiles.get(i)}"/></li>
        </c:forEach>
    </ol>
    <br>
    <br>
    <form method="GET" name="form">
        <input type="hidden" name="p" value="22Rubben23">
        <input type="hidden" name="pphase" value="01">
        <input type="submit" id="back" value="BACK" onclick="form.pphase.value=&quot;01&quot;">
    </form>
</body>
<br>
<br>
<b>@Ruben Gonzalez</b>
</html>