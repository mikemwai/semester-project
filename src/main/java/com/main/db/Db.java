/*
 *  Copyright © Group 4 Authors 2021-2022.
 *
 *
 * This package contains the database interface and is the start
 * of all database interactions that this small project will occur
 *
 * All errors are first logged and then rethrown for easier error management
 *
 * The main writer @ Precious Boruett.
 */
package com.main.db;

// imports

import com.main.intf.Doctor;
import com.main.intf.Labs;
import com.main.intf.Patient;
import com.main.intf.Pharmacy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;


public class Db {
    //initialize the database for the first run


    // local static declarations to prevent pollution of the global space

    // @ Precious add values and any new databases
    // TODO: @ First meeting , we should do a database layout so that these can be filled.

    // @ Precious fix this to be correct statements, its throwing errors.
    // VARCHAR(100)
    //static String DoctorDbFormat = "CREATE TABLE \"Doctor\" ( \"name\" TEXT NOT NULL, \"Patient_ID\" INTEGER NOT NULL, \"profession\" TEXT NOT NULL, \"diagnosis\" TEXT NOT NULL, PRIMARY KEY(\"name\"),FOREIGN KEY (\"Patient_ID\") REFERENCES Patients (Patient_ID), Pharmacy (Patient_ID)";
    static String DoctorDbFormat = "CREATE TABLE \"Doctor\" ( \"name\" TEXT NOT NULL, \"Patient_ID\" INTEGER NOT NULL, \"profession\" TEXT NOT NULL, \"diagnosis\" TEXT NOT NULL, PRIMARY KEY(\"name\")";

    static String PatientsDbFormat = "CREATE TABLE \"Patient\" ( \"patient_ID\" INTEGER NOT NULL, \"name\" TEXT NOT NULL, \"dateOfBirth\" DATE NOT NULL, \"reportTime\" DATE NOT NULL, \"sickness\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"patient_ID\") FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy (assignedPersonnel), Labs (assignedPersonnel), Doctor(assignedPersonnel)  )";

    static String LabsDbFormat = "CREATE TABLE \"Labs\" ( \"name\" TEXT NOT NULL, \"specimen\" TEXT NOT NULL, \"results\" TEXT NOT NULL, \"date\" DATE NOT NULL, \"personnelId\" INTEGER NOT NULL, \"cost\" FLOAT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"personnelId\"), FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy(assignedPersonnel), Doctor(assignedPersonnel),)";

    static String PharmacyDbFormat = "CREATE TABLE \"Pharmacy\" ( \"name\" TEXT NOT NULL, \"ailment\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, \"patient_ID\" INTEGER NOT NULL, \"medicine\" INTEGER NOT NULL, \"price\" FLOAT NOT NULL, PRIMARY KEY(\"patient_ID\"), FOREIGN KEY (\"patient_ID\") REFERENCES Doctor(Patient_ID), Patients(Patient_ID)";

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
     * 2. Create tables (if not created)
     * */

    public Db() throws SQLException {
        // connect to the database
        this.initDatabase();
        // create the tables
        this.createTables();
    }

    private void initDatabase() throws SQLException {

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
            stmt.execute(PatientsDbFormat);
            stmt.execute(LabsDbFormat);
            stmt.execute(PharmacyDbFormat);
            stmt.execute(DoctorDbFormat);

        } catch (SQLException e) {
            // log the error.
            LOGGER.error(e);
            // rethrow the error.
            //throw e;
        }

        LOGGER.info("Finished Executing Table Initialization");
        // TODO: @Precious add more values tables if need be

    }

    /// Inserts a patient instance into the database
    public void insertPatient(Patient patient) throws SQLException {
        //"CREATE TABLE \"Patient\" ( \"patient_ID\" INTEGER NOT NULL, \"name\" TEXT NOT NULL, \"dateOfBirth\" DATE NOT NULL, \"reportTime\" DATE NOT NULL, \"sickness\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"patient_ID\") FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy (assignedPersonnel), Lab (assignedPersonnel), Doctor(assignedPersonnel)  )";

        String db_stmt = "INSERT INTO \"Patient\" VALUES(" + patient.PatientID + "," + patient.name + "," + patient.dateOfBirth + "," + patient.reportTime + "," + patient.sickness + "," + patient.assignedPersonnel + ");";


        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);

    }

    public void insertDoctor(Doctor doctor) throws SQLException {
        //"CREATE TABLE \"Doctor\" ( \"name\" TEXT NOT NULL, \"Patient_ID\" INTEGER NOT NULL, \"profession\" TEXT NOT NULL, \"diagnosis\" TEXT NOT NULL, PRIMARY KEY(\"name\"),FOREIGN KEY (\"Patient_ID\") REFERENCES Patients (Patient_ID), Pharmacy (Patient_ID)";
        String db_stmt = "INSERT INTO\"Doctor\" VALUES(" + doctor.name + "," + doctor.patientId + "," + doctor.profession + "," + doctor.diagnosis + ");";

        Statement stmt = conn.createStatement();
        stmt.execute(db_stmt);
    }


    public void insertPharmacy(Pharmacy pharmacy) throws SQLException {
        //"CREATE TABLE \"Pharmacy\" ( \"name\" TEXT NOT NULL, \"ailment\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, \"patient_ID\" INTEGER NOT NULL, \"medicine\" INTEGER NOT NULL, \"price\" FLOAT NOT NULL, PRIMARY KEY(\"patient_ID\"), FOREIGN KEY (\"patient_ID\") REFERENCES Doctor(Patient_ID), Patients(Patient_ID)";

        String db_stmt = "INSERT INTO \"Pharmacy\" (" + pharmacy.name + "," + pharmacy.ailment + "," + pharmacy.assignedPersonnel + "," + pharmacy.PatientID + "," + pharmacy.medicine + "," + pharmacy.price + ");";


        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);


    }

    public void insertLab(Labs labs) throws SQLException {
        //"CREATE TABLE \"Labs\" ( \"name\" TEXT NOT NULL, \"specimen\" TEXT NOT NULL, \"results\" TEXT NOT NULL, \"date\" DATE NOT NULL, \"personnelId\" INTEGER NOT NULL, \"cost\" FLOAT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"personnelId\"), FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy(assignedPersonnel), Doctor(assignedPersonnel),)";

        String db_stmt = "INSERT INTO \"Labs\" VALUES(" + labs.specimen + "," + labs.name + "," + labs.results + "," + labs.date + "," + labs.personnelId + ");";


        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }

    public void selectPatient(Patient patient) throws SQLException {
        ////"CREATE TABLE \"Patient\" ( \"patient_ID\" INTEGER NOT NULL, \"name\" TEXT NOT NULL, \"dateOfBirth\" DATE NOT NULL, \"reportTime\" DATE NOT NULL, \"sickness\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"patient_ID\") FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy (assignedPersonnel), Lab (assignedPersonnel), Doctor(assignedPersonnel)  )";
        String db_stmt = "SELECT * FROM PATIENTS WHERE name=" + patient.name + "|| dateOfBirth=" + patient.dateOfBirth + "|| patient_ID=" + patient.PatientID + "|| reportTime=" + patient.reportTime + "|| sickness=" + patient.sickness + "|| assignedPersonnel=" + patient.assignedPersonnel + ");";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }

    public void selectPharmacy(Pharmacy pharmacy) throws SQLException {
        //"CREATE TABLE \"Pharmacy\" ( \"name\" TEXT NOT NULL, \"ailment\" TEXT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, \"patientID\" INTEGER NOT NULL, \"medicine\" INTEGER NOT NULL, \"price\" FLOAT NOT NULL, PRIMARY KEY(\"patient_ID\"), FOREIGN KEY (\"patient_ID\") REFERENCES Doctor(Patient_ID), Patients(Patient_ID)";
        String db_stmt = "SELECT * FROM PHARMACY WHERE name=" + pharmacy.name + "|| ailment=" + pharmacy.ailment + "|| patient_D=" + pharmacy.PatientID + "|| medicine=" + pharmacy.medicine + "|| price=" + pharmacy.price + ");";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }

    public void selectDoctor(Doctor doctor) throws SQLException {
        //"CREATE TABLE \"Doctor\" ( \"name\" TEXT NOT NULL, \"Patient_ID\" INTEGER NOT NULL, \"profession\" TEXT NOT NULL, \"diagnosis\" TEXT NOT NULL, PRIMARY KEY(\"name\"),FOREIGN KEY (\"Patient_ID\") REFERENCES Patients (Patient_ID), Pharmacy (Patient_ID)";
        String db_stmt = "SELECT * FROM DOCTOR WHERE name=" + doctor.name + "|| profession=" + doctor.profession + "|| patient_ID=" + doctor.patientId + "|| diagnosis=" + doctor.diagnosis + ");";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }


    public void selectLabs(Labs labs) throws SQLException {
        //"CREATE TABLE \"Labs\" ( \"name\" TEXT NOT NULL, \"specimen\" TEXT NOT NULL, \"results\" TEXT NOT NULL, \"date\" DATE NOT NULL, \"personnelId\" INTEGER NOT NULL, \"cost\" FLOAT NOT NULL, \"assignedPersonnel\" TEXT NOT NULL, PRIMARY KEY(\"personnelId\"), FOREIGN KEY (\"assignedPersonnel\") REFERENCES Pharmacy(assignedPersonnel), Doctor(assignedPersonnel),)";
        String db_stmt = "SELECT * FROM LABS WHERE name=" + labs.name + "|| specimen=" + labs.specimen + "|| results=" + labs.results + "|| date=" + labs.date + "|| personnelId=" + labs.personnelId + "|| assignedPersonnel=" + labs.assignedPersonnel + "|| cost=" + labs.cost + ");";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }

    public void deleteDoctor(Doctor doctor) throws SQLException{

    String db_stmt = "DROP TABLE DOCTOR";
    Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
}

    public void deleteLabs(Labs labs) throws SQLException{

        String db_stmt = "DROP TABLE LABS";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }
    public void deletePharmacy(Pharmacy pharmacy) throws SQLException{

        String db_stmt = "DROP TABLE PHARMACY";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }
    public void deletePatient(Patient patient) throws SQLException{

        String db_stmt = "DROP TABLE PATIENT";
        Statement stmt = conn.createStatement();

        stmt.execute(db_stmt);
    }



}