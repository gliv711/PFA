package PFA.GestionDemandes.Service;


import PFA.GestionDemandes.Module.DemandeModu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static PFA.dbConnection.dbConnection.getOracleConnection;


public class DemandeServ {
    public static void Ajouter(DemandeModu p) {
       

        String SQLquery = "insert into DEM values('" + p.getNom() + "','" +
                p.getPrenom() + "'," + p.getCin() + ",'" +p.getAdresse()
                + "'," + p.getNumtel() + ", DEM_SEQ.nextval," +
                "TO_DATE('" + p.getDateDem().toString() + "','yyyy-mm-dd')" + ",'" +
                p.getTypeDem() + "','" +
                p.getDescreption() + "')";

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

    public static void Modifier(DemandeModu p) {
       /* String SQLquery = String.format("update dem set " +
                        "nom = '%s'," +
                        "prenom = '%s'," +
                        "cin = %d," +
                        "adresse = '%s'," +
                        "numtel = %d," +
                        "datedem = TO_DATE('%s','YYYY-MM-DD')," +
                        "typedem = '%s'," +
                        "descreption = '%s'" +
                        "where id = %d", p.getNom(), p.getPrenom(), p.getCin(), p.getCin(),p.getAdresse(),
                p.getNumtel(),p.getDateDem().toString(),p.getTypeDem(),p.getDescreption(),p.getId());
        */

        String SQLquery = "update dem set nom = '" + p.getNom() + "',prenom = '" + p.getPrenom() + "',cin =" +
                     p.getCin() + " , adresse= '" + p.getAdresse() + "',numtel =" + p.getNumtel() + ",datedem = to_date('" +
                p.getDateDem().toString() + "','yyyy-mm-dd')" + ",typedem ='" + p.getTypeDem() + "',descreption='" +
                p.getDescreption() + "'where id =" + p.getId() + "";
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

    public static void Supprimer(Integer id) {
        String query = "delete from dem where id = " + id ;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<DemandeModu> Recherche(String term) {
        List<DemandeModu> data = new ArrayList<>();
        String SQLquery = "SELECT * from dem where lower(nom) like lower('%" + term + "%') " +
                "or lower(prenom) like lower('%" + term + "%')";
        try {
            Connection connection = getOracleConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SQLquery);
            while (rs.next()) {
                data.add(new DemandeModu(
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getInt("cin"),
                        rs.getString("adresse"),
                        rs.getInt("numtel"),
                        rs.getInt("id"),
                        rs.getDate("datedem").toLocalDate(),
                        rs.getString("typedem"),
                        rs.getString("descreption")));

            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }

}
