package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Patient  implements FileIO<Patient>, DbInterface  {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final File PATIENTS_FILE = new File("./patients.csv");

    // String functions.
    public String name;
    public String sickness;
    public String assignedPersonnel;
    public int PatientID;
    // Date and time functions
    public  Date dateOfBirth;
    public  Date reportTime;

    public Patient(String name, String dateOfBirth, String sickness, String assignedPersonnel) throws ParseException {
        Random rand = new Random();

        this.PatientID = Math.abs(rand.nextInt());

        System.out.println("Patient " + name + " assigned ID " + this.PatientID);

        System.out.println("===========================================\n");

        this.sickness = sickness;

        this.name = name;

        this.reportTime = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        this.dateOfBirth = dateFormat.parse(dateOfBirth);

        this.assignedPersonnel = assignedPersonnel;

    }


    @Override
    public void writeToFile() throws IOException {

        // just assign, shuts up static analysis
        boolean $ = PATIENTS_FILE.createNewFile();

        FileWriter fw = new FileWriter(PATIENTS_FILE, true);

        BufferedWriter writer = new BufferedWriter(fw);
        // format date to something easy to parse
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        String dob = dateFormat.format(dateOfBirth);

        String report = dateFormat.format(reportTime);

        // Finally, write to the file.
        writer.write(String.format("%s,%s,%s,%s,%s,%s\n", name, dob, sickness, assignedPersonnel, report, PatientID));

        writer.flush();


    }

    @Override
    public List<Patient> readFromFile() throws FileNotFoundException {

        // read from our file
        BufferedReader reader = new BufferedReader(new FileReader(PATIENTS_FILE));
        /*
         * This does two things, it reads a file contents pointed by reader and
         * in this file it reads contents line by line. (Because the write function does
         * write line by line.
         * */
        ArrayList<Patient> patientList = new ArrayList<>();
        try {
            String line;

            // read data line by line updating patientsList
            while ((line = reader.readLine()) != null) {
                patientList.add(this.parseFromFile(line));
            }

        } catch (IOException | ParseException e) {
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
    public void writeToDb(Db db)
    {

    }

    @Override
    public void readFromDb(Db db)
    {

    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {

    }

    @Override
    public List<Patient> readFromFile(BufferedReader reader) {
        return null;
    }
}
