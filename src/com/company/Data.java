package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.company.Agence;

import java.util.ArrayList;
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
import com.company.CompteSimple;
import com.company.CompteEpargne;
import com.company.ComptePayant;


public class Data {
    private static final String PROPS_FILE = "././ressources/db.properties";

    public static Connection getConnection() throws FileNotFoundException {
        Properties props = new Properties();
        try {
            try ( FileInputStream fis = new FileInputStream( PROPS_FILE ) ) {
                props.load( fis );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driverClass = props.getProperty( "jdbc.class.driver" );
        String dbURL = props.getProperty( "jdbc.db.url" );
        String dbLogin = props.getProperty( "jdbc.db.login" );
        String dbPWD = props.getProperty( "jdbc.db.pwd" );

        Connection c = null;
        try {
            c = DriverManager.getConnection( dbURL, dbLogin, dbPWD );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public static int getLatestAgenceId() {
        int latestId = 0;
        Connection c = null;
        try {
            c = getConnection();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            PreparedStatement rq = c.prepareStatement("SELECT * FROM agence ORDER BY id DESC LIMIT 1");
            ResultSet rs = rq.executeQuery();
            while(rs.next()){
                latestId = rs.getInt("id_agence");
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latestId;
    }

    public static ArrayList<Compte> getCompteByAgence(int agenceId){
        Connection c = null;
        try {
            c = getConnection();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            PreparedStatement rq = c.prepareStatement("SELECT * FROM agence ORDER BY id DESC LIMIT 1");
            ResultSet rs = rq.executeQuery();
            while(rs.next()){
                int typeCompte = rs.getInt("id_typeCompte ");
                switch (typeCompte){
                    case 1:



                }
            }
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return latestId;
    }
}
