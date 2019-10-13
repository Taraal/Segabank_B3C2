package bo;

public class ComptePayant extends Compte {

    private double taxe;

    public ComptePayant(int identifiant, double solde, int id_agence, double taxe) {
        super(identifiant, solde, id_agence);
        this.taxe = taxe;
    }

    public ComptePayant(double solde, int id_agence, double taxe) {
        super(solde, id_agence);
        this.taxe = taxe;
    }

    @Override
    public void versement(double montant) {
        this.solde += (montant - taxe);
    }

    @Override

    public void retrait(double montant) {
        if (this.solde - (montant + taxe) > 0) {
            this.solde -= (montant + taxe);
        }
    }

    @Override
    public String toString() {
        return "Compte : " + this.id + "\n Solde : " + this.solde + "\n Agence : " + this.id_agence + "\n Taxe fixe : " + taxe;
    }
}