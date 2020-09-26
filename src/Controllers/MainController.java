package Controllers;

import Database.DatabaseController;
import Entities.Worker;
import GUI.FestivalWindow;

import java.util.ArrayList;

public class MainController {
    private DatabaseController dbController;
    private FestivalWindow window;

    public MainController() {
        dbController = new DatabaseController();
    }

    public void setFestivalWindow(FestivalWindow window) {
        this.window = window;
    }

    public void login(String adminUsername, char[] adminPassword) {
        if(DatabaseController.connect() != null){
            if(DatabaseController.login(adminUsername, adminPassword)) {
                System.out.println("Login success");
                window.setAdminLoggedIn();
                DatabaseController.disconnect();
            } else {
                System.out.println("Login failed");
            }
        } else {
            System.out.println("Database failed to connect");
        }
    }

    public void bookBand(String bandName, String bandCountry, String bandInfo, String workerPersonNumber) {

    }

    public boolean addWorker(String workerPersonNumber, String workerName, String workerAddress) {
        return (DatabaseController.addWorker(workerPersonNumber, workerName, workerAddress));
    }

    public ArrayList<Worker> getAllWorkers() {
        return DatabaseController.getAllWorkers();
    }

    public boolean changeWorker(Worker worker) {
        return DatabaseController.changeWorker(worker);
    }
}
