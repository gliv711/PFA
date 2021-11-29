package PFA.GestionFinances.Service;

import PFA.GestionFinances.Module.Operation;

import java.sql.*;
import java.util.ArrayList;

import static PFA.dbConnection.dbConnection.getOracleConnection;

public class OperationServices {
    static public void ajouter(Operation operation) {
        String query = "insert into operation (IDOPERATION, NOMOPERATION, TYPEOPRATION, DATEOPERATION, VALEUROPERATION) VALUES (OPERATION_SEQ.nextval,'" + operation.getNom() + "','" + operation.getType() + "',to_date('" + operation.getDate().toString() + "','yyyy-mm-dd'),'" + operation.getValeur() + "')";
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    static public void supprimer(int id) {
        String query = "delete from OPERATION where IDOPERATION = " + id;
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    static public ArrayList<Operation> parseOperationList() {
        String query = "select * from OPERATION";
        ArrayList<Operation> liste = new ArrayList<>();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Operation(rs.getInt("IDOPERATION"), rs.getString("NOMOPERATION"), rs.getString("TYPEOPRATION"), rs.getFloat("VALEUROPERATION"), rs.getDate("DATEOPERATION").toLocalDate()));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    
    static public ArrayList<Operation> parseOperationListByType(String type) {
        String query = "select * from OPERATION where TYPEOPRATION like '" + type + "'";
        ArrayList<Operation> liste = new ArrayList<>();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Operation(rs.getInt("IDOPERATION"), rs.getString("NOMOPERATION"), rs.getString("TYPEOPRATION"), rs.getFloat("VALEUROPERATION"), rs.getDate("DATEOPERATION").toLocalDate()));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
    static public ArrayList<Operation> recherche(String term) {
        String query = "select * from OPERATION where lower(OPERATION.NOMOPERATION) like lower('%" + term + "%')";
        ArrayList<Operation> liste = new ArrayList<>();
        try {
            Connection connection = getOracleConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                liste.add(new Operation(rs.getInt("IDOPERATION"), rs.getString("NOMOPERATION"), rs.getString("TYPEOPRATION"), rs.getFloat("VALEUROPERATION"), rs.getDate("DATEOPERATION").toLocalDate()));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return liste;
    }
    
}
