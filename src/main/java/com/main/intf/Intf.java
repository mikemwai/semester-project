package com.main.intf;
import com.main.db.Db;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
     * to a file
     * <p>
     * The method should also deal with formatting of the values in an
     * appropriate way and whatever quirks may come up.(E.g comas in names)
     *: The location we are writing to
     *
     * @throws IOException: An exception occuring when something goes
     * wrong in the IO stack
     */
    void writeToFile() throws IOException;


    /**
     * A class extending this interface is expected to read csv values
     * from  argument @reader
     * <p>
     * This method should also deal with any errors that may arise from
     * IO operations, e.g. opening of binary files, or malformed CSV's
     *
     * @throws FileNotFoundException: The exception occuring when a file isn't found
     */
    List<T> readFromFile() throws FileNotFoundException;
}

interface DbInterface {
    void writeToDb(Db db);

    void readFromDb(Db db);

    void writeToFile(BufferedWriter writer) throws IOException;

    List<Patient> readFromFile(BufferedReader reader);
}

