package com.main.intf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

import com.main.db.Db;

/// An interface implementing Input output.
/// A class extending this interface implements File Input/Output
interface FileIO{
    /// A class extending this interface is expected to write csv values
    // to argument @writer
    public void writeToFile(BufferedWriter writer) throws IOException;

    /// A class extending this interface is expected to read csv values
    // from  argument @reader
    public String readFromFile(BufferedReader reader);
}
interface DbInterface{
    public void writeToDb(Db db);

    public void readFromDb(Db db);
}
class Patients implements FileIO,DbInterface
{
    String name;
    Date DateOfBirth;
    String Sickness;
    String AssignedPersonnel;
    int PatientID;

    public Patients(String name, String DateOfBirth,String Sickness, String AssignmentPersonnel,String PatientID){

    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(name+",");
    }

    @Override
    public String readFromFile(BufferedReader reader) {
        return null;
    }

    @Override
    public void writeToDb(Db db) {
        // code that takes class patients and writes it to a database

    }

    @Override
    public void readFromDb(Db db) {

    }
}
class Doctors
{
    String Name;
    int Doctor_Id;
    String Profession;
}
class Appointments
{

}
class Labs
{

}
class Pharmacy
{

}
class Office
{

}