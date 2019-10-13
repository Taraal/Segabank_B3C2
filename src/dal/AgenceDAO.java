package dal;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bo.Agence;
import jdk.jshell.PersistentSnippet;

public class AgenceDAO implements IDAO<Integer, Agence> {

    private static final String INSERT_QUERY = "INSERT INTO Agence (id_agence, code, adresse) VALUES(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE Agence SET code = ?, adresse= ? WHERE id_agence = ?";
    private static final String REMOVE_QUERY = "DELETE FROM Agence WHERE id_agence = ?";
    private static final String FIND_QUERY = "SELECT * FROM Agence WHERE id_agence = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM Agence";

    @Override
    public void create(Agence agence) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection
                    .prepareStatement( INSERT_QUERY, Statement.RETURN_GENERATED_KEYS ) ) {
                ps.setInt( 1, agence.getId_agence() );
                ps.setString( 2, agence.getCodeAgence() );
                ps.setString(3, agence.getAdresse());

                ps.executeUpdate();
                try ( ResultSet rs = ps.getGeneratedKeys() ) {
                    if ( rs.next() ) {
                        agence.setId_agence( rs.getInt( 1 ) );
                    }
                }
            }
        }

    }


    @Override
    public void update(Agence agence) throws SQLException, IOException, ClassNotFoundException{

        Connection connection = PersistenceManager.getConnection();
        if ( connection != null){
            try( PreparedStatement ps = connection.prepareStatement( UPDATE_QUERY)){
                ps.setString(1, agence.getCodeAgence());
                ps.setString(2, agence.getAdresse());
                ps.setInt(3, agence.getId_agence());
            }
        }
    }


    @Override
    public void remove(Agence agence) throws SQLException, IOException, ClassNotFoundException{

        Connection connection = PersistenceManager.getConnection();
        if(connection != null){
            try(PreparedStatement ps = connection.prepareStatement(REMOVE_QUERY)){
                ps.setInt(1, agence.getId_agence());
            }
        }
    }


    @Override
    public Agence findById(Integer id) throws SQLException, IOException, ClassNotFoundException {

        Agence agence = null;
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_QUERY ) ) {
                ps.setLong( 1, id );
                try ( ResultSet rs = ps.executeQuery() ) {
                    if ( rs.next() ) {
                        agence = new Agence(
                                rs.getInt( "id_agence" ),
                                rs.getString( "code" ),
                                rs.getString("adresse")
                        );

                    }
                }
            }
        }
        return agence;
    }

    @Override
    public List<Agence> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Agence> list = new ArrayList<>();
        Connection connection = PersistenceManager.getConnection();
        if ( connection != null ) {
            try ( PreparedStatement ps = connection.prepareStatement( FIND_ALL_QUERY ) ) {
                try ( ResultSet rs = ps.executeQuery() ) {
                    while ( rs.next() ) {
                        Agence agence = new Agence(
                                rs.getInt( "id_agence" ),
                                rs.getString( "code" ),
                                rs.getString("adresse")
                        );

                        list.add( agence );
                    }
                }
            }
        }
        return list;
    }

}
