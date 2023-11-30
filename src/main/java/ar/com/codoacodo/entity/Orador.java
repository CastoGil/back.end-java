package ar.com.codoacodo.entity;
import java.sql.Timestamp;

public class Orador {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String tema;
    private Timestamp fechaAlta;

    public Orador(String nombre, String apellido, String email, String tema, Timestamp fechaAlta) {
     
        init(nombre, apellido, email, tema, fechaAlta);
    }

    public Orador(int id, String nombre, String apellido, String email, String tema, Timestamp fechaAlta) {
        this.id = id;
        init(nombre, apellido, email, tema, fechaAlta);
    }

	public void init(String nombre, String apellido, String email, String tema, Timestamp fechaAlta) {
		this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tema = tema;
        this.fechaAlta = fechaAlta;
	}

	
    @Override
	public String toString() {
		return "Orador [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tema="
				+ tema + ", fechaAlta=" + fechaAlta + "]";
	}

	// Getters y setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
   
}
