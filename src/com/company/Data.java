package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import SegaBank_B3C2.agence;

public class Data {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/contact?useSSL=false&useLegacyDateTimeCode=false&serverTimeCode=utc";
    private static final String DB_LOGIN = "root";
    private static final String DB_PWD = "";

    public static void main(String[] args){
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact?useSSL=false&useLegacyDateTimeCode=false&serverTimeCode=utc", "root", "");
            try{
                PreparedStatement rq = c.prepareStatement("INSERT INTO agence (code, adresse) VALUES (?, ?)");
                rq.setString(1, agence.getCode());
                rq.setString(2, agence.getAdresse());
            }
        }
    }
}
