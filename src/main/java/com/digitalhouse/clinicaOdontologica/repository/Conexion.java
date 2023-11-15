package com.digitalhouse.clinicaOdontologica.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Conexion {


    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/test";
    private final static String DB_URL_INIT = "jdbc:h2:tcp://localhost/~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    public Connection getConexion() {

        try {
            //Obtener la conexión a la base
            Class.forName("org.h2.Driver");
            Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Convierte un Date en un SQL Date
    public static java.sql.Date utilDateToSqlDate(Date utilDate){
        long timeInMilliSeconds = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
        return sqlDate;
    }


    //Convierte un Date en un SQL Date
    public static Timestamp toTimeStamp (LocalDateTime dateTime){

        return Timestamp.valueOf(dateTime);

    }

    public void iniciarBase() {

        try {

            //Obtener la conexión a la base
            Class.forName(DB_JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DB_URL_INIT, DB_USER, DB_PASSWORD);

            //connection.setAutoCommit(false);
            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
