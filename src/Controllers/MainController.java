package Controllers;

import Database.DatabaseController;
import Entities.Band;
import Entities.BandMember;
import Entities.BandMembersAssociation;
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

    public boolean deleteWorker(Worker worker) {
        return DatabaseController.deleteWorker(worker);
    }

    public boolean addBand(Band band) {
        return DatabaseController.addBand(band);
    }

    public ArrayList<Band> getAllBands() {
        return DatabaseController.getAllBands();
    }

    public boolean changeBand(Band band) {
        return DatabaseController.changeBand(band);
    }

    public boolean deleteBand(String id) {
        return DatabaseController.deleteBand(id);
    }

    public ArrayList<BandMember> getAllBandMembers() {
        return DatabaseController.getAllBandMembers();
    }

    public boolean addBandMember(BandMember bandMember) {
        return DatabaseController.addBandMember(bandMember);
    }

    public boolean addBandMembersAssociation(BandMembersAssociation bandMembersAssociation) {
        return DatabaseController.addBandMembersAssociation(bandMembersAssociation);
    }

    public boolean changeBandMember(BandMember bandMember) {
        return DatabaseController.changeBandMember(bandMember);
    }

    public boolean deleteBandMember(int id) {
        return DatabaseController.deleteBandMember(id);
    }

    public ArrayList<BandMember> getAllBandMembersInBand(int band_id) {
        return DatabaseController.getAllBandMembersInBand(band_id);
    }

    public boolean removeBandMemberFromBand(int band_id, int band_member_id) {
        return DatabaseController.removeBandMemberFromBand(band_id, band_member_id);
    }
}
