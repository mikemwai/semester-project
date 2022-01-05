package com.main;

import com.main.db.Db;
import com.main.gui.Gui;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();


    public Main() throws SQLException {
    }


    public static void main(String[] args) {
         // do something.
        try {
            Db db = new Db();
        }catch (SQLException e){
            // exit for no reason at all.
            System.exit(1);
        }
	// write your code here
        LOGGER.info("Welcome to ...., we are glad you are here.");
        Gui.main(null);
    }
}
