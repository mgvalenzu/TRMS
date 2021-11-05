package com.revature.util;

import com.revature.logging.MyLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private static Connection conn = null; // Connection si a java.sql interface
        // this will be the Connection object we will return in our getConnection method.

    public static Connection getConnection() { // Will establish a connection to our database if it doesnt exists

        if(conn == null) {
            // establish new connection
            // need 3 credentails: url(endpoint, username, and password).
            String endpoint = "gabrielpostgres.cfwg11fgmasq.us-west-1.rds.amazonaws.com";
            String url = "jdbc:postgresql://" + endpoint + "/postgres";

            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASS");

            try{ // attempt to connect
                conn = DriverManager.getConnection(url, username, password);
                MyLogger.logger.info("Successfully Connected to JDBC");
            } catch (SQLException e) {
                MyLogger.logger.catching(e);
                e.printStackTrace();
            }
        }

        MyLogger.logger.info("Returning conn");
        return conn;
    }

//    Test -- Success.
//    public static void main(String[] args) {
//        Connection conn1 = getConnection();
//        System.out.println(conn1);
//    }

}
