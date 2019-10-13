package bo;

import java.util.ArrayList;
import java.util.List;

public class Agence {

    private List<Compte> Comptes;
    private int id_agence;
    private String codeAgence;
    private String adresse;

    public Agence(String codeAgence, String adresse){
        super();
        this.Comptes = new ArrayList<>();
        this.codeAgence = codeAgence;
        this.adresse = adresse;
    }

    public Agence(int Id, String codeAgence, String adresse){
        super();
        this.id_agence = Id;
        this.Comptes = new ArrayList<>();
        this.codeAgence = codeAgence;
        this.adresse = adresse;
    }

    public String getCodeAgence(){
        return this.codeAgence;
    }

    public void setCodeAgence(String code){
        this.codeAgence = code;
    }

    public int getId_agence(){
        return this.id_agence;
    }

    public void setId_agence(int idAgence){
        this.id_agence = idAgence;
    }

    public String getAdresse(){
        return adresse;
    }

    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

    public List<Compte> getComptes(){
        return Comptes;
    }

    public void setComptes(List<Compte> comptes){
        this.Comptes = comptes;
    }

    public void removeCompte(Compte delCompte){
        this.Comptes.remove(delCompte);
    }
}
