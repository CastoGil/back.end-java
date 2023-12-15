package ar.com.codoacodo.controllers;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.com.codoacodo.entity.Orador;
import ar.com.codoacodo.repository.AdministradorDeConexiones;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
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
	     AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
	     Connection conexion = administradorDeConexiones.getConnection();
	     OradorRepository oradorRepository = new MysqlOradorRepository(conexion);

	     List<Orador> oradores = oradorRepository.findAll();

	    
	     ObjectMapper objectMapper = new ObjectMapper();
	     resp.getWriter().write(objectMapper.writeValueAsString(oradores));
	 }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
	    String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

	    
	    System.out.println("JSON recibido: " + json);

	  
	    ObjectMapper objectMapper = new ObjectMapper();
	    OradorRequest oradorRequest = objectMapper.readValue(json, OradorRequest.class);

	  
	    AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
	    Connection conexion = administradorDeConexiones.getConnection();
	    OradorRepository oradorRepository = new MysqlOradorRepository(conexion);
	    
	    Orador nuevoOrador = new Orador(
	            oradorRequest.getNombre(),
	            oradorRequest.getApellido(),
	            oradorRequest.getEmail(),
	            oradorRequest.getTema(),
	            Timestamp.from(Instant.now())
	    );

	    oradorRepository.save(nuevoOrador);

	    resp.getWriter().print(objectMapper.writeValueAsString(nuevoOrador));
	}
	 @Override
	    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     
		 	String id = req.getParameter("id");

	        
	        AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
	        Connection conexion = administradorDeConexiones.getConnection();
	        OradorRepository oradorRepository = new MysqlOradorRepository(conexion);

	        int oradorId = Integer.parseInt(id);
	        oradorRepository.delete(oradorId);

	        resp.getWriter().write("Orador eliminado exitosamente");
	    }
	
	 @Override
	 protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     String id = req.getParameter("id");
	     if (id != null) {
	        
	         String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

	         ObjectMapper objectMapper = new ObjectMapper();
	         OradorRequest oradorRequest = objectMapper.readValue(json, OradorRequest.class);

	        
	         AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
	         Connection conexion = administradorDeConexiones.getConnection();
	         OradorRepository oradorRepository = new MysqlOradorRepository(conexion);

	         int oradorId = Integer.parseInt(id);
	         Orador oradorExistente = oradorRepository.getById(oradorId);

	         
	         oradorExistente.setNombre(oradorRequest.getNombre());
	         oradorExistente.setApellido(oradorRequest.getApellido());
	         oradorExistente.setEmail(oradorRequest.getEmail());
	         oradorExistente.setTema(oradorRequest.getTema());

	         
	         oradorRepository.update(oradorExistente);

	         resp.getWriter().write(objectMapper.writeValueAsString(oradorExistente));
	     } else {
	         resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	         resp.getWriter().write("Se requiere un ID para la actualización.");
	     }
	 }

}
