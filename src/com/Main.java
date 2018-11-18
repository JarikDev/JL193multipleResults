package com;

import javax.imageio.ImageIO;
import javax.swing.plaf.nimbus.State;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        String userName = "root";
        String password = "sql123";
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
      //  Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
             Statement stat = conn.createStatement()) {

            stat.execute ("drop table IF EXISTS Books");
            stat.executeUpdate("CREATE TABLE Books(id MEDIUMINT NOT NULL AUTO_INCREMENT,name CHAR(30) NOT NULL,dt DATE, PRIMARY KEY(id))");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('Inferno')");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('Solomon Kein')");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('Ivan Govnov')");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('Moja borba')");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('Voina i mir')");
            stat.executeUpdate("INSERT INTO Books(name)VALUES ('KZOT')");

            CallableStatement cS=conn.prepareCall("{call getCount()}");
            boolean hasResults=cS.execute();
            while (hasResults){
                ResultSet resultSet=cS.getResultSet();
               while (resultSet.next()){
                   System.out.println(resultSet.getInt(1));
               }
               hasResults=cS.getMoreResults();
            }

            }
        }
    }



// CREATE TABLE IF NOT EXISTS Book (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, PRIMARY KEY (id))























