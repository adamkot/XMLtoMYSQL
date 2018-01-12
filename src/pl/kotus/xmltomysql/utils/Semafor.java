package pl.kotus.xmltomysql.utils;

import java.io.File;

public class Semafor {

    private static String login = "";
    private static String password = "";
    private static String baza = "";
    private static String tableName = "";
    private static File file;
    private static String ipAdress = "";

    /**
     * @return the login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * @param aLogin the login to set
     */
    public static void setLogin(String aLogin) {
        login = aLogin;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the baza
     */
    public static String getBaza() {
        return baza;
    }

    /**
     * @param aBaza the baza to set
     */
    public static void setBaza(String aBaza) {
        baza = aBaza;
    }

    /**
     * @return the tableName
     */
    public static String getTableName() {
        return tableName;
    }

    /**
     * @param aTableName the tableName to set
     */
    public static void setTableName(String aTableName) {
        tableName = aTableName;
    }

    /**
     * @return the file
     */
    public static File getFile() {
        return file;
    }

    /**
     * @param aFile the file to set
     */
    public static void setFile(File aFile) {
        file = aFile;
    }

    /**
     * @return the ipAdress
     */
    public static String getIpAdress() {
        return ipAdress;
    }

    /**
     * @param aIpAdress the ipAdress to set
     */
    public static void setIpAdress(String aIpAdress) {
        ipAdress = aIpAdress;
    }
    

   
}
