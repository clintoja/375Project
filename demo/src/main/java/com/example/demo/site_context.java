package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class site_context {

    private final String databaseUrl;

    // The app uses schedule.db in its working directory.
    site_context(){
        this("jdbc:sqlite:schedule.db");
    }

    // Tests can supply a temporary SQLite file to keep their data separate.
    site_context(String databaseUrl) {
        this.databaseUrl = databaseUrl;
        CreateDB();
    }

    /**
     * Opens a connection to the configured scheduling database.
     *
     * @return an open connection that the caller must close
     * @throws SQLException if the connection cannot be opened
     */
    private Connection OpenConnection() throws SQLException {
        return DriverManager.getConnection(databaseUrl);
    }

    private void CreateDB(){
        try {
            Connection connection; 
            connection = OpenConnection();

            Statement create_tables_state = connection.createStatement();

            create_tables_state.execute("create table if not exists booking (booking_id INTEGER PRIMARY KEY, scheduler_id INT REFERENCES scheduler(scheduler_id), start_time DATETIME, end_time DATETIME); \r\n");
                create_tables_state.execute(                "create table if not exists scheduler (scheduler_id INTEGER PRIMARY KEY, start_date DATETIME, end_date DATETIME, minutes INT, meeting_id INT REFERENCES meeting(meeting_id));\r\n");
              create_tables_state.execute(                     "create table if not exists admin_user (admin_user_id INTEGER PRIMARY KEY, password VARCHAR(60), user_name VARCHAR(60));\r\n");
                     create_tables_state.execute(              "create table if not exists meeting (meeting_id INTEGER PRIMARY KEY, meeting_name VARCHAR(300), meeting_description VARCHAR(1000), admin_user_id INT REFERENCES admin_user(admin_user_id) );\r\n");
                      create_tables_state.execute(             "create table if not exists user (user_id INTEGER PRIMARY KEY,  meeting_id INT REFERENCES meeting(meeting_id), user_name VARCHAR(60));\r\n");
                      create_tables_state.execute(             "create table if not exists availability ( availability_id INTEGER PRIMARY KEY, begin_time DATETIME, end_time DATETIME , week_day VARCHAR(32), user_id INT REFERENCES user(user_id), repeating BIT ); \r\n");
                                
                                
            //AddSampleData();
            connection.close();
        } catch (SQLException s){

            s.printStackTrace();
        }
        



    }

    public void AddSampleData(){

        try {
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );


            


            Statement create_tables_state = connection.createStatement();

            create_tables_state.execute("insert into admin_user VALUES(1, 'admin', 'admin' ); " + "\r\n" );
             create_tables_state.execute(   "insert into meeting VALUES(1, 'my meeting', 'wdawdawdawd', 1); " + "\r\n" );
             create_tables_state.execute(   "insert into scheduler VALUES(1, '2026-09-01 00:00', '2026-09-08 00:00', 60, 1); " + "\r\n" );
              create_tables_state.execute(  "insert into user VALUES(1, 1, 'james'); " + "\r\n" );
               create_tables_state.execute( "insert into user VALUES(2, 1, 'james2'); " + "\r\n" ); 
               create_tables_state.execute( "insert into user VALUES(3, 1, 'james3'); " + "\r\n" );
              create_tables_state.execute(  "insert into availability VALUES(1, '2026-09-01 00:00', '2026-09-01 02:00', 'Tuesday', 1, 1 ); " + "\r\n" );
               create_tables_state.execute( "insert into availability VALUES(2, '2026-09-02 00:00', '2026-09-02 02:00', 'Wednesday', 1, 1); " + "\r\n" );
               create_tables_state.execute( "insert into availability VALUES(3, '2026-09-01 00:00', '2026-09-01 02:00', 'Tuesday', 2, 1); " + "\r\n" );
               create_tables_state.execute( "insert into availability VALUES(4, '2026-09-02 00:00', '2026-09-02 02:00', 'Wednesday', 2, 1); " + "\r\n" );
               create_tables_state.execute( "insert into availability VALUES(5, '2026-09-01 00:00', '2026-09-01 02:00', 'Tuesday', 3, 1); " + "\r\n" );
               create_tables_state.execute( "insert into availability VALUES(6, '2026-09-02 00:00', '2026-09-02 02:00', 'Wednesday', 3, 1 ); " + "\r\n" );
                               create_tables_state.execute( "insert into booking VALUES(1, 1, '2026-09-02 00:00', '2026-09-02 02:00'); " + "\r\n" );

            connection.close();
                                

            


        } catch (SQLException s){

            s.printStackTrace();
        }

    }
    public  ArrayList<user> GetUsersFromMeetingId(int meeting_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet users_from_meetingid;
            users_from_meetingid = user_model_statement.executeQuery("select * from user where meeting_id = " + meeting_id + ";");
            ArrayList<user> tmp = new  ArrayList<>();
            while (users_from_meetingid.next()){
                user u = new user(users_from_meetingid.getInt("user_id"),users_from_meetingid.getInt("meeting_id"), users_from_meetingid.getString("user_name") );

                tmp.add(u);


            }
            users_from_meetingid.close();
            connection.close();
            return tmp;

        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }

    public  ArrayList<availability> GetAvailabilityFromUserId(int user_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet availability_from_userid;
            availability_from_userid = user_model_statement.executeQuery("select * from availability where user_id = " + user_id + ";");
            ArrayList<availability> tmp = new  ArrayList<>();
            while (availability_from_userid.next()){


                LocalDateTime start,end;
                DateTimeFormatter dtf;
                dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(availability_from_userid.getString("begin_time"),dtf);
                end = LocalDateTime.parse(availability_from_userid.getString("end_time"),dtf);
                availability a = new availability(availability_from_userid.getInt("availability_id"), availability_from_userid.getInt("user_id"),start,end, availability_from_userid.getString("week_day"), availability_from_userid.getInt("repeating") );

                tmp.add(a);


            }

            availability_from_userid.close();
            connection.close();
            return tmp;


        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }


    public  ArrayList<booking> GetBookingFromSchedule(int scheduler_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet booking_from_meeting;
            booking_from_meeting = user_model_statement.executeQuery("select * from booking where scheduler_id = " + scheduler_id + ";");
            ArrayList<booking> tmp = new  ArrayList<>();
            while (booking_from_meeting.next()){


                LocalDateTime start,end;
                DateTimeFormatter dtf;
                dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(booking_from_meeting.getString("start_time"),dtf);
                end = LocalDateTime.parse(booking_from_meeting.getString("end_time"),dtf);
                booking a = new booking(booking_from_meeting.getInt("booking_id"), booking_from_meeting.getInt("scheduler_id"),start,end );

                tmp.add(a);


            }

            booking_from_meeting.close();
            connection.close();
            return tmp;


        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }

    public  ArrayList<scheduler> GetSchedulerFromMeeting(int user_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet scheduler_from_meeting;
            scheduler_from_meeting = user_model_statement.executeQuery("select * from scheduler where meeting_id = " + user_id + ";");
            ArrayList<scheduler> tmp = new  ArrayList<>();
            while (scheduler_from_meeting.next()){


                LocalDateTime start,end;
                DateTimeFormatter dtf;
                dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(scheduler_from_meeting.getString("start_date"),dtf);
                end = LocalDateTime.parse(scheduler_from_meeting.getString("end_date"),dtf);
                scheduler a = new scheduler(scheduler_from_meeting.getInt("scheduler_id"),start,end, scheduler_from_meeting.getInt("minutes"), scheduler_from_meeting.getInt("meeting_id"));

                tmp.add(a);


            }

            scheduler_from_meeting.close();
            connection.close();
            return tmp;


        }catch (SQLException s){
            s.printStackTrace();
            return new ArrayList<>();
        }

    }
    public meeting GetMeeting(int meeting_id){


        try{
            Connection connection; 
            connection = DriverManager.getConnection("jdbc:sqlite:schedule.db" );

            Statement user_model_statement = connection.createStatement();
            ResultSet meeting_from_meeting_id;
            meeting_from_meeting_id = user_model_statement.executeQuery("select * from meeting where meeting_id = " + meeting_id + ";");


            if (meeting_from_meeting_id.next()){
             meeting tmp = new meeting(meeting_from_meeting_id.getInt("meeting_id"), meeting_from_meeting_id.getInt("admin_user_id"), meeting_from_meeting_id.getString("meeting_name"), meeting_from_meeting_id.getString("meeting_description"));
             connection.close();
             meeting_from_meeting_id.close();
                return  tmp;
            }

             meeting_from_meeting_id.close();
            connection.close();
           

            return null;

        }catch (SQLException s){
            
            s.printStackTrace();
            return null;
        }

    }


    //fill this in (Jaydon)

    /**
     * Checks whether an administrator with the given ID exists.
     *
     * @param admin_user_id the administrator ID to look up
     * @return 1 if the administrator exists, or 0 if the ID is missing or the query fails
     */
    public int ValidateAdminUser(int admin_user_id){
        String sql = "SELECT 1 FROM admin_user WHERE admin_user_id = " + admin_user_id + ";";

        try (Connection connection = OpenConnection();
            Statement statement = connection.createStatement();
            ResultSet rows = statement.executeQuery(sql)) {
                return rows.next() ? 1 : 0;
        } catch (SQLException s) {

            s.printStackTrace();
            return 0;
        }
    }

    
    /**
     * Looks up the administrator assigned to a meeting.
     *
     * @param meet_id the meeting ID
     * @return the administrator's ID and username as an object, or {@code null} if the lookup finds no match or fails
     */
    public admin_user GetAdminUserFromMeetingId(int meet_id){
        String sql = """
            SELECT a.admin_user_id, a.user_name
            FROM meeting AS m
            JOIN admin_user AS a ON a.admin_user_id = m.admin_user_id
            WHERE m.meeting_id = ?
            """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, meet_id);

            try (ResultSet rows = statement.executeQuery()) {
                if (rows.next()) {
                    return new admin_user(
                            rows.getInt("admin_user_id"),
                            rows.getString("user_name")
                    );
                }
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }

        return null;
    }

    private static final Pbkdf2PasswordEncoder PASSWORD_ENCODER =
        Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();


    /**
     * Encodes a password with PBKDF2 and a random salt.
     *
     * @param password the raw password
     * @return the encoded password for storage
     */
    private String HashPassword(String password) {
        return PASSWORD_ENCODER.encode(password);
    } 

    /**
     * Creates an administrator and stores the encoded password.
     *
     * @param user_name the administrator's username
     * @param pass_word the raw password to encode
     * @return the generated administrator ID, or 0 if the insert fails
     */
    public int CreateAdminUser(String user_name, String pass_word){
        String sql = """
                INSERT INTO admin_user (user_name, password)
                VALUES (?, ?)
                RETURNING admin_user_id
                """;

        String passwordHash = HashPassword(pass_word);

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user_name);
            statement.setString(2, passwordHash);
            
            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? rows.getInt("admin_user_id") : 0;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }

 

    /**
     * Saves a booking for a scheduler.
     *
     * @param book the booking with its ID, scheduler ID, and non-null start and end times
     * @return the saved booking ID, or 0 if the insert fails
     */
    public int CreateBooking(booking book) {
        String sql = """
                INSERT INTO booking (booking_id, scheduler_id, start_time, end_time)
                VALUES (?, ?, ?, ?)
                RETURNING booking_id
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book.GetBookingId());
            statement.setInt(2, book.GetSchedulerId());
            statement.setString(3, book.GetStartTime().toString());
            statement.setString(4, book.GetEndTime().toString());

            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? rows.getInt("booking_id") : 0;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }

    /**
     * Adds a participant to a meeting.
     *
     * @param u the participant with a user ID and a meeting ID
     * @return the saved user ID, or 0 if the insert fails
     */
    public int CreateUser(user u) {
        String sql = """
                INSERT INTO user (user_id, meeting_id)
                VALUES (?, ?)
                RETURNING user_id
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, u.GetUserId());
            statement.setInt(2, u.GetMeetingId());

            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? rows.getInt("user_id") : 0;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }










    /**
     * Saves a meeting linked to an administrator.
     *
     * @param meet the meeting with its ID, name, description, and administrator ID
     * @return the saved meeting ID, or 0 if the insert fails
     */
    public int CreateMeeting(meeting meet) {
        String sql = """
                INSERT INTO meeting (meeting_name, meeting_description, admin_user_id)
                VALUES (?, ?, ?)
                RETURNING meeting_id
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            //statement.setInt(1, meet.GetMeetingId());
            statement.setString(1, meet.GetMeetingName());
            statement.setString(2, meet.GetMeetingDescription());
            statement.setInt(3, meet.GetAdminUserId());

            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? rows.getInt("meeting_id") : 0;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }

    /**
     * Creates a scheduler for a meeting with the start and end dates unset.
     *
     * @param meeting_id the meeting ID to associate with the scheduler
     * @return the generated scheduler ID, or 0 if the insert fails
     */
    public int CreateScheduler(int meeting_id) {
        String sql = """
                INSERT INTO scheduler (meeting_id)
                VALUES (?)
                RETURNING scheduler_id
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, meeting_id);

            try (ResultSet rows = statement.executeQuery()) {
                if (rows.next()) {
                    return rows.getInt("scheduler_id");
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return 0;
    }

    /**
     * Saves a user's availability window and its recurrence information.
     *
     * @param av the entry with its ID, user ID, non-null start and end times, weekday, and repeat flag
     * @return the saved availability ID, or 0 if the insert fails
     */
    public int CreateAvailability(availability av) {
        String sql = """
                INSERT INTO availability
                    (availability_id, user_id, begin_time, end_time, week_day, repeating)
                VALUES (?, ?, ?, ?, ?, ?)
                RETURNING availability_id
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, av.GetAvailabilityId());
            statement.setInt(2, av.GetUserId());
            statement.setString(3, av.GetStartTime().toString());
            statement.setString(4, av.GetEndTime().toString());
            statement.setString(5, av.GetWeekDay());
            statement.setInt(6, av.GetRepeating());

            try (ResultSet rows = statement.executeQuery()) {
                return rows.next() ? rows.getInt("availability_id") : 0;
            }
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }


    /**
     * Calculates the highest meeting ID plus one, or 1 if the table is empty.
     * The returned ID is a candidate and remains available to other callers.
     *
     * @return the candidate meeting ID, or 0 if the query fails
     */

    public int GetNewMeetingId() {
        // TODO: Migrate meeting_id to INTEGER PRIMARY KEY, then let SQLite
        // assign the ID in CreateMeeting() and remove this method.
        String sql = """
                SELECT COALESCE(MAX(meeting_id), 0) + 1 AS next_id
                FROM meeting
                """;

        try (Connection connection = OpenConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet row = statement.executeQuery()) {
            return row.next() ? row.getInt("next_id") : 0;
        } catch (SQLException error) {
            error.printStackTrace();
            return 0;
        }
    }
}
