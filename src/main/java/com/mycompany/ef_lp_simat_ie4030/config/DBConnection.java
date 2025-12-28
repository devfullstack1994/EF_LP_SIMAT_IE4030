package com.mycompany.ef_lp_simat_ie4030.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    //private static final String URL  = "jdbc:mysql://localhost:3306/bd_simat_ie4030?useSSL=false&serverTimezone=UTC";
    //private static final String USER = "root";
    //private static final String PASS = "Admin"; // <-- CAMBIA a tu clave real

    private static final String URL  = "jdbc:mysql://shuttle.proxy.rlwy.net:56993/bd_simat_ie4030?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "IpGeNatPTYgWhFUshflMUnPWXXfwTjDM"; // <-- CAMBIA a tu clave real
    public static Connection getConnection() {
        try {
            // MySQL driver se auto-carga con Maven, pero esto ayuda en algunos entornos:
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            System.out.println("ERROR DBConnection: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
