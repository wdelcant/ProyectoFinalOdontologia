package com.digitalhouse.repository;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static java.sql.Connection getConexion() {

        try {
            //Obtener la conexión a la base
            java.sql.Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            connection.setAutoCommit(false);

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
