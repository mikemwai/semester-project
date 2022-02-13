package com.main;

import com.main.db.Db;
import com.main.intf.Doctor;
import com.main.intf.Labs;
import com.main.intf.Patient;
import com.main.intf.Pharmacy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * Console app
 *
 *
 *
 * */
public class Console {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Logger LOGGER = LogManager.getLogger();

    // These point to various files we will be using to write to output

    // various uninitialized classes, initialized later
    Patient patient;
    Doctor doctor;
    Labs labs;
    Pharmacy pharmacy;


    public static void main(String[] args) {

        System.out.println("Hello and welcome to Makini Hospital");

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Choose one of the following \n1.) Console \n2.) GUI");

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
            LOGGER.error(e);
            System.exit(1);

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
                boolean cont = this.doctorsAnalysis();
                // Java doesn't support switch case with boolean
                // weird.
                if (cont)
                {
                    System.out.println("Proceeding to lab\n\n");

                    this.labResults();
                    this.pharmacyAnalysis();
                    this.finish();
                } else {
                    System.out.println("Hey you are not ill go home take some water\n\n");
                }
                break;
            }

            case 2: {
                this.adminConsole();
                break;
            }

            default: {
                System.out.println("Still under Construction!!");
                break;
            }
        }
    }

    private  void adminConsole(){
        System.out.println("======================================");
        System.out.println("ADMIN USE ONLY");

        String password = "SECURE";

        System.out.println("Enter password");

        String[] fileList = {"./doctors.csv","./labs.csv","./patients.csv","./pharmacy.csv"};

        String enteredPassword = scanner.nextLine();
        // String values are not compared with equals? null unsafety?
        // Refactored by the IDE.
        if (Objects.equals(enteredPassword, password)){
            System.out.println("Welcome Admin.");
            System.out.println("Enter file to view");
            System.out.println("1.)Doctors\n2.)Lab Reports\n3.)Patients\n4.)Pharmacy");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: case 2:
                case 3: case 4:
                    readFile(fileList[choice-1]);
                    break;
                default:
                    System.out.println("Invalid number, exiting");
            }


        } else{
            System.out.println("Not admin,exiting");
            System.exit(1);
        }

    }
    private void readFile(String name)  {
        try {


        BufferedReader in = new BufferedReader(new FileReader(name));

        System.out.println("==============================================");
        System.out.println("File "+name);
        System.out.println("==============================================");
        String line;
        while((line = in.readLine()) != null)
        {
            System.out.println(line);
        }
        in.close();
        }catch (Exception e){
            LOGGER.error(e);
            System.exit(1);
        }

    }
    private void finish() {
        System.out.println("Thank you " + patient.name + " for visiting us");
        System.out.println("We hope you had a good stay");
        System.out.println("For inquiries call 0710202010");

    }

    /*
     * Start the registration process.
     * */
    public void startRegistration() {

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

            Db db = new Db();
            db.insertPatient(patient);

            patient.writeToFile();
            // Print diagnostics
            System.out.println("Data for patient " + name + " written to " + Patient.PATIENTS_FILE.getAbsolutePath());

            System.out.println("======================================================");

            System.out.println("Wait for a few minutes to see the doctor.\nNurse " + patient.assignedPersonnel + " will come pick you up");


        } catch (Exception e) {
            // if you fail, bail out, because I'm too lazy to handle
            // this.
            LOGGER.error(e);

            System.exit(1);
        }

    }

    public boolean doctorsAnalysis() {

        System.out.println("====================================================");
        System.out.println("DOCTOR ANALYSIS");
        System.out.println("====================================================");

        System.out.println("For Doctor's use only");


        System.out.println("Profession(of the doctor):");
        String profession = scanner.nextLine();


        System.out.println("Analysis of the patient:");
        String analysis = scanner.nextLine();


        try {
            doctor = new Doctor(profession,patient.PatientID, analysis);

            System.out.println("You were handled by doctor "+ doctor.name);

            doctor.writeToFile();

            System.out.println("Data for Doctor analysis of patient " + patient.name + " written to " + Doctor.DOCTORS_FILE.getAbsolutePath() + "\n");

            System.out.println("==================================================");

        } catch (Exception e)
        {
            LOGGER.error(e);

            System.exit(1);

        }
        System.out.println("Send patient to lab?");

        String decision = scanner.nextLine();
        // if decision starts with y assume doctor typed yes
        return decision.startsWith("y");
    }

    public void pharmacyAnalysis() {
        System.out.println("==============================================");
        System.out.println("PHARMACY DEPARTMENT");
        System.out.println("==============================================");

        System.out.println("Medicine to be issued to patient "+ patient.name);
        // some weird bug in the scanner where it would skip. this
        scanner.nextLine();

        String medicine = scanner.nextLine();


        System.out.println("Price");
        String price = scanner.nextLine();

        try {

            pharmacy = new Pharmacy(patient.name, patient.sickness, patient.assignedPersonnel, patient.PatientID, medicine, price);

            pharmacy.writeToFile();

            System.out.println("Medicine issued for patient " + patient.name + " backed up to " + Pharmacy.PHARMACY_FILE.getAbsolutePath() + "\n\n");

        } catch (Exception e) {

            LOGGER.error(e);

            System.exit(1);
        }


    }

     public void labResults() {
        System.out.println("===========================================");
        System.out.println("LAB ANALYSIS");
        System.out.println("===========================================");

        System.out.println("Please do not access.Personnel authorized only");


        // get the current time
        String processingTime = new SimpleDateFormat("hh:mm:ss   dd-MM-yyyy").format(new Date());


        System.out.println("Specimen analysed");

        String specimen = scanner.nextLine();

        System.out.println("Results after analysing specimen");

        String results = scanner.nextLine();

        System.out.println("Cost of analysis");

        int cost = scanner.nextInt();

        // Some good formatting.
        System.out.println("\n\nDETAILS FOR PATIENT " + patient.name);
        System.out.println("____________________________________________");
        System.out.println("NAME:\t\t\t" + patient.name);
        System.out.println("Processed at:\t" + processingTime);
        System.out.println("Specimen:\t\t" + specimen);
        System.out.println("Results:\t\t" + results);
        System.out.println("Cost:\t\t\t" + cost);
        System.out.println("============================================");

        try {

            labs = new Labs(patient.name, specimen, patient.assignedPersonnel,processingTime, cost, results);

            labs.writeToFile();

            System.out.println("Data for patient " + patient.name + " written to " + Labs.LABS_FILE.getAbsolutePath() + "\n\n");

            System.out.println("==================================================");


        } catch (Exception e) {
            LOGGER.error(e);

            System.exit(1);
        }
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

