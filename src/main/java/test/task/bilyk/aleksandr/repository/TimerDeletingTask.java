package test.task.bilyk.aleksandr.repository;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.TimerTask;

public class TimerDeletingTask extends TimerTask {
    private final String url = "https://api.backendless.com/2BEF7A3A-FBD7-8C82-FF35-7B3A8F5FF000/4CCD00F1-EA71-DDB1-FF26-ED21E7C01800/data/bulk/TODO?where=";

    @Override
    public void run() {
        deletingIfCompleted();
    }

    private void deletingIfCompleted () {
        try {
            Unirest.delete(url + "Completed%3D%27true%27")
                    .header("accept", "application/json").asJson();
        } catch (UnirestException e) {
            System.out.println("UnirestException");
        }
    }
}
