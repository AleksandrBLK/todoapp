package test.task.bilyk.aleksandr.repository;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import test.task.bilyk.aleksandr.model.Todo;

import java.util.ArrayList;

public class TodoRepository implements BackendlessTodoRepository {
    private final String url = "https://api.backendless.com/2BEF7A3A-FBD7-8C82-FF35-7B3A8F5FF000/4CCD00F1-EA71-DDB1-FF26-ED21E7C01800/data/TODO";


    @Override
    public Todo save(Todo todo) throws UnirestException {
        if (todo.isNew()) {
                HttpResponse<JsonNode> response = Unirest.post(url)
                        .header("accept", "application/json")
                        .body("{\"TodoName\":\"" + todo.getName() + "\", \"Description\" :\"" + todo.getDescription() + "\", \"Completed\":\"false\"}")
                        .asJson();
                if (response.getStatus() == 200) {
                    JSONObject responseBody = response.getBody().getObject();
                    String objectId = (String) responseBody.get("objectId");
                    todo.setObjectId(objectId);

                    return null;
                }


        } else {
            Unirest.put(url + "/" + todo.getObjectId())
                    .header("accept", "application/json")
                    .body("{\"TodoName\":\"" + todo.getName() + "\", \"Description\" :\"" + todo.getDescription() + "\", \"Completed\":\"" + todo.isCompleted() + "\"}")
                    .asJson();

        }


        return todo;

    }

    @Override
    public void delete(Todo todo) throws UnirestException {
        if (!todo.isCompleted()){
            todo.setCompleted();
            save(todo);
        }
    }




    @Override
    public ArrayList<Todo> getAll() throws UnirestException {
        HttpResponse<String> response = Unirest.get(url + "/count").asString();
        int count = Integer.parseInt(response.getBody());
        HttpResponse<JsonNode> response1 = Unirest.get(url + "?pageSize=" + count).asJson();
        JSONArray list = response1.getBody().getArray();
        ArrayList<Todo> todoList = new ArrayList<>();

        /* Unirest библиотека вызывает конфликт при сброке Maven,
        заменил на более старые версии зависимостей
        которые нужны Unirest в pom.xml,
        нет поддержки лямбд, foeeach (Java 8).
        list.forEach(item -> {
            JSONObject obj = (JSONObject) item;
            String todoName = (String) obj.get("TodoName");
            String description = (String) obj.get("Description");
            boolean completed = Boolean.parseBoolean((String)obj.get("Completed"));
            String objectId = (String) obj.get("objectId");
            todoList.add(new Todo(todoName, description, completed, objectId));
        } );
        */

        for (int i = 0; i < list.length(); i++){
            JSONObject obj = (JSONObject) list.get(i);
            String todoName = (String) obj.get("TodoName");
            String description = (String) obj.get("Description");
            boolean completed = Boolean.parseBoolean((String)obj.get("Completed"));
            String objectId = (String) obj.get("objectId");
            todoList.add(new Todo(todoName, description, completed, objectId));
        }
        return todoList;
    }

    @Override
    public boolean findById(String objectId) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(url + "/" + objectId).asJson();
        return response.getStatus() == 200;
    }

    @Override
    public Todo findByIdAndGet(String ObjectId) throws UnirestException{
        HttpResponse<JsonNode> response = Unirest.get(url + "/" + ObjectId).asJson();
        JSONObject obj = response.getBody().getObject();
        String todoName = (String) obj.get("TodoName");
        String description = (String) obj.get("Description");
        boolean completed = Boolean.parseBoolean((String)obj.get("Completed"));
        String objectId = (String) obj.get("objectId");

        return new Todo(todoName, description, completed, objectId);
    }

}
