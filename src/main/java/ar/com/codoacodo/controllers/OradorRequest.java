package ar.com.codoacodo.controllers;

public class OradorRequest {
	 	private String nombre;
	    private String apellido;
	    private String email;
	    private String tema;
	    
	    public OradorRequest() {
	    	
	    }

		public OradorRequest(String nombre, String apellido, String email, String tema) {
			super();
			this.nombre = nombre;
			this.apellido = apellido;
			this.email = email;
			this.tema = tema;
		}

		@Override
		public String toString() {
			return "OradorRequest [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", tema=" + tema
					+ "]";
		}

		public String getNombre() {
			return nombre;
		}

		public String getApellido() {
			return apellido;
		}

		public String getEmail() {
			return email;
		}

		public String getTema() {
			return tema;
		}

}
