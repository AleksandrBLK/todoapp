package test.task.bilyk.aleksandr.repository;

import com.mashape.unirest.http.exceptions.UnirestException;
import test.task.bilyk.aleksandr.model.Todo;

import java.util.ArrayList;

public interface BackendlessTodoRepository {

    Todo save(Todo todo) throws UnirestException;

    void delete(Todo todo) throws UnirestException;

    ArrayList<Todo> getAll() throws UnirestException;

    boolean findById (String objectID) throws UnirestException;

    Todo findByIdAndGet(String objectId) throws UnirestException;



}
