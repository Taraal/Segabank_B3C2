package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import bo.Compte;
import bo.CompteEpargne;
import bo.ComptePayant;
import bo.CompteSimple;

import static javax.swing.UIManager.put;

public class CompteDAO implements IDAO<Long, Compte>{

    private static final String INSERT_QUERY = "INSERT INTO compte (id_compte, solde, interet, decouvert, id_typeCompte, id_agence) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE compte SET solde = ?, interet = ?, decouvert = ?, id_typeCompte = ?, id_agence= ?  WHERE id_compte = ?";
    private static final String REMOVE_QUERY = "DELETE FROM compte WHERE id_compte = ?";
    private static final String FIND_QUERY = "SELECT * FROM compte WHERE id_compte = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM compte";

    private static final Map<String, Integer> typesCompte = new HashMap<>();


    @Override
    public void create(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if( connection != null){
            try(PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)){
                ps.setInt( 1, compte.getId());
                ps.setDouble(2, compte.getSolde());

                if(compte.getClass().getSimpleName().toString().equals("CompteEpargne")){
                    ps.setDouble(3,  ((CompteEpargne) compte).getTauxInteret()  );
                }
                else{
                    ps.setDouble(3, 0);
                }

                if(compte.getClass().getSimpleName().toString().equals("CompteSimple")){
                    ps.setDouble(4,  ((CompteSimple) compte).getDecouvert()  );
                }
                else{
                    ps.setDouble(4, 0);
                }

                ps.setInt(5, typesCompte.get(compte.getClass().getSimpleName().toString()));
                ps.setInt(6, compte.getId_agence());

                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if (rs.next()){
                        compte.setId(rs.getInt(1));
                    }
                }
            }
        }

    }

    @Override
    public void update(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( UPDATE_QUERY ) ) {
                ps.setDouble( 1, compte.getSolde() );

                if(compte.getClass().getSimpleName().equals("CompteEpargne"))
                    ps.setDouble( 2, ((CompteEpargne) compte).getTauxInteret() );
                else
                    ps.setDouble( 2, 0 );

                if(compte.getClass().getSimpleName().equals("CompteSimple"))
                    ps.setDouble( 3, ((CompteSimple) compte).getDecouvert() );
                else
                    ps.setDouble( 3, 0 );

                ps.setInt( 4, typesCompte.get(compte.getClass().getSimpleName()) );
                ps.setInt( 5, compte.getId_agence());



                ps.setInt( 6, compte.getId() );
                ps.executeUpdate();
            }
        }

    }

    @Override
    public void remove(Compte compte) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( REMOVE_QUERY ) ) {
                ps.setInt( 1, compte.getId() );
                ps.executeUpdate();
            }
        }

    }

    @Override
    public Compte findById(Long id) throws SQLException, IOException, ClassNotFoundException {

        Compte compte = null;
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_QUERY ) ) {
                ps.setLong( 1, id );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        if(rs.getInt( "id_typeCompte" ) == 3)
                            compte=new ComptePayant(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getInt("id_agence"));
                        else if(rs.getInt( "id_typeCompte" ) == 2)
                            compte=new CompteEpargne(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getDouble("interet"), rs.getInt("id_agence"));
                        else if(rs.getInt( "id_typeCompte" ) == 1)
                            compte=new CompteSimple(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getInt("id_agence"), rs.getDouble("decouvert"));

                    }
                }
            }
        }
        return compte;

    }

    @Override
    public List<Compte> findAll() throws SQLException, IOException, ClassNotFoundException{

        List<Compte> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next() ) {
                        Compte compte = null;
                        if(rs.getInt( "id_typeCompte" ) == 3)
                            compte=new ComptePayant(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getInt("id_agence"));
                        else if(rs.getInt( "id_typeCompte" ) == 2)
                            compte=new CompteEpargne(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getDouble("interet"), rs.getInt("id_agence"));
                        else if(rs.getInt( "id_typeCompte" ) == 1)
                            compte=new CompteSimple(rs.getInt( "id_compte" ), rs.getDouble("solde"), rs.getInt("id_agence"), rs.getDouble("decouvert"));


                        list.add( compte );
                    }
                }
            }
        }
        return list;

    }



}