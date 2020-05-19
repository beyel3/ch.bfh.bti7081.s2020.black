package ch.bfh.bti7081.s2020.black.persistence;

import java.sql.*;

public class QueryHelpers {
    private static Connection connection = null;

    private QueryHelpers(){}

    private static void openConnection(){
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new Error("Cannot find JDBC Driver", e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/ch/bfh/bti7081/s2020/black/persistence/burnOUTevents_db.db");
        } catch (SQLException e) {
            // open database failed
            System.err.println(e.getMessage());
        }
    }
    private static void closeConnection(){
        try {
            if(connection != null)
                connection.close();
        }
        catch(SQLException e) {
            // connection close failed
            System.err.println(e);
        }
    }
    public static ResultSet executeQuery(String query) {
        openConnection();
        ResultSet resultSet;
        try {

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            resultSet = statement.executeQuery(query);
            closeConnection();
            return resultSet;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            closeConnection();
            return null;
        }
    }
}
