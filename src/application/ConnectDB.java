package application;

import java.sql.*;

public class ConnectDB implements Runnable{

    // we have two database connections which are main and local
    // main connection is used to access the main databse
    // while local connections is used to access the local database

    private String server;
    private String database;
    private String username;
    private String password;
    private String errorMessage;

    private Connection connection;
    private boolean running = true;

    // constructor that gets the server name, database name, username, and password.
    public ConnectDB(String server, String database, String username, String password) {

        this.server = server;
        this.database = database;
        this.username = username;
        this.password = password;

        try {

            // here ehh local database pa lang cinoconnectan
            connection = DriverManager.getConnection(buildURL(server,database), username, password);
            System.out.println("You are now connected to: " + server + " and can access " + database);

        }
        catch(SQLException SE) {

            // some code kung di makapaglogin

            System.out.println("Cannot Access Database...");
            errorMessage = "Cannot Access Database.";
        }

        // runs yung run method which then constantly checks if connected pa sa sql
        Thread connectionCheckerThread = new Thread(this);
        connectionCheckerThread.start();


    }

    // another constructor which is the same as the previous one but has boolean autocommit to set the autocommit.
    // calls the previous constructor.
    public ConnectDB(String server, String database, String un, String pass, boolean autocommit) throws SQLException{
        this(server, database, un, pass);
        connection.setAutoCommit(autocommit);
    }

    public void run() {
        while (running) {

            try {
                // Check connection status periodically
                if (connection == null || connection.isClosed()||!connection.isValid(5)) {
                    // Attempt to reconnect
                    try {
                        connection = DriverManager.getConnection(buildURL(server, database), username, password);
                        System.out.println("Reconnected to the database.");
                    }
                    catch (SQLException e) {
                        System.out.println("Cannot Connect.");
                        errorMessage = "Database connection was disabled.";
                    }
                }

            }
            catch (SQLException  e) {
                e.printStackTrace();
            }

        }
        System.out.println("Connection is closed by the user.");
    }

    // builds URL
    private String buildURL (String server, String database) {
        return String.format("jdbc:sqlserver://%s;databaseName=%s;encryption=true;trustServerCertificate=true", server, database);
    }

    // returns connection
    public Connection getConnection() {
        return connection;
    }

    // closes connection
    public void closeConnection() throws SQLException {
        running = false;
        connection.close();
    }

    public String returnDatabaseError () {
        return errorMessage;
    }




}
