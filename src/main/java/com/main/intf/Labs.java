package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Labs implements FileIO<Labs>, DbInterface{

    private static final Logger LOGGER=LogManager.getLogger();

    public static final File LABS_FILE =   new File ("./labs.csv");

    String name;
    String specimen;
    String results;
    public String assignedPersonnel;
    int personnelId;
    Date processingTime;
    int cost;

    public Labs(String name, String specimen, String assignedPersonnel, int cost, String results){
        Random rand =new Random();

        this.personnelId=Math.abs(rand.nextInt());
        this.name=name;
        this.specimen=specimen;
        this.results=results;
        this.cost=cost;
        this.assignedPersonnel=assignedPersonnel;

        System.out.println("personnel"+name+"assigned Id"+this.personnelId);

    }
    @Override
    public void writeToFile() throws IOException{


            boolean $ = LABS_FILE.createNewFile();

            FileWriter fw = new FileWriter(LABS_FILE, true);

            BufferedWriter writer = new BufferedWriter(fw);

            SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");

            String results=dateFormat.format(processingTime);

            writer.write(String.format("%s,%s,%s,%s,%s",name,specimen,results,assignedPersonnel,cost));

            writer.flush();


    }

    @Override
    public List<Labs> readFromFile()throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(LABS_FILE));

        ArrayList<Labs> labsList = new ArrayList<>();
        try {
            String line;

            // read data line by line updating patientsList
            while ((line = reader.readLine()) != null) {
                labsList.add(this.parseFromFile(line));
            }

        } catch (IOException e) {
            // Exit process.
            LOGGER.error(e);

            System.exit(1);
        }
        return labsList;
    }
    private Labs parseFromFile(String data){
        // split into string
        String[] fields = data.split(",");
        return new Labs(
               fields[0],
               fields[1],
               fields[2],
               Integer.parseInt(fields[3]),
               fields[4]
        );

    }

    @Override
    public void writeToDb(Db db) {

    }

    @Override
    public void readFromDb(Db db) {

    }
}

