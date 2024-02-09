package proyectoFinal.enums;

public enum EnumUsuarios {
	
	uno("user","clave","nombre", "apellidos", "dni", "V", "user@dominio.es", 916870000,"03-01-1985", "Admin"),
	dos("user2", "clave2", "nombre2", "apellidos2", "dni2", "M", "user2@dominio.es", 916151245,"21-08-2002", "User"),
	tres("juan", "terremoto", "Juan", "lopez", "123456T", "V", "user3@dominio.es", 916251245,"15-02-2005", "User");
	
	String usuario;
	String clave;
	String nombre;
	String apellidos;
	String dni;
	String genero;
	String email;
	long telefono;
	String fecha;  // dd-MM-yyyy
	String rol;
	
	private EnumUsuarios(String usuario, String clave, String nombre, String apellidos, String dni, String genero,
			String email, long telefono,String fecha,String rol) {
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.genero = genero;
		this.email = email;
		this.telefono = telefono;
		this.fecha = fecha;
		this.rol = rol;
	}
	

	public String getFecha() {
		return fecha;
	}

	public String getRol() {
		return rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getClave() {
		return clave;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getDni() {
		return dni;
	}

	public String getGenero() {
		return genero;
	}

	public String getEmail() {
		return email;
	}

	public long getTelefono() {
		return telefono;
	}
	
	
	
	
}
