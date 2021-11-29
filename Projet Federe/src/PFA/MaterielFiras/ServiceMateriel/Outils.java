package PFA.MaterielFiras.ServiceMateriel;

import PFA.MaterielFiras.ModuleMateriel.Outil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;


public class Outils {
    public static void Ajouter(Outil p) {
       /* String SQLquery = String.format("insert into outil values (" +
                "outil_seq.nextval,'%s',%d,%d)", p.getNom(), p.getQuantite(), p.getConsumable());

        */

        String SQLquery = "insert into outil values(outil_seq.nextval , '" +
                p.getNom() + "'," + p.getQuantite() + "," +p.getConsumable() + ")";
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
            System.out.println("ajouté");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public static List<Outil> Recherche(String term) {
        List<Outil> liste = new ArrayList<>();
        String query = "select * from OUTIL where lower(NOMOUTIL) like lower('%" + term + "%')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Outil(rs.getInt("idoutil"),
                        rs.getInt("quantiteoutil"),
                        rs.getString("nomoutil"),
                        rs.getInt("consumableoutil")));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    
    public static void Modifier(Outil p) {
        String SQLquery = "update OUTIL set  " +
                "  NOMOUTIL = '" + p.getNom() +
                "', QUANTITEOUTIL = " + p.getQuantite() +
                " where IDOUTIL = " + p.getId() + "";
        System.out.println(SQLquery);
        Statement statement;
        try {
            Connection connection = getOracleConnection();
            statement = connection.createStatement();
            statement.executeUpdate(SQLquery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void Suprrimer(Integer id) {
        String query = "delete from OUTIL where IDOUTIL = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public static List<Outil> OutilsListe() {
        ArrayList<Outil> data = new ArrayList<>();
        String SQLquery = "SELECT * from OUTIL ";
        try {
            Connection connection = getOracleConnection();
            
            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new Outil(
                        rs.getInt("idOutil"),
                        rs.getInt("quantiteOutil"),
                        rs.getString("nomOutil"),
                        rs.getInt("consumableOutil")));
                
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
        
    }
    
}

