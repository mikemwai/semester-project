package com.main;
import com.main.intf.Doctor;

import com.main.intf.Labs;
import com.main.intf.Patient;
import com.main.intf.Pharmacy;
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

    // These point to various files we will be using to write to output

    Patient patient;
    Doctor doctor;
    Labs labs;
    Pharmacy pharmacy;


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
                this.doctorsAnalysis();
                this.labResults();
                this.pharmacyAnalysis();
                this.finish();
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

    public void finish(){
        System.out.println("Thank you "+patient.name +" for visiting us");
        System.out.println("We hope you had a good stay");
        System.out.println("For inquiries call 0710202010");

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

        String assignedPersonnel = assignRandomPersonnelName();

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
            patient = new Patient(name, dob, ailment, assignedPersonnel);

            patient.writeToFile();
            // Print diagnostics
            LOGGER.info("\n\nData for patient "+name+" written to "+ Patient.PATIENTS_FILE.getAbsolutePath()+"\n\n");

            System.out.println("Wait for a few minutes to see the doctor.\nNurse "+ patient.assignedPersonnel+" will come pick you up");

            System.out.println("======================================================");

        } catch (Exception e) {
            // if you fail, bail out, because I'm too lazy to handle
            // this.
            LOGGER.error(e);

            System.exit(1);
        }

    }

    private void doctorsAnalysis() {
        System.out.println("For Doctor's use only");

        System.out.println("Name");
        String name = scanner.nextLine();

        System.out.println("Doctor_Id number:");
        String Doctor_Id = String.valueOf(scanner.nextInt());

        System.out.println("Profession:");
        String profession = scanner.nextLine();

        System.out.println("Date");
        String reportTime = scanner.nextLine();

        System.out.print("Analysis of the patient:");
        String analysis = scanner.nextLine();


        try {
            doctor = new Doctor(name, profession, analysis, Doctor_Id, reportTime);

            doctor.writeToFile();

            LOGGER.info("\n\nData for doctor" + name + profession + Doctor_Id + " written to " + Doctor.DOCTORS_FILE.getAbsolutePath() + "\n\n");

            System.out.println("====================================================");
        } catch (Exception e) {
            LOGGER.error(e);

            System.exit(1);

        }
    }
    private void pharmacyAnalysis(){
        System.out.println("PHARMACY DEPARTMENT:");

        System.out.println("PatientID");
        String PatientID = scanner.nextLine();

        System.out.println("Medicine");
        String medicine = scanner.nextLine();

        System.out.println("Price");
        String price = scanner.nextLine();

        try
        {

            pharmacy = new Pharmacy(patient.name, patient.sickness, patient.assignedPersonnel,PatientID,medicine,price);

            pharmacy.writeToFile();

            LOGGER.info("\n\nData for patient "+patient.name+" written to "+Pharmacy.PHARMACY_FILE.getAbsolutePath()+"\n\n");

        }
        catch (Exception e)
        {

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
        String results = scanner.nextLine();

        System.out.println("cost");
        int cost = scanner.nextInt();


        // Some good formatting.
        System.out.println("\n\nDETAILS FOR PATIENT " + name);
        System.out.println("____________________________________________");
        System.out.println("NAME:\t\t\t" + name);
        System.out.println("processingTime:\t" + processingTime);
        System.out.println("specimen:\t\t" + specimen);
        System.out.println("results:\t\t" + results);
        System.out.println("The price is is:\t\t" + cost);
        System.out.println("ASSIGNED TO:\t" + patient.assignedPersonnel);
        System.out.println("============================================");

        try {

            labs = new Labs(name, specimen,patient.assignedPersonnel,cost,results);

            labs.writeToFile();

            LOGGER.info("\n\nData for patient " + name + " written to " + Labs.LABS_FILE.getAbsolutePath() + "\n\n");

            System.out.println("======================================================");


        } catch (Exception e) {
            LOGGER.error(e);

            System.exit(1);
        }
        System.exit(1);

    }

    /*
     *  Create random names for people
     * */
    String assignRandomPersonnelName() {
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

