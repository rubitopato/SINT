<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Albums released in country</title>
    <link rel="stylesheet" type="text/css" href="./p3.css"/>
</head>
<body>
    <h1>Music Information Service</h1>
    <br>
    <h2>Query 1: Phase 2 (Country = ${bean.pcountry})</h2>
    <br>
    <h2><b>Please, select an album</b></h2>
    <ol>
        <c:forEach var="i" begin="0" end="${bean.albums.size() - 1}">
            <li><a href='?pphase=13&p=22Rubben23&pcountry=<c:out value="${bean.pcountry}"/>&paid=<c:out value="${bean.albums.get(i).getAid()}"/>'>Album = <c:out value="${bean.albums.get(i).getName()}"/> --- Year = '<c:out value="${bean.albums.get(i).getYear()}"/>' --- Performer = '<c:out value="${bean.albums.get(i).getPerformer()}"/>' --- Review = '<c:out value="${bean.albums.get(i).getDescription()}"/>'</a></li>
        </c:forEach>
    </ol>
    <br>
    <br>
    <form method="GET" name="form">
        <input type="hidden" name="p" value="22Rubben23">
        <input type="hidden" name="pphase" value="01">
        <input type="submit" id="home" value="HOME" onclick="form.pphase.value=&quot;01&quot;">
        <input type="submit" id="back" value="BACK" onclick="form.pphase.value=11">
        </form>
        </body>
        <br>
        <br>
        <b>@Ruben Gonzalez</b>
    </html>