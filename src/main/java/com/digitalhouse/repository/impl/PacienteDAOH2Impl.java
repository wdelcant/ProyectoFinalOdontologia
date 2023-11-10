package com.digitalhouse.repository.impl;

import com.digitalhouse.domain.Domicilio;
import com.digitalhouse.domain.Paciente;
import com.digitalhouse.repository.Conexion;
import com.digitalhouse.repository.DomicilioDAOH2;
import com.digitalhouse.repository.PacienteDAOH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2Impl extends Conexion implements PacienteDAOH2 {

    private final static Logger logger = LoggerFactory.getLogger(PacienteDAOH2Impl.class);
    private DomicilioDAOH2 domicilioDAO = new DomicilioDAOH2Impl();

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    //con la instruccion INIT=RUNSCRIPT cuando se conecta a la base ejecuta el script de sql que esta en dicho archivo
    private final static String DB_URL = "jdbc:h2:tcp://localhost/~/test;INIT=RUNSCRIPT FROM 'create.sql'";
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";


    @Override
    public Paciente registrar(Paciente paciente) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();


            //Como primer paso primero debemos guardar el domicilio del paciente
            //ya que necesitamos el ID del domicilio que se generará en la base de datos para luego
            //insertar ese id en el campo domicilio_id de la tabla pacientes


            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO pacientes(nombre,apellido,dni,fecha_alta,domicilio_id) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            //Hay que convertir el Date en sql.Date ya que son dos clases diferentes en Java
            preparedStatement.setDate(4, utilDateToSqlDate(new java.util.Date()));
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id

            if (paciente.getDomicilio() != null) {

                Domicilio domicilio = domicilioDAO.registrar(paciente.getDomicilio());
                paciente.getDomicilio().setId(domicilio.getId());
                preparedStatement.setLong(5, paciente.getDomicilio().getId());
            } else {
                preparedStatement.setNull(5, Types.BIGINT);
            }

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next())
                paciente.setId(keys.getLong(1));

            preparedStatement.close();

        } catch (SQLException throwables) {
            throw new Exception("Error al Guardar al Paciente " + throwables.getMessage());
        }
        return paciente;

    }

    @Override
    public Paciente actualizar(Paciente paciente) throws Exception {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();


            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE pacientes SET nombre=?, apellido=?, dni=?, domicilio_id=?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setString(1, paciente.getNombre());
            preparedStatement.setString(2, paciente.getApellido());
            preparedStatement.setInt(3, paciente.getDni());
            //Hay que convertir el Date en sql.Date ya que son dos clases diferentes en Java
            //Tenemos que pasarle la clave foranea del ID del domicilio es decir el campo domicilio_id
            //Como primer paso actualizamos el domicilio del paciente
            if (paciente.getDomicilio() != null) {
                Domicilio domicilio = domicilioDAO.actualizar(paciente.getDomicilio());
                preparedStatement.setLong(4, paciente.getDomicilio().getId());
            } else {
                preparedStatement.setNull(4, Types.BIGINT);
            }


            preparedStatement.setLong(5, paciente.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException throwables) {
            throw new Exception(throwables);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return paciente;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM pacientes where id = ?");
            preparedStatement.setLong(1, id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


    @Override
    public Paciente buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Paciente paciente = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT id,nombre,apellido,dni,fecha_alta,domicilio_id  FROM pacientes where id = ?");
            preparedStatement.setLong(1, id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Integer dni = result.getInt("dni");
                Date fechaAlta = result.getDate("fecha_alta");
                Long idDomicilio = result.getLong("domicilio_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDAO.buscar(idDomicilio);
                paciente = new Paciente(idPaciente, nombre, apellido, dni, fechaAlta, domicilio);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM pacientes");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            //4 Obtener resultados
            while (result.next()) {
                Long idPaciente = result.getLong("id");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");
                Integer dni = result.getInt("dni");
                Date fechaAlta = result.getDate("fecha_alta");
                Long idDomicilio = result.getLong("domicilio_id");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Domicilio domicilio = domicilioDAO.buscar(idDomicilio);
                Paciente paciente = new Paciente(idPaciente, nombre, apellido, dni, fechaAlta, domicilio);
                pacientes.add(paciente);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }

        return pacientes;

    }
}
