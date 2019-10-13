package bo;

abstract public class Compte {
    private int id_compte;
    private int solde;

    abstract public  String toString();

    abstract public void versement();

    abstract public void retrait();
}
