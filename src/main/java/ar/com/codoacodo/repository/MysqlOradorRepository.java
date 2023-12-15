package ar.com.codoacodo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.codoacodo.entity.Orador;

import java.sql.Timestamp;

public class MysqlOradorRepository implements OradorRepository {

	private Connection conexion;

	public MysqlOradorRepository(Connection conexion) {
		this.conexion = conexion;
	}

	public void save(Orador orador) {
    String query = "INSERT INTO oradores (nombre, apellido, email, tema, fecha_alta) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement statement = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, orador.getNombre());
        statement.setString(2, orador.getApellido());
        statement.setString(3, orador.getEmail());
        statement.setString(4, orador.getTema());
        statement.setTimestamp(5, orador.getFechaAlta());

        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas == 0) {
            throw new SQLException("Error al guardar el orador en la base de datos, no se generó ningún ID.");
        }
        try (ResultSet res = statement.getGeneratedKeys()) {
            if (res.next()) {
                int id = res.getInt(1);
                orador.setId(id);
            } else {
                throw new SQLException("Error al obtener el ID generado para el orador.");
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al guardar el orador en la base de datos", e);
    }
}

	public Orador getById(int id) {

		String query = "SELECT * FROM oradores WHERE id_orador = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return construirOradorDesdeResultSet(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener el orador desde la base de datos", e);
		}
		return null;
	}

	public List<Orador> findAll() {

		String query = "SELECT * FROM oradores";
		List<Orador> oradores = new ArrayList<>();
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Orador orador = construirOradorDesdeResultSet(resultSet);
				oradores.add(orador);
			}
		} catch (SQLException e) {
			throw new RuntimeException("Error al obtener la lista de oradores desde la base de datos", e);
		}
		return oradores;
	}

	public void update(Orador orador) {

		String query = "UPDATE oradores SET nombre = ?, apellido = ?, email = ?, tema = ?, fecha_alta = ? WHERE id_orador = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setString(1, orador.getNombre());
			statement.setString(2, orador.getApellido());
			statement.setString(3, orador.getEmail());
			statement.setString(4, orador.getTema());
			statement.setTimestamp(5, orador.getFechaAlta());
			statement.setInt(6, orador.getId());

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error al actualizar el orador en la base de datos", e);
		}
	}

	public void delete(int id) {

		String query = "DELETE FROM oradores WHERE id_orador = ?";
		try (PreparedStatement statement = conexion.prepareStatement(query)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Error al eliminar el orador en la base de datos", e);
		}
	}

	private Orador construirOradorDesdeResultSet(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id_orador");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String email = resultSet.getString("email");
		String tema = resultSet.getString("tema");
		Timestamp fechaAlta = resultSet.getTimestamp("fecha_alta");

		return new Orador(id, nombre, apellido, email, tema, fechaAlta);
	}
}
