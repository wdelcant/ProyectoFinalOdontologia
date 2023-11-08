package com.digitalhouse.repositories;

import com.digitalhouse.domain.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;


public class OdontologoDAOH2Impl implements OdontologoDAOH2 {
    private final static Logger logger = LoggerFactory.getLogger(OdontologoDAOH2Impl.class);

    @Override
    public void crearTablaOdontologo() throws Exception {

        try {

            Connection connection = getConexion();

            // Crear una sentencia SQL
            Statement statement = connection.createStatement();

            // Ejecutar una consulta SQL y obtener un ResultSet

            statement.execute("CREATE TABLE ODONTOLOGO(ID INT PRIMARY KEY,\n" +
                    "   NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), NUMEROMATRICULA INT);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registrar(Odontologo odontologo) throws Exception {
        Connection connection = getConexion();

        try {

            String insert = "INSERT INTO odontologo (ID,NUMEROMATRICULA,NOMBRE, APELLIDO) VALUES (?,?,?,?)";

            PreparedStatement sentencia = connection.prepareStatement(insert);

            sentencia.setInt(1, odontologo.getId());
            sentencia.setInt(2, odontologo.getNumeroMatricula());
            sentencia.setString(3, odontologo.getNombre());
            sentencia.setString(4, odontologo.getApellido());
            sentencia.execute();

            logger.info(sentencia.toString());

            sentencia.close();

            connection.commit();

        } catch (SQLException ex) {
            logger.error("Error al registrar el odotonlogo " + ex.getMessage());
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
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public void buscarPorId(Long id) {

    }

    @Override
    public List<Odontologo> listarTodos() {
        return null;
    }


    @Override
    public Connection getConexion() {
        return Conexion.getConexion();
    }

}
