<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="training.programming.util.Mappings" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Todo Items</title>
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    </head>
    <body>
        <div>
            <c:url var="addUrl" value="${Mappings.ADD_ITEM}"/>
            <a class="margin-top color" href="${addUrl}">New Item</a>
            <table border="1">

                <caption><h2>Todo Items</h2></caption>

                <tr>
                    <th>Title</th>
                    <th>Deadline</th>
                    <th>View</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>

                <c:forEach var="item" items="${todoData.items}">

                    <c:url var="viewUrl" value="${Mappings.VIEW_ITEM}">
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="editUrl" value="${Mappings.ADD_ITEM}">
                        <c:param name="id" value="${item.id}"/>
                    </c:url>
                    <c:url var="deleteUrl" value="${Mappings.DELETE_ITEM}">
                        <c:param name="id" value="${item.id}"/>
                    </c:url>

                    <tr>
                        <td><c:out value="${item.title}"/></td>
                        <td><c:out value="${item.deadline}"/></td>
                        <td><a href="${viewUrl}">View</a></td>
                        <td><a href="${editUrl}">Edit</a></td>
                        <td><a href="${deleteUrl}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </body>
</html>
