package com.company;

import java.util.List;

public class Agence {

    static List<Agence> Agences;
    private int id_agence;
    private String codeAgence;
    private String adresse;

    public Agence(String codeAgence, String adresse){
        this.codeAgence = codeAgence;
        this.adresse = adresse;
    }
}
