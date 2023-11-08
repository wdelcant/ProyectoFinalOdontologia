package com.digitalhouse.repository;

import com.digitalhouse.domain.Odontologo;
import com.digitalhouse.domain.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2Impl implements PacienteDAOH2 {

    private final static Logger logger = LoggerFactory.getLogger(PacienteDAOH2Impl.class);

    @Override
    public void crearTablaPaciente() throws Exception{

        try {

            Connection connection = getConexion();

            // Crear una sentencia SQL
            Statement statement = connection.createStatement();

            // Ejecutar una consulta SQL y obtener un ResultSet

            statement.execute("CREATE TABLE PACIENTE(ID INT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), DOMICILIO VARCHAR(255), DNI INT, FECHA_ALTA DATETIME);");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registrar(Paciente paciente) throws Exception {
        Connection connection = getConexion();

        try {

            String insert = "INSERT INTO paciente (ID,NOMBRE,APELLIDO,DOMICILIO,DNI,FECHA_ALTA) VALUES (?,?,?,?,?,?)";

            PreparedStatement sentencia = connection.prepareStatement(insert);

            sentencia.setInt(1, paciente.getId());
            sentencia.setString(2, paciente.getNombre());
            sentencia.setString(3, paciente.getApellido());
            sentencia.setString(4, paciente.getDomicilio());
            sentencia.setInt(5, paciente.getDni());
            sentencia.setDate(6, new java.sql.Date(paciente.getFechaAlta().getTime()));
            sentencia.execute();

            logger.info(sentencia.toString());

            sentencia.close();

            connection.commit();

        } catch (SQLException ex) {
            logger.error("Error al registrar el paciente " + ex.getMessage());
            ex.printStackTrace();
            connection.rollback();

            throw new Exception(ex);

        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();

        } finally {
            connection.close();
        }

    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public void buscarPorId(int id) {

    }

    @Override
    public List<Paciente> listarPacientes() {
        Connection connection = getConexion();
        List<Paciente> pacientes = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM PACIENTE");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Paciente paciente = crearObjetoPaciente(resultSet);
                pacientes.add(paciente);
            }
            logger.info("Listado de todos los pacientes: " + pacientes);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return pacientes;
    }


    @Override
    public Connection getConexion() {

        return Conexion.getConexion();
    }

    private Paciente crearObjetoPaciente(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("id");
        String nombre = resultado.getString("nombre");
        String apellido = resultado.getString("apellido");
        String domicilio = resultado.getString("domicilio");
        int dni = resultado.getInt("dni");
        Date fechaAlta = resultado.getDate("fecha_alta");

        return new Paciente(id, nombre, apellido, domicilio, dni, fechaAlta);
    }

}
