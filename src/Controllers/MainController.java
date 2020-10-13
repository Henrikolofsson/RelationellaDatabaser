package Controllers;

import Database.DatabaseController;
import Entities.*;
import GUI.FestivalWindow;

import java.util.ArrayList;
/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The MainController is the class that fetches information from the database via the database controller and returns the information to the GUI.
 */

public class MainController {
    private DatabaseController dbController;
    private FestivalWindow window;

    public MainController() {
        dbController = new DatabaseController();
    }

    public void setFestivalWindow(FestivalWindow window) {
        this.window = window;
    }

    /*
        Checks if the database controller is connected to the database, and if it is, it sends the admin user information to see if it matches the database.
     */
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

    public boolean addConcert(Concert concert) {
        return DatabaseController.addConcert(concert);
    }

    public Concert getConcertByBand(String bandId) {
        return DatabaseController.getConcertByBand(bandId);
    }

    public boolean changeConcert(int id, Concert concert) {
        return DatabaseController.changeConcert(id,concert);
    }

    public boolean deleteConcert(String concert_id) {
        return DatabaseController.deleteConcert(concert_id);
    }

    public ArrayList<Concert> getAllConcerts() {
        return DatabaseController.getAllConcerts();
    }
}
