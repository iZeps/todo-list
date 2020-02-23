<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="training.programming.util.AttributeName" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Items</title>
    </head>
    <body>
        <div>
            <form:form method="POST" modelAttribute="${AttributeName.TODO_ITEMS}">
                <table>
                    <tr>
                        <td><label>ID</label></td>
                        <td>
                            <form:input path="id" disabled="true"/>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Title</label></td>
                        <td><form:input path="title"/></td>
                    </tr>
                    <tr>
                        <td><label>Deadline</label></td>
                        <td><form:input path="deadline"/></td>
                    </tr>
                    <tr>
                        <td><label>Details</label></td>
                        <td><form:textarea path="details"/></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit"/></td>
                    </tr>
                </table>
            </form:form>
        </div>
    </body>
</html>
