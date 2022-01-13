 package com.main;

 import java.util.Scanner;
 import java.util.logging.Logger;

 /*
 * Console app
 *
 * */
public class Console {

    public static void main(String[] args)
    {

        System.out.println("Hello and welcome to Makini Hospital");


        // cin;
        Scanner input = new Scanner(System.in);


            try {
                System.out.println("Choose one of the following \n1.) Console \n2.) GUI");

                // cin>>choice;
                int choice = input.nextInt();

                switch (choice){
                    case 1:
                        // Woow....

                        console();

                        break;
                    case 2:
                        System.out.println("GUI not available, try later");
                        break;
                    default:
                        System.out.println("Choice "+choice+" not known , choose either 1 or 2");
                        break;
                }

            } catch (Exception e) {

                e.printStackTrace();

            }

    }

    public static void console(){
        System.out.println("Welcome to console mode");

    }

}
