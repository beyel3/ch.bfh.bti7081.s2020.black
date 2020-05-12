package ch.bfh.bti7081.s2020.black.persistence;
import ch.bfh.bti7081.s2020.black.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import ch.bfh.bti7081.s2020.black.model.Event;
import ch.bfh.bti7081.s2020.black.model.EventTemplate;
import ch.bfh.bti7081.s2020.black.model.Tag;

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

    public void saveEvent(Event event) throws SQLException {
        List<Coreuser> participants = event.getParticipants();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            //need to be looked at statement.executeQuery("INSERT INTO tbl_event VALUES (NULL,'"+event.getInfo()+"',"+event.isPublic()+","+event.getRating()+","+event.getStatus().toString()+","+event.getMaxParticipants()+","+event.getTemplateID()+","+event.getPictureID()+")");

            for (Coreuser c:participants){
                //REL from Event to Account
            }
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
        }
    }

    public Event getEventById(int id){
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_event WHERE eventID = "+id);

            List<Coreuser> participants = new ArrayList<Coreuser>(); //needs to be done
            ResultSet p = statement.executeQuery("SELECT * FROM tbl_participants WHERE eventID ="+rs.getInt("eventID"));
            while (p.next()){
                //REL from Event to Account
                //Coreuser cu = new Coreuser();
                //participants.add(cu);
            }
            Event event = new Event(rs.getInt("eventId"), getEventTemplateById(rs.getInt("eventTemplateID")), rs.getString("info"), rs.getBoolean("isPublic"),rs.getInt("maxParticipants"), rs.getInt("rating"), Status.valueOf(rs.getString("state")), rs.getInt("imageID"), participants);

            return event;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<Event> getEventsByTemplateId(int id) throws SQLException {
        ArrayList<Event> eventList = new ArrayList<Event>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_event WHERE eventTemplateID = "+id);

            while (rs.next()) {
                List<Coreuser> participants = new ArrayList<Coreuser>(); //needs to be done
                ResultSet p = statement.executeQuery("SELECT * FROM tbl_participants WHERE eventID ="+rs.getInt("eventID"));
                while (p.next()){
                    //REL from Event to Account
                    //Coreuser cu = new Coreuser();
                    //participants.add(cu);
                }
                Event event = new Event(rs.getInt("eventId"), getEventTemplateById(rs.getInt("eventTemplateID")), rs.getString("info"), rs.getBoolean("isPublic"), rs.getInt("maxParticipants"), rs.getInt("rating"), Status.valueOf(rs.getString("state")), rs.getInt("imageID"), participants);
                eventList.add(event);
            }
            return eventList;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            return null;
        }
    }
    public ArrayList<Event> getEventList() throws SQLException {
        ArrayList<Event> eventList = new ArrayList<Event>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_event");

            while (rs.next()) {
                List<Coreuser> participants = new ArrayList<Coreuser>(); //needs to be done
                ResultSet p = statement.executeQuery("SELECT * FROM tbl_participants WHERE eventID ="+rs.getInt("eventID"));
                while (p.next()){
                    //Coreuser cu = new Coreuser();
                    //participants.add(cu);
                }
                Event event = new Event(rs.getInt("eventId"), getEventTemplateById(rs.getInt("eventTemplateID")), rs.getString("info"), rs.getBoolean("isPublic"), rs.getInt("maxParticipants"), rs.getInt("rating"), Status.valueOf(rs.getString("state")), rs.getInt("imageID"), participants);
                eventList.add(event);
            }
            return eventList;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            return eventList;
        }
    }

    public EventTemplate saveEventTemplate(EventTemplate et) throws SQLException{
        ArrayList<Tag> tags = et.getTags();

        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeQuery("INSERT INTO tbl_eventTemplate VALUES (NULL, '"+et.getTitle()+"', '"+et.getDescription()+"', '"+et.getAvgRating()+"')");
            ResultSet id = statement.executeQuery("SELECT LAST_INSERT_ROWID()");
            et.setId(id.getInt("eventTemplateID"));

            for (Tag t:tags) {
                statement.executeQuery("INSERT INTO tbl_tagEventTemplateREL(tagID,eventTemplateID) SELECT "+t.getId()+", '"+et.getId()+"' WHERE NOT EXISTS(SELECT 1 FROM tbl_tagEventTemplateREL WHERE tagID = "+t.getId()+" AND eventTemplateID = "+et.getId()+");");
            }

            //return eventTemplate with updated id
            return et;
        }
        catch(SQLException e) {
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public EventTemplate getEventTemplateById(int id) throws SQLException {
        ArrayList<Event> events = getEventsByTemplateId(id);
        ArrayList<Tag> tags = getTagsByTemplateID(id);
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet templateResult = statement.executeQuery("SELECT * FROM tbl_eventTemplate WHERE eventTemplateID = "+id);
            EventTemplate eT = new EventTemplate(templateResult.getInt("eventTemplateId"), templateResult.getString("title"), templateResult.getString("description"), tags, events, templateResult.getDouble("rating"));

            return eT;
        }

        catch(SQLException e){
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<EventTemplate> getEventTemplateList() throws SQLException {
        ArrayList<EventTemplate> eventTemplateList = new ArrayList<EventTemplate>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            ResultSet rs = statement.executeQuery("SELECT * FROM tbl_eventTemplate");

            while (rs.next()) {
                ArrayList<Event> events = getEventsByTemplateId(rs.getInt("eventTemplateId"));
                ArrayList<Tag> tags = getTagsByTemplateID(rs.getInt("eventTemplateId"));
                EventTemplate eT = new EventTemplate(rs.getInt("eventTemplateId"), rs.getString("title"), rs.getString("description"), tags, events, rs.getDouble("rating"));
                eventTemplateList.add(eT);
            }
            return eventTemplateList;
        } catch (SQLException e) {
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public Tag saveTag(Tag t) throws SQLException{

        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeQuery("INSERT INTO tbl_tag VALUES (NULL, '"+t.getTagName()+"')");
            ResultSet id = statement.executeQuery("SELECT LAST_INSERT_ROWID()");
            t.setId(id.getInt("tagID"));

            return t;
        }
         catch(SQLException e){
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public Tag getTagById(int id) throws SQLException{
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet tr = statement.executeQuery("SELECT tagID, tag_name FROM tbl_tag WHERE tagID = "+id);
            Tag t = new Tag(tr.getInt(1), tr.getString(2));
            return t;
        }
        catch(SQLException e){
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<Tag> getTagsByTemplateID(int id) throws SQLException{
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet tagResult = statement.executeQuery("SELECT t.tagID, t.tag_name FROM tbl_tagEventTemplateREL as tr INNER JOIN tbl_tag as t on tr.tagID = t.tagID WHERE tr.eventTemplateID =" + id);
            while (tagResult.next()) {
                Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
                tags.add(t);
            }
            return tags;
        }
        catch(SQLException e){
            // query failed
            System.err.println(e);
            return null;
        }
    }

    public ArrayList<Tag> getTagList() throws SQLException{
        ArrayList<Tag> tags = new ArrayList<Tag>();
        try {
            Statement statement = this.connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet tagResult = statement.executeQuery("SELECT * FROM tbl_tag ");
            while (tagResult.next()) {
                Tag t = new Tag(tagResult.getInt(1), tagResult.getString(2));
                tags.add(t);
            }
            return tags;
        }
        catch(SQLException e){
            // query failed
            System.err.println(e);
            return null;
        }
    }
    //tags 3 cases,

	public Collection<Tag> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

}