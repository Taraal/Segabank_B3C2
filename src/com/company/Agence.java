package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.company.Data;

public class Agence {

    static ArrayList<Agence> Agences;
    public ArrayList<Compte> Comptes;
    public int id_agence;
    public String codeAgence;
    public String adresse;

    public Agence(int id, String codeAgence, String adresse){
        this.id_agence = id;
        this.codeAgence = codeAgence;
        this.adresse = adresse;
    }

    public static Agence newAgence(String codeAgence, String adresse) throws SQLException, IOException, ClassNotFoundException {
        //TODO:
        //Implémenter Data.getLatestAgence();
        Data.getLatestAgence();
        int id = 0; //A changer
        Agence newAgence = new Agence(id, codeAgence, adresse);
        //TODO :
        //Implémenter Data.addAgence(newAgence);
        Agence.Agences.add(newAgence);
        return  newAgence;
    }

    public List<Compte> getComptes(){
        //TODO:
        //Implémenter Data.getComptes()
        this.Comptes = new ArrayList<>();

        return Comptes;
    }
}
