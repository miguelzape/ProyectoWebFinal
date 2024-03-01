package proyectoFinal.enums;

public enum EnumUsuarios {
	
	uno("user","user","Pedro", "Martinez", "53000000L", "V", "pedrito@yahoo.es", 916870000,"03-01-1985", "User"),
	dos("admin", "admin", "Manuel", "Cuba", "00156478K", "V", "manuelcuba2@terra.es", 916151245,"21-08-2002", "Admin"),
	tres("sara78", "clave", "Sara", "Iturriaga", "123456T", "M", "sarita@hotmail.com", 926250045,"15-02-2005", "User"),
	cuatro("SantaTeresa", "clave", "Teresa", "Pastor", "52414550H", "V", "terepas@hotmail.com", 936781414,"25-03-1985", "User"),
	cinco("tomate", "clave", "Tomas", "Lopez", "51145110Z", "M", "tomatito@yahoo.com", 925201445,"31-12-1988", "Admin");
	
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
