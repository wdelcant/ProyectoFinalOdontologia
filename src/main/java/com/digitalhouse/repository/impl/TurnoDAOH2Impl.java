package com.digitalhouse.repository.impl;

import com.digitalhouse.domain.Odontologo;
import com.digitalhouse.domain.Paciente;
import com.digitalhouse.domain.Turno;
import com.digitalhouse.repository.Conexion;
import com.digitalhouse.repository.OdontologoDAOH2;
import com.digitalhouse.repository.PacienteDAOH2;
import com.digitalhouse.repository.TurnoDAOH2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAOH2Impl extends Conexion implements TurnoDAOH2 {


    private PacienteDAOH2 pacienteDAO = new PacienteDAOH2Impl() {
    };

    private OdontologoDAOH2 odontologoDAO = new OdontologoDAOH2Impl();

    public TurnoDAOH2Impl() {

    }

    @Override
    public Turno registrar(Turno turno) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();



            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("INSERT INTO turnos(paciente_id,odontologo_id,fecha_hora) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);

            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setLong(1, turno.getPaciente().getId());
            preparedStatement.setLong(2, turno.getOdontologo().getId());
            preparedStatement.setTimestamp(3, toTimeStamp(turno.getTurno()));

            //3 Ejecutar una sentencia SQL y obtener los ID que se autogeneraron en la base de datos
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            if(keys.next()){
                turno.setId(keys.getLong(1));
            }

            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return turno;
    }

    @Override
    public void eliminar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("DELETE FROM turnos where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public Turno buscar(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Turno turno = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM turnos where id = ?");
            preparedStatement.setLong(1,id);

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();



            //4 Obtener resultados
            while (result.next()) {
                Long idTurno = result.getLong("id");

                Long pacienteId = result.getLong("paciente_id");
                Long odontologoId = result.getLong("odontologo_id");
                Timestamp turnoFecha = result.getTimestamp("turno");
                //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
                Paciente paciente = pacienteDAO.buscar(pacienteId);
                Odontologo odontologo = odontologoDAO.buscar(odontologoId);

                turno = new Turno(idTurno, paciente, odontologo, turnoFecha.toLocalDateTime());
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return turno;
    }

    @Override
    public List<Turno> buscarTodos() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Turno> turnos = new ArrayList<>();
        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("SELECT *  FROM turnos");

            //3 Ejecutar una sentencia SQL
            ResultSet result = preparedStatement.executeQuery();

            //4 Obtener resultados
            //4 Obtener resultados
            while (result.next()) {
                Turno turno = crearTurno(result);
                turnos.add(turno);
            }

            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return turnos;
    }

    @Override
    public Turno actualizar(Turno turno) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            connection = getConexion();


            //2 Crear una sentencia especificando que el ID lo auto incrementa la base de datos y que nos devuelva esa Key es decir ID
            preparedStatement = connection.prepareStatement("UPDATE turnos SET turno = ?  WHERE id = ?");
            //No le vamos a pasar el ID ya que hicimos que fuera autoincremental en la base de datos
            //preparedStatement.setInt(1,paciente.getId());
            preparedStatement.setTimestamp(1, toTimeStamp(turno.getTurno()));
            preparedStatement.setLong(2, turno.getId());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();


            preparedStatement.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return turno;
    }

    private Turno crearTurno(ResultSet result) throws SQLException {

        Long idTurno = result.getLong("id");
        Long pacienteId = result.getLong("paciente_id");
        Long odontologoId = result.getLong("odontologo_id");
        Timestamp turnoFecha = result.getTimestamp("fecha_hora");
        //Con el domicilio_id traemos el domicilio de la tabla domicilio a traves de DAO de Domicilios
        Paciente paciente = pacienteDAO.buscar(pacienteId);
        Odontologo odontologo = odontologoDAO.buscar(odontologoId);

        Turno turno = new Turno(idTurno, paciente, odontologo, turnoFecha.toLocalDateTime());

        return turno;

    }
}
