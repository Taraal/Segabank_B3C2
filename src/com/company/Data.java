package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.company.Agence;

public class Data {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/segabank?useSSL=false&useLegacyDateTimeCode=false&serverTimeCode=utc";
    private static final String DB_LOGIN = "root";
    private static final String DB_PWD = "";

    public static void getLatestAgence(){
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact?useSSL=false&useLegacyDateTimeCode=false&serverTimeCode=utc", "root", "");
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
