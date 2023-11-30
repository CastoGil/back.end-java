package ar.com.codoacodo.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;

import ar.com.codoacodo.AdministradorDeConexiones;
import ar.com.codoacodo.MysqlOradorRepository;
import ar.com.codoacodo.Orador;
import ar.com.codoacodo.OradorRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/api/orador")

public class NuevoOradorController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String apellido = req.getParameter("apellido");
		String email = req.getParameter("email");
		String tema = req.getParameter("tema");

		Orador nuevoOrador = new Orador(nombre, apellido, email, tema, Timestamp.from(Instant.now()));
		AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
		Connection conexion = administradorDeConexiones.getConnection();

		// Crear el repositorio con la conexión
		OradorRepository oradorRepository = new MysqlOradorRepository(conexion);

		oradorRepository.save(nuevoOrador);
		 resp.getWriter().write("Orador guardado exitosamente en la base de datos");
	}

}
