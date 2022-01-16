package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

public class labs implements FileIO<labs>, DbInterface{

    private static final Logger LOGGER=LogManager.getLogger();
    String name;
    String specimen;
    String results;
    public String assignedPersonnel;
    int personnelId;
    int patientId;
    Date processingTime;
    int cost;

    public labs(String name, String specimen, String assignedPersonnel, int patientId, int personnelId, int cost, String results){
        Random rand =new Random();
        this.personnelId=Math.abs(rand.nextInt());
        System.out.println("personnel"+name+"assigned Id"+this.personnelId);

        this.patientId=Math.abs(rand.nextInt());
        System.out.println("patient"+name+"assigned Id"+this.patientId);

        this.name=name;
        this.specimen=specimen;
        this.results=results;
        this.cost=cost;

        this.processingTime= new Date();

        SimpleDateFormat dateFormat=new SimpleDateFormat( "dd-MM-yyyy");
        this.assignedPersonnel=assignedPersonnel;

    }
    public void writeToFile(BufferedWriter writer){
        try{
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

            String results=dateFormat.format(processingTime);
            writer.write(String.format(name,specimen,results,assignedPersonnel,processingTime,patientId,processingTime,cost));
            writer.flush();
        }catch(IOException e){
            LOGGER.error(e);

            System.exit(1);
        }
    }

    @Override
    public List<labs> readFromFile(BufferedReader reader) {
        return null;
    }

    @Override
    public void writeToDb(Db db) {

    }

    @Override
    public void readFromDb(Db db) {

    }
}

