package com.digitalhouse.clinicaOdontologica.repository.impl;

import com.digitalhouse.clinicaOdontologica.domain.Domicilio;
import com.digitalhouse.clinicaOdontologica.repository.Conexion;
import com.digitalhouse.clinicaOdontologica.repository.DomicilioDAOH2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2Impl extends Conexion implements DomicilioDAOH2 {

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER ="sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Domicilio registrar(Domicilio domicilio) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO domicilios(calle,numero,localidad,provincia) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,domicilio.getId());
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next())
                domicilio.setId(keys.getLong(1));

            preparedStatement.close();

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return domicilio;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM domicilios where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Domicilio buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Domicilio domicilio = null;
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,calle,numero,localidad,provincia FROM domicilios where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                Long idDomicilio = result.getLong("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");

                domicilio = new Domicilio(idDomicilio,calle,numero,localidad,provincia);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return domicilio;

    }

    @Override
    public List<Domicilio> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Domicilio> domicilios = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM domicilios");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                Long idDomicilio = result.getLong("id");
                String calle = result.getString("calle");
                String numero = result.getString("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");

                Domicilio domicilio = new Domicilio(idDomicilio,calle,numero,localidad,provincia);

                domicilios.add(domicilio);

            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return domicilios;
    }

    @Override
    public Domicilio actualizar(Domicilio domicilio) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            preparedStatement = connection.prepareStatement("UPDATE domicilios SET calle = ?, numero = ? ,localidad = ?, provincia = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,domicilio.getId());
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setString(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.setLong(5, domicilio.getId());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException throwables) {
            throw new Exception(throwables.getMessage());
        }
        return domicilio;
    }
}
