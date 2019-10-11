package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.company.Agence;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Data {
    private static final String PROPS_FILE = "././ressources/db.properties";

    public static void getLatestAgence() throws IOException, ClassNotFoundException, SQLException{
        try{
            Properties props = new Properties();
            try ( FileInputStream fis = new FileInputStream( PROPS_FILE ) ) {
                props.load( fis );
            }

            String driverClass = props.getProperty( "jdbc.class.driver" );
            String dbURL = props.getProperty( "jdbc.db.url" );
            String dbLogin = props.getProperty( "jdbc.db.login" );
            String dbPWD = props.getProperty( "jdbc.db.pwd" );

            Connection c = DriverManager.getConnection( dbURL, dbLogin, dbPWD );
            try{
                PreparedStatement rq = c.prepareStatement("SELECT * FROM agence");
                rq.execute();
             //   rq.setString(1, "prout");
             //   rq.setString(2, "caca");
                System.out.println("rq passed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args){

    }
}
