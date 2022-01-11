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


/*
* Main GUI class, all other class should derive from this
*
*  */
public class Gui {

    public static void main(String[] args) {
        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("click");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height
        JTextArea c = new JTextArea();
        c.setSize(100,100);
        f.add(c);
        f.add(b);//adding button in JFrame

        f.setSize(400,500);//400 width and 500 height
        // ()c.toString()4
        c.getText();
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        // create empty JTextField
    }
}
