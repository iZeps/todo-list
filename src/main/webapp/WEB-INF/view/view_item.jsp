<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="training.programming.util.Mappings" %>

<html>
    <head>
        <title>View Item: <c:out value="${todoItem.title}"/></title>
    </head>
    <body>
        <div align="center">
            <table border="1" cellpadding="5">

                <caption><h2>View Item: <c:out value="${todoItem.title}"/></h2></caption>

                <tr>
                    <th>Id</th>
                    <th>Title</th>
                    <th>Details</th>
                    <th>Deadline</th>
                </tr>

                <tr>
                    <td><c:out value="${todoItem.id}"/></td>
                    <td><c:out value="${todoItem.title}"/></td>
                    <td><c:out value="${todoItem.details}"/></td>
                    <td><c:out value="${todoItem.deadline}"/></td>
                </tr>

            </table>

            <c:url var="itemsLink" value="${Mappings.ITEMS}"/>
            <a href="${itemsLink}">Back to All</a>
        </div>
    </body>
</html>
