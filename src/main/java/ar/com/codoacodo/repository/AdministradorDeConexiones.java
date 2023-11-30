package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class AdministradorDeConexiones {
    public Connection getConnection() {
        String username = "root";
        String password = "Movistar,.73";
        String port = "3307";
        String host = "localhost";
        String dbName = "integrador_cac";

        String diverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?allowPublicKeyRetrieval=true&serverTimeZone=UTC&useSSL=false";
        try {
            Class.forName(diverName);
            return DriverManager.getConnection(url, username, password); 
        } catch (Exception e) {
            throw new IllegalArgumentException("no se pudo conectar a la db: " + url);
        }
    }
}
