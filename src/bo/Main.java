package bo;

import dao.AgenceDAO;
import dao.CompteDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Main {

    private static AgenceDAO agenceDAO = new AgenceDAO();
    private static CompteDAO compteDAO = new CompteDAO();
    private static List<Agence> listeAgences = new ArrayList<>();
    private static List<Compte> listeComptes = new ArrayList<>();


    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

        /*
        Suites à des complications personnelles, l'application console n'a pas pu être aboutie.
        Les choix restent limités et le déroulement est fixe. La logique de l'application reste néanmoins fonctionnelle.
        Les problèmes rencontrés ont été uniquement dûs au temps limité, et non pas à une incompréhension du programme.

        Merci de votre compréhension.
         */
        Scanner sc = new Scanner(System.in);
        listeAgences = agenceDAO.findAll();
        System.out.println("Liste des agences : \n");
        for (Agence agence : listeAgences){
            System.out.println(agence.getId_agence() + " - " + agence.getCodeAgence() + " - " + agence.getAdresse() + "\n");
        }
        System.out.println("Choisir l'agence à consulter \n");
        int idAgenceChoisie = sc.nextInt();
        Agence agenceChoisie = agenceDAO.findById(idAgenceChoisie);

        listeComptes = compteDAO.findByAgency(idAgenceChoisie);

        System.out.println("Consultation de l'agence " + agenceChoisie.getCodeAgence());
        for(Compte compte : listeComptes){
            System.out.println(compte.toString());
        }
        System.out.println("\n Quel compte souhaitez vous modifier ? \n");
        sc.nextLine();
        int idCompteChoisi = sc.nextInt();

        Compte compteChoisi = compteDAO.findById((long) idCompteChoisi);
        System.out.println(compteChoisi.toString());
        System.out.println("Que souhaitez vous faire ? \n");
        System.out.println("1 - Effectuer un virement \n");
        System.out.println("2 - Effectuer un retrait \n");
        System.out.println("3 - Modifier le compte \n");
    }
}
