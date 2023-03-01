<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Songs of an album</title>
    <link rel="stylesheet" type="text/css" href="./p3.css"/>
</head>
<body>
    <h1>Music Information Service</h1>
    <br>
    <h2>Query 1: Phase 3 (Country = ${bean.pcountry}, Album = ${bean.paid})</h2>
    <br>
    <h2><b>This is the query result:</b></h2>
    <ol>
        <c:forEach var="i" begin="0" end="${bean.songs.size() - 1}">
            <li>Title = '<c:out value="${bean.songs.get(i).getTitle()}"/>' --- Language = '<c:out value="${bean.songs.get(i).getLang()}"/>' --- Genres = '<c:out value="${bean.songs.get(i).getBien()}"/>' --- Composer = '<c:out value="${bean.songs.get(i).getComposer()}"/>'</li>
        </c:forEach>
    </ol>
    <br>
    <br>
    <button id="home" onclick='location.href="?pphase=01&p=22Rubben23"'>HOME</button>
    <button id="back" onclick='location.href="?pphase=12&p=22Rubben23&pcountry=${bean.pcountry}"'>BACK</button>
</body>
<br>
<br>
<b>@Ruben Gonzalez</b>
</html>