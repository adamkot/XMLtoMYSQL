package pl.kotus.xmltomysql.utils;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {

    private static int testSprawnosciPolaczenia() {
        int x = 1;
        try {
            String queryIloscPROMO = "select 1 from dual;";
            int i = 0;
            Statement stip = connection.createStatement();
            ResultSet rsip = stip.executeQuery(queryIloscPROMO);
            while (rsip.next()) {
                i = rsip.getInt("1");
            }
            rsip.close();
            stip.close();

        } catch (SQLException e) {
            x = 0;
        }
        return x;
    }
    private static Connection connection = null;

    public static Connection getConnection(String login, String password, String baza) {
        if (DatabaseConnector.connection == null) {
            //System.out.println("Nie ma jeszcze połączenia z bazą danych. Generuję nowe");
            initConnection(login, password, baza);
        } else {
            //System.out.println("Wykorzystanie istniejącej encji połączenia");
            int r = testSprawnosciPolaczenia();

            if (r == 0) {
                System.out.println("### DATABASE CONNECTOR: Błąd w połączeniu");
                try {
                    DatabaseConnector.connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
                }
                DatabaseConnector.initConnection(login, password, baza);

            } else {
                //System.out.println("nie ma błędu w połączeniu");
            }
        }
        return DatabaseConnector.connection;
    }

    private static void initConnection(String login, String password, String baza) {
        try {
            System.out.println("Inicjalizacja polaczenia....");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://188.116.4.117/" + baza + "?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=true";        //jdbc:mysql:///dbname?useUnicode=true&characterEncoding=utf-8
            String user = login;
            String pw = password;
            DatabaseConnector.connection
                    = DriverManager.getConnection(url, user, pw);
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println(" ### Błąd przy próbie połączenia z bazą danych: " + e.getMessage());
        }
    }

}
