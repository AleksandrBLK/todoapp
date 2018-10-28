<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Todo</title>
    <style>
        body {
            margin: 50px;
        }
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>${param.action == 'create' ? 'Create todo' : 'Edit todo'}</h2>
    <hr>
    <jsp:useBean id="todo" type="test.task.bilyk.aleksandr.model.Todo" scope="request"/>

    <form method="post" action="todo">
        <input type="hidden" name="objectId" value="${todo.objectId}">
        <dl>
            <dt>TODO name:</dt>
            <dd><input type="text" value="${todo.name}" size=40 name="TodoName" required></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${todo.description}" name="Description" required></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
