package com.main;

import com.main.intf.Labs;
import com.main.intf.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

/*
 * Console app
 *
 * */
public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger LOGGER = LogManager.getLogger();
    // This points to the patients file where we will
    // be writing data regarding new patients.
    private static final  File PATIENTS_FILE = new File("./patients.csv");


    public static void main(String[] args) {

        System.out.println("Hello and welcome to Makini Hospital");


        // cin;
        Scanner input = new Scanner(System.in);


        try {
            System.out.println("Choose one of the following \n1.) Console \n2.) GUI");

            // cin>>choice;
            int choice = input.nextInt();
            Console console = new Console();

            switch (choice) {
                case 1:
                    console.console();
                    break;
                case 2:
                    System.out.println("GUI not available, try later");
                    break;
                default:
                    System.out.println("Choice " + choice + " not known , choose either 1 or 2");
                    break;
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public void console() {

        System.out.println("Welcome to console mode");

        System.out.println("Choose either \n1.) Registration \n2.) Admin");

        Scanner input = new Scanner(System.in);

        int choice2 = input.nextInt();

        switch (choice2) {
            case 1: {
                // registration, let's finally write code for that
                this.startRegistration();

            }

            case 2: {
                System.out.println("Still under Construction!");
                break;
            }

            default: {
                System.out.println("Still under Construction!!");
                break;
            }
        }
    }

    /*
    * Start the registration process.
    * */
    private void startRegistration() {

        try {
            // clear the screen.

            // TODO: Change this to be Windows because presentation will probably be on windows,
            Runtime.getRuntime().exec("clear");
        } catch (Exception ignored) {
            // Don't care id it fails.
        }
        // Everything else.
        System.out.println("Welcome to registration :)\n" +
                "We're glad you're here ");
        System.out.println("Please input necessary fields");

        System.out.println("Name");
        String name = scanner.nextLine();

        System.out.println("Date of birth dd-mm-yyyy");

        String dob = scanner.nextLine();

        System.out.println("Ailment");

        String ailment = scanner.nextLine();

        String assignedPersonnel = assignRandomName();

        System.out.println("Thank you for filling the details, you have been assigned to " + assignedPersonnel);
        // Some good formatting.
        System.out.println("\n\nDETAILS FOR PATIENT " + name);
        System.out.println("____________________________________________");
        System.out.println("NAME:\t\t\t" + name);
        System.out.println("DATE OF BIRTH:\t" + dob);
        System.out.println("AILMENT:\t\t" + ailment);
        System.out.println("ASSIGNED TO:\t" + assignedPersonnel);
        System.out.println("============================================");

        try {
            // create the patient class.
            Patient patient = new Patient(name, dob, ailment, assignedPersonnel);

            // create the new file, if it doesn't exist.
            // Probably only important for the first run
            PATIENTS_FILE.createNewFile();

            // Use a buffered writer because it's good practice.
            // NO I WILL ALWAYS CODE CORRECTLY (LEAVE ME ALONE)
            FileWriter fw = new FileWriter(PATIENTS_FILE,true);

            BufferedWriter bw = new BufferedWriter(fw);
            // done write details
            patient.writeToFile(bw);

            // Print diagnostics
            LOGGER.info("\n\nData for patient "+name+" written to "+PATIENTS_FILE.getAbsolutePath()+"\n\n");

            System.out.println("Wait for a few minutes to see the doctor.\nNurse "+ patient.assignedPersonnel+" will come pick you up");

            System.out.println("======================================================");

            // Tell the logger to inform me how things are going


        } catch (Exception e) {
            // if you fail, bail out, because I'm too lazy to handle
            // this.
            LOGGER.error(e);

            System.exit(1);
        }


    }
    private void labResults() {
                
        System.out.println("Please do not access.Personnel authorized only");

        System.out.println("Name");
        String name = scanner.nextLine();

        System.out.println("processing time dd-mm-yyyy");

        String processingTime = scanner.nextLine();

        System.out.println("specimen");

        String specimen = scanner.nextLine();
        
        System.out.println("results");
        String results=scanner.nextLine();
        
        System.out.println("cost");
        int cost=scanner.nextInt();

        
        String assignedPersonnel = assignRandomName();

        System.out.println("Thank you for filling the details, you have been assigned to " + assignedPersonnel);
        // Some good formatting.
        System.out.println("\n\nDETAILS FOR PATIENT " + name);
        System.out.println("____________________________________________");
        System.out.println("NAME:\t\t\t" + name);
        System.out.println("processingTime:\t" + processingTime);
        System.out.println("specimen:\t\t"+specimen);
        System.out.println("results:\t\t" + results);
        System.out.println("The price is is:\t\t"+cost);
        System.out.println("ASSIGNED TO:\t" + assignedPersonnel);
        System.out.println("============================================");

        try {
            
            Labs labs = new Labs(name, processingTime,specimen, results, assignedPersonnel);


            File Labs_FILE = null;
            Labs_FILE.createNewFile();

           
            FileWriter fw = new FileWriter(Labs_FILE,true);

            BufferedWriter bw = new BufferedWriter(fw);
            // done write details
            labs.writeToFile(bw);

            
            LOGGER.info("\n\nData for patient "+name+" written to "+Labs_FILE.getAbsolutePath()+"\n\n");
            

            System.out.println("======================================================");



        } catch (Exception e) {
            LOGGER.error(e);

            System.exit(1);
        }


    }
    /*
     *  Create random names for people
     * */
    String assignRandomName() {
        String[] names = {
                "Leslie Barnett", "Devyn Wood", "Itzel Simpson", "Braiden Chandler",
                "Caitlin Berry", "Neveah Brady", "Araceli Garrett", "Kaylen Wu", "Adriana Summers",
                "Taryn Carrillo", "Allie Hughes", "Jordyn Barber", "Emmy Tanner", "Jax Hernandez",
                "Gordon Kane", "Trinity Caldwell", "Cole Robles", "Kaeden Eaton", "Summer Boyle",
                "Valentino Grant"
        };

        Random random = new Random();

        int next = random.nextInt(names.length - 1);

        return names[next];
    }
}

