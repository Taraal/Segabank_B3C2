package bo;

abstract public class Compte {
    protected int id;
    protected double solde;
    protected  int id_agence;

    public Compte(int id, double solde, int id_agence){
        this.id = id;
        this.solde = solde;
        this.id_agence = id_agence;
    }

    public Compte(double solde, int id_agence){
        this.solde = solde;
        this.id_agence=id_agence;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public double getSolde(){
        return this.solde;
    }

    public void setSolde(double solde){
        this.solde = solde;
    }

    public int getId_agence() {
        return id_agence;
    }

    public void setId_agence(int id_agence) {
        this.id_agence = id_agence;
    }

    public void versement(double montant){
        this.solde += montant;
    }

    public void retrait(double montant){
        if(this.solde - montant > 0){
            this.solde -= montant;
        }
    }

    @Override
    public String toString(){
        return "Compte : " + this.id + "\n Solde : " + this.solde + "\n Agence : " + this.id_agence;
    }
}
