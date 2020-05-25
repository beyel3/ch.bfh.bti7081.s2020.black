package ch.bfh.bti7081.s2020.black.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Patient;
import ch.bfh.bti7081.s2020.black.model.Tag;

public class Persistence {

    private Connection connection = null;

    public Persistence() throws ClassNotFoundException
    {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new Error("Cannot find JDBC Driver", e);
        }
        openConnection();
    }

    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/ch/bfh/bti7081/s2020/black/persistence/burnOUTevents_db.db");
        } catch (SQLException e) {
            // open database failed
            System.err.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            if(this.connection != null)
                this.connection.close();
            }
        catch(SQLException e) {
            // connection close failed
            System.err.println(e);
        }
    }
    
    
    
    public ResultSet executeQuery(String query) {
    	
    	 try {
    		 
             Statement statement = this.connection.createStatement();
             statement.setQueryTimeout(30);
             return statement.executeQuery(query);
         }
         catch(SQLException e) {
             // query failed
             System.err.println(e);
             return null;
         }
    }

    public void executeUpdate(String query) {

        try {

            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate(query);
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
        }
    }
}