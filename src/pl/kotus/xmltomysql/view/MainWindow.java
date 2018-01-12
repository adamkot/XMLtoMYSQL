package pl.kotus.xmltomysql.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import pl.kotus.xmltomysql.utils.Save;
import pl.kotus.xmltomysql.utils.Semafor;

public class MainWindow extends JFrame implements ActionListener {

    JLabel label1;
    JLabel label2;
    JLabel label3;
    JTextField login;
    JTextField password;
    JTextField tableName;
    JTextField dataBase;
    JTextField ipAdress;
    static JProgressBar progress;
    JButton OkButton;
    JButton open;

    int check = 0;

    Semafor sm = new Semafor();
    //pobieranie rozmiaru ekranu aby okno logowania pojawiało się na samym środku ekranu
    Dimension monitorSize = Toolkit.getDefaultToolkit().getScreenSize();

    public MainWindow() {
        super();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setSize(290, 420);
        this.setLocation(monitorSize.width / 2 - this.getWidth() / 2, monitorSize.height / 2 - this.getHeight() / 2);

        label1 = new JLabel();
        this.add(label1);
        label1.setBounds(10, 10, 250, 50);
        label1.setText("<HTML><CENTER>Zapis z pliku EXCEL do bazy danych.<BR/>Uwaga! obecne dane z bazy danych zostaną zastąpione!<CENTER></HTML>");

        label2 = new JLabel();
        this.add(label2);
        label2.setText("<HTML><CENTER>Wybierz plik do wgrania:</CENTER></HTML>");
        label2.setBounds(10, 70, 250, 20);

        open = new JButton("Otwórz");
        this.add(open);
        open.setActionCommand("open");
        open.addActionListener(this);
        open.setBounds(10, 100, 250, 20);
        
        login = new JTextField("login");
        this.add(login);
        login.setBounds(10, 130, 250, 20);
        
        password = new JTextField("hasło");
        this.add(password);
        password.setBounds(10, 160, 250, 20);
        
        tableName = new JTextField("Nazwa tabeli");
        this.add(tableName);
        tableName.setBounds(10, 190, 250, 20);
        
        dataBase = new JTextField("Nazwa bazy danych");
        this.add(dataBase);
        dataBase.setBounds(10, 220, 250, 20);
        
        ipAdress = new JTextField("Adres IP");
        this.add(ipAdress);
        ipAdress.setBounds(10, 250, 250, 20);

        label2 = new JLabel();
        this.add(label2);
        label2.setText("<HTML><CENTER>następnie zaakceptuj</CENTER></HTML>");
        label2.setBounds(10, 280, 250, 20);

        OkButton = new JButton("OK");
        this.add(OkButton);
        OkButton.setActionCommand("ok");
        OkButton.addActionListener(this);
        OkButton.setBounds(10, 310, 250, 20);

        progress = new JProgressBar(0, 100);
        this.add(progress);
        progress.setBounds(10, 340, 250, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            if (check == 1) {
                sm.setLogin(login.getText());
                sm.setPassword(password.getText());
                sm.setBaza(dataBase.getText());
                sm.setTableName(tableName.getText());
                sm.setIpAdress(ipAdress.getText());
                new Thread((Runnable) new Save()).start();
            } else {
                JOptionPane.showMessageDialog(this, "Nie udało się wczytać pliku");
            }
        }
        if (e.getActionCommand().equals("open")) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                Semafor.setFile(fc.getSelectedFile());
                check = 1;
            }
        }
    }

    public static void setMaxProgress(int y) {
        progress.setMaximum(y);
    }

    public static void progress(int x) {
        progress.setValue(x);
    }
}
