<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="training.programming.util.Mappings" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Todo List Application</title>
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    </head>
    <body>
        <div>
            <c:url var="itemsLink" value="${Mappings.ITEMS}"/>
            <h2><a class="color" href="${itemsLink}">Show Todo Items</a></h2>
        </div>
    </body>
</html>
