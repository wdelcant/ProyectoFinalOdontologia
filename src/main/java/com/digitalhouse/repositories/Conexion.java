package com.digitalhouse.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);
    public static java.sql.Connection getConexion() {

        try {
            //Obtener la conexión a la base
            java.sql.Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            connection.setAutoCommit(false);

            logger.info("Conexión exitosa a la base de datos");

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
