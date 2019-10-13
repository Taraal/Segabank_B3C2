package bo;

import bo.Compte;

public class CompteEpargne extends Compte {

    private double tauxInteret;

    public CompteEpargne(int id, double solde, double tauxInteret, int id_agence){
        super(id, solde, id_agence);
        this.tauxInteret = tauxInteret;
    }

    public CompteEpargne(double solde, double tauxInteret, int id_agence){
        super(solde, id_agence);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public void addInteret(){
        this.solde += (solde * tauxInteret);
    }

    @Override
    public String toString() {
        return "Compte Epargne : " + id + "\n Solde : " + solde + "\n Agence : " + id_agence;
    }
}
