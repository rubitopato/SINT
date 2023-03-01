<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
    <html>
    <head>
        <title>Years Available</title>
        <link rel="stylesheet" type="text/css" href="./p3.css"/>
    </head>
        <body>
        <h1>Query 1: Phase 1</h1>
        <br>
        <h2><b>Please; select a Country</b></h2>
        <ol>
            <c:forEach var="i" begin="0" end="${bean.countries.size() - 1}">
                <li><a href='?pphase=12&p=22Rubben23&pcountry=<c:out value="${bean.countries.get(i)}"/>'><c:out value="${bean.countries.get(i)}"/></a></li>
            </c:forEach>
        </ol>
        <br>
        <br>
        <form method="GET" name="form">
        <input type="hidden" name="p" value="22Rubben23">
        <input type="hidden" name="pphase" value="01">
        <input type="submit" id="home" value="HOME" onclick="form.pphase.value=&quot;01&quot;">
        </form>
        </body>
        <br>
        <br>
        <b>@Ruben Gonzalez</b>
    </html>