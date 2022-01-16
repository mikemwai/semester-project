package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Doctor implements FileIO<Doctor>, DbInterface{
    private static final Logger LOGGER = LogManager.getLogger();

    String name;
    String profession;

    String Doctor_Id;
    Date reportTime;
    public Doctor(String name, String profession, int Doctor_Id) {
        Random rand = new Random();
        this.Doctor_Id = String.valueOf(Math.abs(rand.nextInt()));
        System.out.println("Doctor" +name+"assigned ID"+this.Doctor_Id);
        System.out.println("========================================");
         this.profession = profession;
         this.name = name;
        this.reportTime = new Date();


    }



    public Doctor(String name, String profession, String analysis, String Doctor_id,String reportTime) {
    }

    @Override
    public void writeToFile(BufferedWriter writer){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
            String report = dateFormat.format(reportTime);

            writer.write(String.format(name, profession, reportTime,Doctor_Id));
            writer.flush();

        } catch (IOException e){
            LOGGER.error(e);
            System.exit(1);
        }
    }

    @Override
    public List<Doctor> readFromFile(BufferedReader reader){
        ArrayList<Doctor> doctorList = new ArrayList<>();
        try{
            String line;
            while((line = reader.readLine()) != null)
            {doctorList.add(this.parseFromFile(line));
        }
    }catch (IOException| ParseException e){
            LOGGER.error(e);
            System.exit(1);
        }
        return doctorList;

}

Doctor parseFromFile(String data) throws ParseException{
    String[]fields = data.split(",");
    return new Doctor(fields[0],
            fields[1],
            fields[2],
            fields[3],
            fields[4]);
    }
    @Override
    public void writeToDb(Db db) {
    }

    @Override
    public void readFromDb(Db db){
    }


}
