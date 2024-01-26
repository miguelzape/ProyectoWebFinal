package proyectoFinal.enums;

public enum UsuariosEnum {
	
	uno("user","clave","nombre", "apellidos", "dni", "V", "user@dominio.es", 916050000),
	dos("user2", "clave2", "nombre2", "apellidos2", "dni2", "V", "user2@dominio.es", 916051245);
	
	
	String usuario;
	String clave;
	String nombre;
	String apellidos;
	String dni;
	String genero;
	String email;
	long telefono;
	
	private UsuariosEnum(String usuario, String clave, String nombre, String apellidos, String dni, String genero,
			String email, long telefono) {
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.genero = genero;
		this.email = email;
		this.telefono = telefono;
	}
}
