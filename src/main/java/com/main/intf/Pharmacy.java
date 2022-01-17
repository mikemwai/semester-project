package com.main.intf;

import com.main.db.Db;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Pharmacy implements FileIO<Pharmacy>, DbInterface
{

    private static final Logger LOGGER = LogManager.getLogger();

    public  static final File PHARMACY_FILE = new File("./pharmacy.csv");

    // String functions.
    String name;
    String ailment;
    public  String assignedPersonnel;
    int PatientID;
    String medicine;
    String price;

    public Pharmacy(String name, String ailment, String assignedPersonnel, int PatientID, String medicine,String price)
    {

        this.ailment = ailment;

        this.name = name;

        this.PatientID= PatientID;

        this.assignedPersonnel = assignedPersonnel;

        this.medicine=medicine;

        this.price=price;


    }


    @Override
    public void writeToFile() throws IOException
    {

            boolean $ = PHARMACY_FILE.createNewFile();

            FileWriter fw = new FileWriter(PHARMACY_FILE, true);

            BufferedWriter writer = new BufferedWriter(fw);

            // Finally, write to the file.
            writer.write(String.format("%s,%s,%s,%s,%s,%s\n", name, ailment, assignedPersonnel,PatientID,medicine,price));
            writer.flush();


    }

    @Override
    public List<Pharmacy> readFromFile() throws FileNotFoundException
    {

        BufferedReader reader = new BufferedReader(new FileReader(PHARMACY_FILE));
        /*
         * This does two things, it reads a file contents pointed by reader and
         * in this file it reads contents line by line. (Because the write function does
         * write line by line.)
         * */
        ArrayList<Pharmacy> pharmacyList = new ArrayList<>();
        try {
            String line;

            // read data line by line updating pharmacyList
            while ((line=reader.readLine()) != null)
            {
                pharmacyList.add(this.parseFromFile(line));
            }

        } catch (IOException | ParseException e)
        {
            // Exit process.
            LOGGER.error(e);

            System.exit(1);
        }
        return pharmacyList;
    }

    /**
     * Convert a string to a pharmacy class
     * The fields in the string are expected to be separated
     * using comas, otherwise this will panic.
     */
    Pharmacy parseFromFile(String data) throws ParseException
    {
        String[] fields = data.split(",");

        return new Pharmacy(fields[0],
                fields[1],
                fields[2],
                Integer.parseInt(fields[3]),
                fields[4],
                fields[5]);


    }

    @Override
    public void writeToDb(Db db)
    {

    }

    @Override
    public void readFromDb(Db db)
    {

    }
}
