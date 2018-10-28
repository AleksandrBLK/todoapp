package test.task.bilyk.aleksandr.model;


public class Todo {
    private String name;
    private String description;
    private String objectId;
    private boolean completed;

    public Todo(String name) {
        this.name = name;
        this.completed = false;
        this.objectId = null;
        this.description = "";
    }

    public Todo(String name, String description) {
        this.name = name;
        this.description = description;
        this.completed = false;
        this.objectId = null;
    }

    public Todo(String name, String description, boolean completed, String objectId) {
        this.name = name;
        this.description = description;
        this.objectId = objectId;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public boolean isNew() {
        return objectId == null;
    }

    public String getObjectId(){
        return objectId;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public void setObjectId(String objectId){
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", objectId='" + objectId + '\'' +
                ", completed=" + completed +
                '}';
    }
}
