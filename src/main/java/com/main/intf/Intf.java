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

/*
 * An interface implementing Input output.
 * A class extending this interface implements File Input/Output
 *
 * The class is generic because the values returned by readFromFile vary
 * depending on the class that implements them
 */
interface FileIO<T> {

    /**
     * A class extending this interface is expected to write csv values
     * to argument @writer
     * <p>
     * The method should also deal with formatting of the values in an
     * appropriate way and whatever quirks may come up.(E.g comas in names)
     *
     * @param writer: The location we are writing to
     * @throws IOException
     */
    void writeToFile(BufferedWriter writer) throws IOException;


    /**
     * A class extending this interface is expected to read csv values
     * from  argument @reader
     * <p>
     * This method should also deal with any errors that may arise from
     * IO operations, e.g. opening of binary files, or malformed CSV's
     *
     * @param reader: The file we are reading from
     */
    List<T> readFromFile(BufferedReader reader);
}

interface DbInterface {
    void writeToDb(Db db);

    void readFromDb(Db db);
}

class Doctors {
    String Name;
    int Doctor_Id;
    String Profession;
}

class Appointments {

}
class Labs{

}





class Pharmacy {

}

class Office {

}