package test.task.bilyk.aleksandr.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import test.task.bilyk.aleksandr.model.Todo;

import java.util.ArrayList;

public interface BackendlessTodoService {


    void delete(Todo todo) throws UnirestException;

    ArrayList<Todo> getAll() throws UnirestException;

    void update(Todo todo, String objectId) throws UnirestException;

    Todo create(Todo todo) throws UnirestException;

    Todo findByIdAndGet(String ObjectId) throws UnirestException;



}
