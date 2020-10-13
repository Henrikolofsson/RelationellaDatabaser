package Database;

import Entities.*;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
/*
    @Author: Henrik Olofsson
    @Date: 2020-10-12
    The DatabaseController is the class which fetches information from the database and returns it to the main controller.
 */
//TODO: Add the data to the queries with setString to prevent SQL injection
public class DatabaseController {
    private static String DB_URL = "jdbc:postgresql://localhost:5432/FestivalDB";
    private static String user = "postgres";
    private static String password = "123Henrik123";
    private static Connection connection;

    /*
        Opens up a connection to the database.
     */
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

    /*
        Shuts down the connection to the database.
     */
    public static void disconnect() {
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch(SQLException e) {
            System.out.println("Failed to disconnect from database.");
        }
    }

    /*
        Tries to log in the administrator, and if the name and password is the same as in the database, the log in function returns true.
     */
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

    /*
        Checks if a table for workers exist, if it does not - it will create a table for workers.
        After that it inserts the worker into the table.
     */
    public static boolean addWorker(String workerPersonNumber, String workerName, String workerAddress) {
        connect();
        try {
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS workers (" +
                                                                            "worker_id VARCHAR(12) PRIMARY KEY NOT NULL," +
                                                                            "worker_name TEXT NOT NULL," +
                                                                            "worker_address TEXT NOT NULL);");
            statement.execute();
            statement.close();

            statement = connection.prepareStatement("INSERT INTO workers (worker_id, worker_name, worker_address)" +
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

    /*
        Fetches all the workers in the database as an ArrayList of workers.
     */
    public static ArrayList<Worker> getAllWorkers() {
        connect();
        try {
            ArrayList<Worker> listOfWorkers = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM workers WHERE EXISTS (SELECT worker_name) ORDER BY worker_name ASC; ");
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

   /*
        Change a workers information, except of the ID(person number), as it is unique for every person.
    */
    public static boolean changeWorker(Worker worker) {
        connect();
        try {
            String str = "UPDATE workers SET worker_name = '" + worker.getName() + "'," + " worker_address = '"
                    + worker.getAddress() + "' WHERE worker_id = '" + worker.getPerson_number() + "';" ;
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

    /*
        Deletes an entry of a worker.
     */
    public static boolean deleteWorker(Worker worker) {
        connect();
        try {
            String str = "DELETE FROM workers WHERE worker_id = '" + worker.getPerson_number() + "';";
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

    /*
       Checks if a table for bands exist, if it does not - it will create a table for bands.
       After that it inserts the band into the table.
    */
    public static boolean addBand(Band band) {
        connect();
        try {
            String str = "CREATE TABLE IF NOT EXISTS bands (" +
                    "band_id serial PRIMARY KEY NOT NULL," +
                    "band_name TEXT NOT NULL," +
                    "band_country TEXT NOT NULL," +
                    "band_info TEXT NOT NULL," +
                    "worker_id VARCHAR(12) NOT NULL, " +
                    "FOREIGN KEY(worker_id) " +
                    "REFERENCES workers(worker_id) " +
                    "ON DELETE CASCADE);";

            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO bands (band_id, band_name, band_country, band_info, worker_id)" +
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

    /*
       Fetches all the bands in the database as an ArrayList of bands.
    */
    public static ArrayList<Band> getAllBands() {
        connect();
        try {
            ArrayList<Band> listOfBands = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bands WHERE EXISTS (SELECT band_name) ORDER BY band_name ASC; ");
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

    /*
        Changes a band entry in the band table.
     */
    public static boolean changeBand(Band band) {
        connect();
        try {
            String str1 = "UPDATE bands SET band_name = '" + band.getBand_name() + "', " + "band_country = '" +
                    band.getBand_country_of_origin() + "', band_info = '" + band.getBand_info() + "', worker_id = '" + band.getContact_person_id() + "' WHERE band_id = '" + band.getBand_id() + "';";
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

    /*
        Deletes a band entry in the band table.
     */
    public static boolean deleteBand(String id) {
        connect();
        try {
            String str = "DELETE FROM bands WHERE band_id ='" + id + "';";
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

    /*
        Checks if a band member table exists, and if it does not - it creates a table for band members.
        After that, it inserts a band member into the table.
     */
    public static boolean addBandMember(BandMember bandMember) {
        connect();
        try {
          String str = "CREATE TABLE IF NOT EXISTS bandmembers (" +
                        "band_member_id serial PRIMARY KEY NOT NULL, " +
                        "band_member_name TEXT NOT NULL, " +
                        "band_member_info TEXT NOT NULL);";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO bandmembers (band_member_id, band_member_name, band_member_info) VALUES (DEFAULT, '" + bandMember.getBand_member_name() + "', '" + bandMember.getBand_member_info() + "');";
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

    /*
       Fetches all the band members in the database as an ArrayList of band members.
    */
    public static ArrayList<BandMember> getAllBandMembers() {
        connect();
        try {
            ArrayList<BandMember> bandMembers = new ArrayList<>();
            String str = "SELECT * FROM bandmembers WHERE EXISTS (SELECT band_member_id) ORDER BY band_member_name ASC;";
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

    /*
        Adds a band member association between a band and a band member.
     */
    public static boolean addBandMembersAssociation(BandMembersAssociation bandMembersAssociation) {
        connect();
        try {
            String str = "CREATE TABLE IF NOT EXISTS band_member_association (" +
                    "association_id serial PRIMARY KEY NOT NULL, " +
                    "band_id integer NOT NULL, " +
                    "member_id integer NOT NULL);";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String insert = "INSERT INTO band_member_association (association_id, band_id, member_id) VALUES (DEFAULT, '" +
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

    /*
        Changes a band members information in the database.
     */
    public static boolean changeBandMember(BandMember bandMember) {
        connect();
        try {
            String str = "UPDATE bandmembers SET band_member_name = '" + bandMember.getBand_member_name() + "', band_member_info = '" + bandMember.getBand_member_info() + "' WHERE band_member_id = '" + bandMember.getBand_member_id() + "';";
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

    /*
        Deletes a band member entry in the table.
     */
    public static boolean deleteBandMember(int id) {
        connect();
        try {
            String str = "DELETE FROM bandmembers WHERE band_member_id = '" + id + "';";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            String str1 = "DELETE FROM band_member_association WHERE member_id = '" + id + "';";
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

    /*
        Fetches an ArrayList of band members from a specific band.
     */
    public static ArrayList<BandMember> getAllBandMembersInBand(int band_id) {
        connect();
        try {
            ArrayList<BandMember> bandMembers = new ArrayList<>();
            String test = "SELECT bm.band_member_id, bm.band_member_name, bm.band_member_info FROM band_member_association bma INNER JOIN bandmembers bm ON bma.member_id=bm.band_member_id where bma.band_id='" + band_id + "';";
            String str = "SELECT * FROM Band_member_association WHERE band_id = '" + band_id + "';";
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

    /*
        Removes a band member association between a band and a band member.
     */
    public static boolean removeBandMemberFromBand(int band_id, int band_member_id) {
        connect();
        try {
            System.out.println(band_id);
            System.out.println(band_member_id);
            String sql = "DELETE FROM band_member_association WHERE band_id = " + band_id + " AND member_id = " + band_member_id + ";";
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

    /*
       Checks if a table for concerts exist, if it does not - it will create a table for concerts.
       After that it inserts the concert into the table.
    */
    public static boolean addConcert(Concert concert) {
        connect();
        try {
            String str = "CREATE TABLE IF NOT EXISTS concerts (" +
                    "concert_id serial PRIMARY KEY NOT NULL, " +
                    "concert_day TEXT NOT NULL, " +
                    "concert_time TEXT NOT NULL," +
                    "concert_scene TEXT NOT NULL," +
                    "concert_band_id integer NOT NULL, " +
                    "FOREIGN KEY (concert_band_id) " +
                    "REFERENCES bands(band_id) " +
                    "ON DELETE CASCADE);";
            PreparedStatement statement = connection.prepareStatement(str);
            statement.executeUpdate();
            statement.close();

            //TODO:Check if concert scene day and time is already booked

            String insert = "INSERT INTO concerts(concert_id, concert_day, concert_time, concert_scene, concert_band_id) VALUES(DEFAULT, '" + concert.getDay() + "', '" + concert.getTime() +
                    "', '" + concert.getScene() + "', '" + concert.getBand_id() + "');";
            statement = connection.prepareStatement(insert);
            statement.executeUpdate();
            statement.close();
            return true;

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        Gets a concert by a specific band. (Band is limited to only one concert each during the festival)
     */
    public static Concert getConcertByBand(String bandId) {
        connect();
        try {
            String str = "SELECT * FROM concerts WHERE concert_band_id = '" + bandId + "' ORDER BY concert_day, concert_time, concert_scene ASC;";
            PreparedStatement statement = connection.prepareStatement(str);
            ResultSet rs = statement.executeQuery();
            Concert concert = null;
            while(rs.next()) {
                concert = new Concert(rs.getString(1), Integer.parseInt(rs.getString(5)), rs.getString(2), rs.getString(3), rs.getString(4));
            }
            statement.close();
            disconnect();
            return concert;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        Changes a concert in the table.
     */
    public static boolean changeConcert(int concertToChangeId, Concert concert) {
        connect();
        try {
            String str = "UPDATE concerts SET concert_day = '" + concert.getDay() + "', concert_time = '" + concert.getTime() + "', concert_scene = '" + concert.getScene() + "', concert_band_id = '" + concert.getBand_id() + "' WHERE concert_id = '" + concertToChangeId + "';";
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

    /*
        Deletes a concert from the table.
     */
    public static boolean deleteConcert(String concert_id) {
        connect();
        try {
            String str = "DELETE FROM concerts WHERE concert_id = '" + concert_id + "';";
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

    /*
        Fetches an ArrayList of all the concerts at the festival, ordered by day, time and scene.
     */
    public static ArrayList<Concert> getAllConcerts() {
        connect();
        try {
            ArrayList<Concert> listOfConcerts = new ArrayList<>();
            //String str = "SELECT * FROM Concerts ORDER BY concert_day, concert_time, concert_scene ASC;";
            String tst = "SELECT * FROM concerts ORDER BY concert_day, concert_time, concert_scene ASC;";
            PreparedStatement statement = connection.prepareStatement(tst);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Concert concert = new Concert(rs.getString(1), Integer.parseInt(rs.getString(5)), rs.getString(2), rs.getString(3), rs.getString(4));
                listOfConcerts.add(concert);
            }
            statement.close();
            disconnect();
            return listOfConcerts;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
