package bo;

public class CompteSimple extends Compte{

    private double decouvert;

    public CompteSimple(int id, double solde, int id_agence, double decouvert){
        super(id, solde, id_agence);
        this.decouvert = decouvert;
    }

    public CompteSimple(double solde, int id_agence, double decouvert){
        super(solde, id_agence);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }

    @Override
    public String toString(){
        return "Compte : " + this.id + "\n Solde : " + this.solde + "\n Agence : " + this.id_agence + "\n Decouvert : " + decouvert;
    }


    @Override
    public void retrait(double montant) {
        if(this.solde-montant > decouvert){
            this.solde -= montant;
        }

    }
}
