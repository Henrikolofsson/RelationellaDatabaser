package Database;

import Entities.Band;
import Entities.BandMember;
import Entities.BandMembersAssociation;
import Entities.Worker;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/FestivalDB";
    private static String user = "postgres";
    private static String password = "123Henrik123";
    private static Connection connection;

    public static Connection connect() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, user, password);
        //    System.out.println("Connection to database successful.");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void disconnect() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(SQLException e) {
            System.out.println("Failed to disconnect from database.");
        }
    }

    public static boolean login(String adminUsername, char[] adminPassword) {
        try {
            //String pw = "{" + String.valueOf(adminPassword) +"}";
            String pw = String.valueOf(adminPassword);
            PreparedStatement statement = connection.prepareStatement("SELECT name, password FROM administrators WHERE name = ? AND password::varchar = ?");
            statement.setString(1, adminUsername);
            statement.setString(2, pw);
            ResultSet rs = statement.executeQuery();

            if(rs.next()) {
                return true;
            }
            statement.close();
            rs.close();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean addWorker(String workerPersonNumber, String workerName, String workerAddress) {
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS workers (" +
                                                                            "id VARCHAR(12) PRIMARY KEY NOT NULL," +
                                                                            "workersname TEXT NOT NULL," +
                                                                            "workersaddress TEXT NOT NULL);");
            statement.execute();
            statement.close();

            statement = connection.prepareStatement("INSERT INTO workers (id, workersname, workersaddress)" +
                    " VALUES ('"+ workerPersonNumber + "','" + workerName + "','" + workerAddress + "');");
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;

        } catch(PSQLException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Worker> getAllWorkers() {
        connect();
        try {
            ArrayList<Worker> listOfWorkers = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers WHERE EXISTS (SELECT workersname) ORDER BY workersname ASC; ");
            ResultSet rs = statement.executeQuery();



            while(rs.next()) {
                Worker worker = new Worker(rs.getString(1), rs.getString(2), rs.getString(3));
                listOfWorkers.add(worker);
            }
            statement.close();
            rs.close();
            disconnect();
            return listOfWorkers;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: change worker
    public static boolean changeWorker(Worker worker) {
        connect();
        try {
            String str = "UPDATE workers SET workersname = '" + worker.getName() + "'," + " workersaddress = '"
                    + worker.getAddress() + "' WHERE id = '" + worker.getPerson_number() + "';" ;
            System.out.println(str);
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteWorker(Worker worker) {
        connect();
        try {
            String str = "DELETE FROM workers WHERE id = '" + worker.getPerson_number() + "';";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addBand(Band band) {
        connect();
        try {
            String str = "CREATE TABLE IF NOT EXISTS bands (" +
                    "id serial PRIMARY KEY NOT NULL," +
                    "bandsname TEXT NOT NULL," +
                    "bandscountry TEXT NOT NULL," +
                    "bandsinfo TEXT NOT NULL," +
                    "workerid VARCHAR(12) NOT NULL, " +
                 //   "CONSTRAINT fk_workers " +
                    "FOREIGN KEY(workerid) " +
                    "REFERENCES workers(id) " +
                    "ON DELETE CASCADE);";
            System.out.println(str);

            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO bands (id, bandsname, bandscountry, bandsinfo, workerid)" +
                            " VALUES (DEFAULT, '" + band.getBand_name() + "', '" + band.getBand_country_of_origin() + "', '" +
                            band.getBand_info() + "', '" + band.getContact_person_id() + "');";
            statement = connection.prepareStatement(insert);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Band> getAllBands() {
        connect();
        try {
            ArrayList<Band> listOfBands = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bands WHERE EXISTS (SELECT bandsname) ORDER BY bandsname ASC; ");
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Band band = new Band(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                listOfBands.add(band);
            }
            statement.close();
            rs.close();
            disconnect();
            return listOfBands;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean changeBand(Band band) {
        connect();
        try {
            String str1 = "UPDATE bands SET bandsname = '" + band.getBand_name() + "', " + "bandscountry = '" +
                    band.getBand_country_of_origin() + "', bandsinfo = '" + band.getBand_info() + "', workerid = '" + band.getContact_person_id() + "' WHERE id = '" + band.getBand_id() + "';";
            PreparedStatement statement = connection.prepareStatement(str1);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteBand(String id) {
        connect();
        try {
            String str = "DELETE FROM bands WHERE id ='" + id + "';";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean addBandMember(BandMember bandMember) {
        connect();
        try {
          String str = "CREATE TABLE IF NOT EXISTS bandmembers (" +
                        "bandmemberid serial PRIMARY KEY NOT NULL, " +
                        "bandmembername TEXT NOT NULL, " +
                        "bandmemberinfo TEXT NOT NULL);";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO bandmembers (bandmemberid, bandmembername, bandmemberinfo) VALUES (DEFAULT, '" + bandMember.getBand_member_name() + "', '" + bandMember.getBand_member_info() + "');";
            System.out.println(insert);
            statement = connection.prepareStatement(insert);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<BandMember> getAllBandMembers() {
        connect();
        try {
            ArrayList<BandMember> bandMembers = new ArrayList<>();
            String str = "SELECT * FROM bandmembers WHERE EXISTS (SELECT bandmemberid) ORDER BY bandmembername ASC;";
            PreparedStatement statement = connection.prepareStatement(str);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                BandMember bandMember = new BandMember(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3));
                bandMembers.add(bandMember);
            }
            statement.close();
            rs.close();
            disconnect();
            return bandMembers;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addBandMembersAssociation(BandMembersAssociation bandMembersAssociation) {
        connect();
        try {
            String str = "CREATE TABLE IF NOT EXISTS bandmembersassociation (" +
                    "bandmembersassociationid serial PRIMARY KEY NOT NULL, " +
                    "bandmembersassociationbandid integer NOT NULL, " +
                    "bandmembersassociationbandmemberid integer NOT NULL);";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO bandmembersassociation (bandmembersassociationid, bandmembersassociationbandid, bandmembersassociationbandmemberid) VALUES (DEFAULT, '" +
                    bandMembersAssociation.getBand_id() + "', '" + bandMembersAssociation.getBand_member_id() + "');";
            statement = connection.prepareStatement(insert);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean changeBandMember(BandMember bandMember) {
        connect();
        try {
            String str = "UPDATE bandmembers SET bandmembername = '" + bandMember.getBand_member_name() + "', bandmemberinfo = '" + bandMember.getBand_member_info() + "' WHERE bandmemberid = '" + bandMember.getBand_member_id() + "';";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteBandMember(int id) {
        connect();
        try {
            String str = "DELETE FROM bandmembers WHERE bandmemberid = '" + id + "';";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String str1 = "DELETE FROM bandmembersassociation WHERE bandmembersassociationbandmemberid = '" + id + "';";
            statement = connection.prepareStatement(str1);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //TODO: Get band members instead of strings!!!
    public static ArrayList<BandMember> getAllBandMembersInBand(int band_id) {
        connect();
        try {
            ArrayList<BandMember> bandMembers = new ArrayList<>();
            String test = "SELECT bm.bandmemberid,bm.bandmembername,bm.bandmemberinfo FROM bandmembersassociation bma INNER JOIN bandmembers bm ON bma.bandmembersassociationbandmemberid=bm.bandmemberid where bma.bandmembersassociationbandid='" + band_id + "';";
            String str = "SELECT * FROM bandmembersassociation WHERE bandmembersassociationbandid = '" + band_id + "';";
            PreparedStatement statement = connection.prepareStatement(test);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                System.out.println("Rs1" + rs.getString(1));
                System.out.println("Rs2" +rs.getString(2));
                System.out.println("Rs3" +rs.getString(3));
                BandMember bm = new BandMember(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(2));
                bandMembers.add(bm);
            }
            statement.close();
            disconnect();
            return bandMembers;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean removeBandMemberFromBand(int band_id, int band_member_id) {
        connect();
        try {
            System.out.println(band_id);
            System.out.println(band_member_id);
            String sql = "DELETE FROM bandmembersassociation WHERE bandmembersassociationbandid = " + band_id + " AND bandmembersassociationbandmemberid = " + band_member_id + ";";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            disconnect();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
