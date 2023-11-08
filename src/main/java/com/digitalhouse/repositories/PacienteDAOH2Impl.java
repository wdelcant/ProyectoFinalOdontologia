package com.digitalhouse.repositories;

import com.digitalhouse.domain.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class PacienteDAOH2Impl implements PacienteDAOH2 {

    private final static Logger logger = LoggerFactory.getLogger(PacienteDAOH2Impl.class);

    @Override
    public void crearTablaPaciente() {
        try {

            Connection connection = getConexion();

            // Crear una sentencia SQL
            Statement statement = connection.createStatement();

            // Ejecutar una consulta SQL y obtener un ResultSet

            statement.execute("CREATE TABLE PACIENTE (\n" +
                    "    ID INT PRIMARY KEY,\n" +
                    "    NOMBRE VARCHAR(255),\n" +
                    "    APELLIDO VARCHAR(255),\n" +
                    "    DOMICILIO VARCHAR(255),\n" +
                    "    DNI VARCHAR(50),\n" +
                    "    FECHA_ALTA DATETIME\n" +
                    ");");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void registrar(Paciente paciente) throws Exception {
        Connection connection = getConexion();

        try {

            String insert = "INSERT INTO PACIENTE (ID,NOMBRE, APELLIDO, DOMICILIO, DNI, FECHA_ALTA) VALUES (?,?,?,?,?,?)";

            PreparedStatement sentencia = connection.prepareStatement(insert);

            sentencia.setInt(1, paciente.getId());
            sentencia.setString(2, paciente.getNombre());
            sentencia.setString(3, paciente.getApellido());
            sentencia.setString(4, paciente.getDomicilio());
            sentencia.setInt(5, paciente.getDni());
            sentencia.setDate(2, new java.sql.Date(paciente.getFechaAlta().getTime()));
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
            connection.rollback();

        } finally {
            connection.close();
        }

    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public void buscarPorId(Long id) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }


    @Override
    public Connection getConexion() {

        return Conexion.getConexion();
    }
}
