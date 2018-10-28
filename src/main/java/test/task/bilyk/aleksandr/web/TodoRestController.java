package test.task.bilyk.aleksandr.web;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.task.bilyk.aleksandr.model.Todo;
import test.task.bilyk.aleksandr.service.BackendlessTodoService;

import java.util.ArrayList;

public class TodoRestController {
    private final BackendlessTodoService service;
    private static final Logger log = LoggerFactory.getLogger(TodoRestController.class);

    public TodoRestController(BackendlessTodoService service) {
        this.service = service;
    }


    public void delete(Todo todo) throws UnirestException {
        log.info("delete todo {}", todo);
        service.delete(todo);
    }

    public ArrayList<Todo> getAll() throws UnirestException {
        log.info("get all todo items {}");
        return service.getAll();
    }

    public Todo create(Todo todo) throws UnirestException {
        log.info("create todo {}", todo);
        return service.create(todo);
    }

    public void update(Todo todo, String objectId) throws UnirestException {
        log.info("update todo {}", todo, objectId);
        if (todo == null) {
            System.out.println("Todo cant be null");
        } else {
            service.update(todo, objectId);
        }
    }

    public Todo get(String ObjectId) throws UnirestException {
        Todo todo = service.findByIdAndGet(ObjectId);
        log.info("get todo {}", todo);
        return todo;
    }

    public void deletingIfCompleted (int minutes) {
        log.info("deletingIfCompleted {}", minutes);
        service.deletingIfCompleted(minutes);
    }




}
