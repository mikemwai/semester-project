/*
 *  Copyright © Group 4 Authors 2021-2022.
 *
 *
 * This package contains the database interface and is the start
 * of all database interactions that this small project will occur
 *
 * All errors are first logged and then rethrown for easier error management
 *
 * The main writer @ Precious Bourret.
 */
package com.main.db;

// imports

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class Db {
    //  Initialize the database for the first run


    // local static declarations to prevent pollution of the global space

    // @ Precious add values and any new databases
    // TODO: @ First meeting , we should do a database layout so that these can be filled.

    // @ Precious fix this to be correct statements, its throwing errors.
    static String MainHospitalDbFormat = "CREATE TABLE IF NOT EXISTS Hospital";

    static String PatientsDbFormat = "CREATE TABLE IF NOT EXISTS Patients ()";

    // connection to the database.
    static Connection conn;

    // the file path this database points to
    // should be a local instance
    static String filePath = "jdbc:sqlite:./db/main.db";

    private static final Logger LOGGER = LogManager.getLogger();

    /*
    * The class called when calling a new instance.
    * the following things are executed in order
    *
    * 1. Connects to a database instance(configured in  the private String keyword
    *
    * 2. Creates tables (if not created)
    * */

    public Db() throws SQLException {
        // connect to the database
        this.initDatabase();
        // create the tables
        this.createTables();
    }

    private  void initDatabase() throws SQLException {

        try {
            // connect to the database
            conn = DriverManager.getConnection(filePath);

        } catch (SQLException e) {
            // log the error.
            LOGGER.error(e);
            // rethrow the error.
            throw e;
        }
    }
    /// This function creates the Database tables and should
    /// be called at least once in the duration of the application.
    private void createTables() throws SQLException {

        LOGGER.info("Executing database initialization");

        Statement stmt = conn.createStatement();


        // execute the database statements.

        try {
            stmt.execute(MainHospitalDbFormat);

            stmt.execute(PatientsDbFormat);
        } catch (SQLException e){
            // log the error.
            LOGGER.error(e);
            // rethrow the error.
            //throw e;
        }


        LOGGER.info("Finished Executing Table Initialization");
        // TODO: @Precious add more values tables if need be

    }
    public Db getInstance(){
        return this;
    }


}
