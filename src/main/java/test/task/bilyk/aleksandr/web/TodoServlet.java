package test.task.bilyk.aleksandr.web;

import com.mashape.unirest.http.exceptions.UnirestException;
import test.task.bilyk.aleksandr.model.Todo;
import test.task.bilyk.aleksandr.repository.TodoRepository;
import test.task.bilyk.aleksandr.service.TodoServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class TodoServlet extends HttpServlet {
    private TodoRestController todoController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        todoController = new TodoRestController(new TodoServiceImpl(new TodoRepository()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            Todo todo = new Todo(request.getParameter("TodoName"), request.getParameter("Description"));
            try {
                if (request.getParameter("objectId").isEmpty()) {
                    todoController.create(todo);
                } else {
                    todoController.update(todo, getId(request));
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        } else if ("setTime".equals(action)) {
            if (!request.getParameter("time").isEmpty()) {
                todoController.deletingIfCompleted(Integer.parseInt(request.getParameter("time")));
            }

        }

        response.sendRedirect("todo");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                try {
                    Todo todoDelete = todoController.get(getId(request));
                    todoController.delete(todoDelete);
                    response.sendRedirect("todo");
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
            case "create":
                final Todo todo = new Todo("", "");
                request.setAttribute("todo", todo);
                request.getRequestDispatcher("/todoForm.jsp").forward(request, response);
                break;
            case "update":
                try {
                    final Todo todoUpdate = todoController.get(getId(request));
                    request.setAttribute("todo", todoUpdate);
                    request.getRequestDispatcher("/todoForm.jsp").forward(request, response);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
            case "setDeletingTime":
                request.getRequestDispatcher("/TodoDeletingTimePeriod.jsp").forward(request, response);
            case "all":
            default:
                try {
                    request.setAttribute("todo", todoController.getAll());
                    request.getRequestDispatcher("/todo.jsp").forward(request, response);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private String getId(HttpServletRequest request) {
        return Objects.requireNonNull(request.getParameter("objectId"));
    }
}
