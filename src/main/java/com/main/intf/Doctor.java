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


public class Doctor implements FileIO<Doctor>, DbInterface {

    public static final File DOCTORS_FILE = new File("./doctors.csv");
    private static final Logger LOGGER = LogManager.getLogger();

    public  String name;

    public String profession;

    public int patientId;

    public String diagnosis;


    public Doctor(String profession,int patientId, String diagnosis) {

        this.profession = profession;

        this.name = assignRandomDoctor();

        this.patientId = patientId;

        this.diagnosis=diagnosis;

    }



    @Override
    public void writeToFile() throws IOException {

        boolean $ = DOCTORS_FILE.createNewFile();

        FileWriter fw = new FileWriter(DOCTORS_FILE, true);

        BufferedWriter writer = new BufferedWriter(fw);



        writer.write(String.format("%s,%s,%s,%s\n",patientId,diagnosis,name,profession));

        writer.flush();


    }

    @Override
    public List<Doctor> readFromFile() throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader(DOCTORS_FILE));

        ArrayList<Doctor> doctorList = new ArrayList<>();
        try {
            String line;

            while ((line = reader.readLine()) != null) {
                doctorList.add(this.parseFromFile(line));
            }
        } catch (IOException | ParseException e) {
            LOGGER.error(e);
            System.exit(1);
        }
        return doctorList;

    }

    Doctor parseFromFile(String data) throws ParseException {
        String[] fields = data.split(",");
        return new Doctor(fields[0],
                Integer.parseInt(fields[1]),
                fields[2]);
    }
    // Assign a random doctor to this patient.
    public String assignRandomDoctor(){
        String[] doctor = {"Juraj Toril", "Ziyaettin Darko", "Tu Nerthus", "Shayne Shula", "Cailyn Zdenko"};
        Random random = new Random();

        int next = random.nextInt(doctor.length - 1);

        return doctor[next];
    }

    @Override
    public void writeToDb(Db db) {
    }

    @Override
    public void readFromDb(Db db) {
    }


}
