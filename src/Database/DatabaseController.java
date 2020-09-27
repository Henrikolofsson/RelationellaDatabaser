package Database;

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
            System.out.println("Connection to database successful.");
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
            String pw = "{" + String.valueOf(adminPassword) +"}";
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
            String str = "UPDATE workers SET workersname = '" + worker.getName() + "'," + " workersaddress = '" + worker.getAddress() + "'";

           // PreparedStatement statement = connection.prepareStatement("UPDATE workers SET workersname = '" + worker.getName() + "'," +
            //        " workersaddress = '" + worker.getAddress() + "'")
        } catch(Exception e) {

        }
        return true;
    }
}
