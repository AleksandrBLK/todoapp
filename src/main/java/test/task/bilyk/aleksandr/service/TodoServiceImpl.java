package test.task.bilyk.aleksandr.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import test.task.bilyk.aleksandr.model.Todo;
import test.task.bilyk.aleksandr.repository.BackendlessTodoRepository;
import test.task.bilyk.aleksandr.repository.TimerDeletingTask;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TodoServiceImpl implements BackendlessTodoService {
    private final BackendlessTodoRepository repository;


    public TodoServiceImpl(BackendlessTodoRepository repository) {
        this.repository = repository;
    }


    @Override
    public void delete(Todo todo) throws UnirestException {
        if (repository.findById(todo.getObjectId())) {
            repository.delete(todo);
        }

    }

    @Override
    public ArrayList<Todo> getAll() throws UnirestException {
        return repository.getAll();
    }

    @Override
    public void update(Todo todo, String objectId) throws UnirestException {
        if (repository.findById(objectId)) {
            todo.setObjectId(objectId);
            repository.save(todo);
        }

    }

    @Override
    public Todo create(Todo todo) throws UnirestException {
        if (todo == null) {
            System.out.println("Todo must be not a null");
        }

        return repository.save(todo);
    }

    @Override
    public Todo findByIdAndGet(String ObjectId) throws UnirestException {
        if (repository.findById(ObjectId)) {
            return repository.findByIdAndGet(ObjectId);
        }
        return null;
    }

    @Override
    public void deletingIfCompleted(int minutes) {
        TimerTask timerTask = new TimerDeletingTask();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, minutes * 60000, minutes * 60000);
    }
}
