<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Todo list</title>
    <style>
        body {
            margin: 50px;
        }
        .normal {
            color: green;
        }

        .completed {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Todo</h2>
    <a href="todo?action=create">Add Todo</a><br>
    <a href="todo?action=setDeletingTime">Set deleting time</a>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${todo}" var="todo">
            <jsp:useBean id="todo" scope="page" type="test.task.bilyk.aleksandr.model.Todo"/>
            <tr class="${todo.completed ? 'completed' : 'normal'}">
                <td>${todo.name}</td>
                <td>${todo.description}</td>
                <td><a href="todo?action=update&objectId=${todo.objectId}">Update</a></td>
                <td><a href="todo?action=delete&objectId=${todo.objectId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>