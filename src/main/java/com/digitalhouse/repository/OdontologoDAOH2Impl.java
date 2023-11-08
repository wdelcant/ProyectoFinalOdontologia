package com.digitalhouse.repository;

import com.digitalhouse.domain.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
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

            statement.execute("CREATE TABLE ODONTOLOGO(ID INT PRIMARY KEY, NOMBRE VARCHAR(255), APELLIDO VARCHAR(255), NUMEROMATRICULA INT);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registrar(Odontologo odontologo) throws Exception {
        Connection connection = getConexion();

        try {

            String insert = "INSERT INTO odontologo (ID,NOMBRE,APELLIDO,NUMEROMATRICULA) VALUES (?,?,?,?)";

            PreparedStatement sentencia = connection.prepareStatement(insert);

            sentencia.setInt(1, odontologo.getId());
            sentencia.setString(2, odontologo.getNombre());
            sentencia.setString(3, odontologo.getApellido());
            sentencia.setInt(4, odontologo.getNumeroMatricula());
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
    public void eliminar(int id) {

    }

    @Override
    public void buscarPorId(int id) {

    }

    @Override
    public List<Odontologo> listarOdontologos() {
        Connection connection = getConexion();
        List<Odontologo> odontologos = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGO");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Odontologo odontologo = crearObjetoOdontologo(resultSet);
                odontologos.add(odontologo);
            }

            logger.info("Listado de todos los Odontologos: " + odontologos);


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return odontologos;
    }

    @Override
    public Connection getConexion() {
        return Conexion.getConexion();
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultado) throws SQLException {

        int id = resultado.getInt("id");
        int numeroMatricula = resultado.getInt("numeroMatricula");
        String nombre = resultado.getString("nombre");
        String apellido = resultado.getString("apellido");

        return new Odontologo(id, numeroMatricula, nombre, apellido);
    }


}
