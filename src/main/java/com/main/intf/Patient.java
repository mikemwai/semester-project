package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class Patient implements FileIO<Patient>, DbInterface {

    private static final Logger LOGGER = LogManager.getLogger();
    // String functions.
    String name;
    String sickness;
    String assignedPersonnel;
    // Date and time functions
    Date dateOfBirth;
    Date reportTime;
    int PatientID;

    public Patient(String name, String dateOfBirth, String sickness, String assignedPersonnel) throws ParseException {
        // Default constructor randomly initializes  patientID
        // TODO: This will be changed once we have a cool way to create them
        Random rand = new Random();

        this.PatientID = rand.nextInt();

        System.out.println("Patient "+name+" assigned ID "+ this.PatientID);

        this.sickness = sickness;

        this.reportTime = new  Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        this.dateOfBirth = dateFormat.parse(dateOfBirth);

        this.assignedPersonnel = assignedPersonnel;

    }


    @Override
    public void writeToFile(BufferedWriter writer) {
        try {
            writer.write(String.format("%s,%s,%s,%s,%s %s\n", name, dateOfBirth, sickness, assignedPersonnel, reportTime, PatientID));
        } catch (IOException e) {
            // Exit process.
            LOGGER.error(e);

            System.exit(1);
        }
    }

    @Override
    public List<Patient> readFromFile(BufferedReader reader) {
        /*
         * This does two things, it reads a file contents pointed by reader and
         * in this file it reads contents line by line. (Because the write function does
         * write line by line.
         * */
        ArrayList<Patient> patientList = new ArrayList<>();
        try {
            String line;

            // read data line by line updating patientsList
            while ((line = reader.readLine()) != null)
            {
                patientList.add(this.parseFromFile(line));
            }

        } catch (IOException | ParseException e)
        {
            // Exit process.
            LOGGER.error(e);

            System.exit(1);
        }
        return patientList;
    }

    /**
     * Convert a string to a patients class
     * The fields in the string are expected to be separated
     * using comas, otherwise this will panic.
     */
    Patient parseFromFile(String data) throws ParseException {
        String[] fields = data.split(",");

        return new Patient(fields[0],
                fields[1],
                fields[2],
                fields[3]);

    }

    @Override
    public void writeToDb(Db db) {

    }

    @Override
    public void readFromDb(Db db) {

    }
}