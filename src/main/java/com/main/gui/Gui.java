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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
* Main GUI class, all other class should derive from this
*
*  */
public class Gui implements ActionListener {

    private static JLabel enterLabel;
    private static JTextField enterText;
    private static JLabel passwordLabel;
    private static JPasswordField encryptionText;
    private static JButton enterButton;
    private static JLabel welcome;

    public static void main(String[] args) {
        JFrame frame=new JFrame("MAKINI HOSPITAL");//creating instance of JFrame

        frame.setSize(400,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel =new JPanel();
        frame.add(panel);

        placeComponents(panel);
        frame.setVisible(true);


    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);
        JLabel enterLabel=new JLabel("Personnel");
        enterLabel.setBounds(130,100,100,40);
        panel.add(enterLabel);

        JTextField enterText=new JTextField(30);
        enterText.setBounds(130,100,100,40);
        panel.add(enterText);

        JLabel exitLabel= new JLabel("EXIT");
        exitLabel.setBounds(50,40,40,20);
        panel.add(exitLabel);

        JLabel passwordLabel =new JLabel("Enter password");
        passwordLabel.setBounds(30,50,100,25);
        panel.add(passwordLabel);

        JPasswordField encryptionText=new JPasswordField(30);
        encryptionText.setBounds(30,50,100,25);
        panel.add(encryptionText);

        JButton enterButton=new JButton("CLICK");
        enterButton.setBounds(30,50,100,25);
        enterButton.addActionListener(new Gui());
        panel.add(enterButton);

        JLabel welcome =new JLabel("Well welcome : ");
        welcome.setBounds(30,50,100,25);
        welcome.setText("How can we be of assistance:");



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String personnel= enterText.getText();
        String password=encryptionText.getText();

       if (personnel.equals("Caleb") && password.equals("OOPs")){
           welcome.setText("You're in !");
       }
       else if (personnel.equals("Mike") && password.equals("OOPs")){
           welcome.setText("You're in");
       }
       else if (personnel.equals("Precious") && password.equals("OOPs")){
           welcome.setText("You're in");
       }
       else if (personnel.equals("Venoliah") && password.equals("OOPs")){
           welcome.setText("You're in");
       }
      else {
          welcome.setText("Please exit .Authorized personel only!!!");
       }


    }
}
