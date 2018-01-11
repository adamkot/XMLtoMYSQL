package pl.kotus.xmltomysql.utils;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import pl.kotus.xmltomysql.view.MainWindow;

public class Save implements Runnable {

    ArrayList error = new ArrayList();
    Semafor sm = new Semafor();

    @Override
    public void run() {
        save(sm.getLogin(), sm.getPassword(), sm.getBaza(), sm.getTableName(), sm.getFile());
    }

    public void save(String login, String password, String database, String table, File file) {
        String text = "";

        try {
            delete(login, password, database, table);
            WorkbookSettings ustawienia = new WorkbookSettings();
            ustawienia.setEncoding("Cp1250");
            Workbook skoroszyt = Workbook.getWorkbook(file, ustawienia);
            Sheet arkusz = skoroszyt.getSheet(0);

            int rows = arkusz.getRows();
            int cols = arkusz.getColumns();

            MainWindow.setMaxProgress(rows);
            int i = 1;
            String tmp = "";
            for (; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    Cell komorka = arkusz.getCell(j, i);
                    tmp = komorka.getContents().replace(',', '.');
                    text = text + "\"" + tmp + "\"";
                    if (j + 1 < cols) {
                        text = text + ", ";
                    }
                }
                System.out.println("Dodaje do bazy");
                System.out.println(text);
                String save = "insert into " + table + " values (" + text + ")";
                System.out.println("Zapis do bazy: " + save);
                text = "";
                MainWindow.progress(i);
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = DatabaseConnector.getConnection(login, password, database).prepareStatement(save);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Błąd w zapoisywaniu do bazy danych " + e);
                    error.add("Błąd w zapoisywaniu do bazy danych " + e + "\n");
                }
            }
            skoroszyt.close();
            JOptionPane.showMessageDialog(null, "Zakończono");
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }

        if (error.size() > 0) {
            JOptionPane.showMessageDialog(null, "Błąd" + error.toString());
        }
    }

    private void delete(String login, String password, String database, String table) {
        System.out.println("Usuwam w bazie");
        String del = "delete from " + table;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = DatabaseConnector.getConnection(login, password, database).prepareStatement(del);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Błąd w usuwaniu " + e);
        }
    }
}
