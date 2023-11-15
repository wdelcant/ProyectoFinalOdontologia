package com.digitalhouse.clinicaOdontologica.repository.impl;

import com.digitalhouse.clinicaOdontologica.domain.Odontologo;
import com.digitalhouse.clinicaOdontologica.repository.Conexion;
import com.digitalhouse.clinicaOdontologica.repository.OdontologoDAOH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOH2Impl extends Conexion implements OdontologoDAOH2 {
    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Odontologo registrar(Odontologo odontologo) {
        logger.debug("guardando un nuevo odontologo");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO odontologos(nombre,apellido,matricula) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                odontologo.setId(keys.getLong(1));

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Conexión a la Base de Datos
            connection = getConexion();


            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("UPDATE odontologos SET nombre = ?, apellido = ?,matricula = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,odontologo.getId());
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setInt(3, odontologo.getMatricula());
            preparedStatement.setLong(4, odontologo.getId());


            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        }
        return odontologo;

    }

    @Override
    public void eliminar(Long id) {
        logger.debug("Borrando odontologo con id : " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos

            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM odontologos where id = ?");
            preparedStatement.setLong(1, id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        }


    }

    @Override
    public Odontologo buscar(Long id) {
        logger.debug("Buscando al odontologo con id = " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Odontologo odontologo = null;
        try {

            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,matricula FROM odontologos where id = ?");
            preparedStatement.setLong(1, id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {

                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int matricula = result.getInt("matricula");

                odontologo = new Odontologo(id, nombre, apellido, matricula);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.debug("Buscando todos los odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM odontologos");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            //4 Obtener resultados
            while (result.next()) {
                Long id = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                int matricula = result.getInt("matricula");

                Odontologo odontologo = new Odontologo(id,nombre,apellido,matricula);
                odontologos.add(odontologo);
            }

            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        }

        return odontologos;
    }





}
