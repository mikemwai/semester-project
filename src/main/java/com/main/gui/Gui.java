/*
 *  Copyright © Group 4 Authors 2021-2022.
 *
 *
 * This package contains the Graphical User Interface and is the start
 * of all GUI  interactions that this small project will use as an interface.
 *
 *
 * The main writer @ Venoliah Angel
 */
package com.main.gui;
// GUI handles
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


/*
* Main GUI class, all other class should derive from this
*
*  */
public class Gui implements ActionListener {

    public static JDesktopPane desk;
    static JInternalFrame frame1;
    static JInternalFrame frame2;
    static JInternalFrame frame3;
    static JInternalFrame frame4;

    private static  JFrame frame;

    private static  String username;
    private static String password;

   public  static void main (String [] args){

        frame=new JFrame("MAKINI HOSPITAL");//creating instance of JFrame

        frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =new JPanel();
        frame.add(panel);
        placeComponents(panel);
        frame.setVisible(true);

        Gui d=new Gui();

    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel enterLabel=new JLabel("Personnel");
        enterLabel.setBounds(130,100,200,40);
        panel.add(enterLabel);

        JTextField enterText=new JTextField(30);
        enterText.setBounds(230,100,100,40);
        enterText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get username
                username =  enterText.getText();
            }
        });
        panel.add(enterText);


        JLabel passwordLabel =new JLabel("Enter password");
        passwordLabel.setBounds(130,200,100,25);
        panel.add(passwordLabel);

        JPasswordField encryptionText=new JPasswordField(30);
        encryptionText.setBounds(230,200,100,40);
        encryptionText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get password
                password = String.valueOf(encryptionText.getPassword());
            }
        });
        panel.add(encryptionText);

        JButton exiButton = new JButton("EXIT");
        exiButton.setBounds(200,400,40,20);
        exiButton.addActionListener(new CloseListener());
        panel.add(exiButton);

        JButton enterButton=new JButton("Login");
        enterButton.setBounds(230,250,100,35);
        JLabel welcome =new JLabel("Well welcome :) ");
        welcome.setBounds(230,290,200,25);
        panel.add(welcome);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(password, "OOPs")) {
                    welcome.setText("Welcome Admin");

                }
                else{
                    welcome.setText("WE DON'T KNOW YOU");
                }
                frame.dispose();

            }
        });
        panel.add(enterButton);




        {;;
          frame=new JFrame("PERSONNEL VIEW");
          frame.dispose();
          desk=new JDesktopPane();
          frame1=new JInternalFrame("frame1",true,true,true,true);
          frame1.setBounds(20,200,150,100);
          frame1.setVisible(true);

            frame2=new JInternalFrame("frame1",true,true,true,true);
            frame2.setBounds(20,200,150,100);
            frame2.setVisible(true);

            frame3=new JInternalFrame("frame1",true,true,true,true);
            frame3.setBounds(20,200,150,100);
            frame3.setVisible(true);

            frame4=new JInternalFrame("frame1",true,true,true,true);
            frame4.setBounds(20,200,150,100);
            frame4.setVisible(true);

            desk.add(frame1);
            desk.add(frame2);
            desk.add(frame3);
            desk.add(frame4);
            frame.setSize(400,500);
            frame.setVisible(true);
        }




    }
    private static void exitFuntcion(){
        frame.dispose();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
/// Close the GUI
/// not the best way, and we should probably use this.dispose()
/// but I'm lazy and don't feel like doing it.
class CloseListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        //DO SOMETHING
        System.exit(1);
    }
}