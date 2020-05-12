package ch.bfh.bti7081.s2020.black.persistence;
import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Persistence {

    Connection connection = null;

    public Persistence() throws ClassNotFoundException
    {
        // load the sqlite-JDBC driver using the current class loader
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new Error("Cannot find JDBC Driver", e);
        }
    }

    public void openConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/java/ch/bfh/bti7081/s2020/black/persistence/burnOUTevents_db.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            if(this.connection != null)
                this.connection.close();
            }
        catch(SQLException e) {
            // connection close failed.
            System.err.println(e);
        }
    }
    public ArrayList<Event> getEvents() throws SQLException {
        ArrayList<Event> eventList = new ArrayList<Event>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_event");

            while (rs.next()) {
//                Event event = new Event(rs.getInt("eventId"), rs.getString("info"), rs.getBoolean("isPrivate"), rs.getDouble("rating"), (Status) rs.getString("state"), rs.getInt("maxParticipants"), rs.getInt("eventTemplateID"), rs.getString("imagePath"));
//                eventList.add(event);
            }
            return eventList;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            return eventList;
        }
    }

    public Event getEvent(int id){
        return null;
    }

    public void saveEvent(Event event) throws SQLException {
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeQuery("INSERT INTO tbl_event VALUES ()");
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
        }
    }
    public void saveEventTemplate(EventTemplate et){

    }
    public EventTemplate getEventTemplate(int id){
        return null;
    }

    public ArrayList<EventTemplate> getEventTemplateList(){
        return null;
    }
    //tags 3 cases,

}