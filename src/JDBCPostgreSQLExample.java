//package com.vertex.academy.databases;

//STEP 1. Import required packages
import javax.swing.plaf.SplitPaneUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostgreSQLExample {

    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "1769";
    static Connection connection = null;

    public static void main(String[] argv) throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        addClient();
        index();
    }

    public static void addClient() throws SQLException {

        PreparedStatement addClient =
                connection.prepareStatement("INSERT INTO CLIENTS VALUES (?, ?, ?, ?, ?, ?)");

        addClient.setInt(1, 4);
        addClient.setInt(2, 11);
        addClient.setString(3, "Dima");
        addClient.setString(4, "9 классов");
        addClient.setString(5, "царь");
        addClient.setInt(6, 1000);

        addClient.executeUpdate();
        }


    public static List<Client> index() throws SQLException {
        List<Client> clients = new ArrayList<>();

        Statement statement = connection.createStatement();
        String sql = "SELECT* FROM CLIENTS";
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Client client = new Client();

            client.setAge(resultSet.getInt("age"));
            client.setName(resultSet.getString("name"));
            client.setEducation(resultSet.getString("education"));
            client.setSocialSstatus(resultSet.getString("social_status"));
            client.setIncome(resultSet.getInt("income"));

            clients.add(client);
        }

        for (Client x : clients)
        {
            System.out.println(x.getAge());
        }
        return clients;
    }
}