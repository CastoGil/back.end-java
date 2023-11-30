package ar.com.codoacodo.entity;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.Instant;

import ar.com.codoacodo.repository.AdministradorDeConexiones;
import ar.com.codoacodo.repository.MysqlOradorRepository;
import ar.com.codoacodo.repository.OradorRepository;
public class MainOrador {

	public static void main(String[] args) {
		AdministradorDeConexiones administradorDeConexiones = new AdministradorDeConexiones();
        Connection conexion = administradorDeConexiones.getConnection();
        OradorRepository oradorRepository = new MysqlOradorRepository(conexion);
        Orador nuevoOrador = new Orador("Pedro", "Antonio", "antonio@hotmail.com", "javascript",Timestamp.from(Instant.now()));

        
        oradorRepository.save(nuevoOrador);
        System.out.println("Orador guardado: " + nuevoOrador);
       
        
        System.out.println("Lista de todos los oradores:");
        oradorRepository.findAll().forEach(System.out::println);
        
        //cambiar el numero id para ir borrando o buscando
        
        int idBuscado = 40;
        Orador oradorRecuperado = oradorRepository.getById(idBuscado);
        
        if (oradorRecuperado != null) {
            System.out.println("Orador recuperado: " + oradorRecuperado);
        } else {
            System.out.println("No se pudo recuperar el orador.");
        }

        
        oradorRecuperado.setNombre("Mario");
        oradorRepository.update(oradorRecuperado);
        System.out.println("Orador actualizado: " + oradorRecuperado);

      
        int idAEliminar = oradorRecuperado.getId();
        oradorRepository.delete(idAEliminar);
        System.out.println("Orador eliminado con ID: " + idAEliminar);
    }
}