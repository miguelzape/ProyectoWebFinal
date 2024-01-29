package proyectoFinal;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import proyectoFinal.Daos.RolDao;
import proyectoFinal.Daos.UserDao;
import proyectoFinal.entities.Rol;
import proyectoFinal.entities.User;
import proyectoFinal.utils.EnumColor;

public class Principal {

	public static void main(String[] args) {
		
		
		String parametro =  (args.length>0)?args[0]:"";
		Principal p = new Principal();
		p.hacerTodo();

	}

	public void hacerTodo() {

		this.ReadProperties();

		UserDao udao = new UserDao();
		RolDao rdao = new RolDao();

		Rol admin = new Rol("Administrador");
		Rol oper = new Rol("Operador");

		User u1 = new User("user", "clave", "nombre", "apellidos", "dni", "V", "user@dominio.es", 916050000, new Date(),
				admin);
		User u2 = new User("user2", "clave2", "nombre2", "apellidos2", "dni2", "V", "user2@dominio.es", 916050002,
				new Date(), oper);

//		rdao.putRol(admin);
//		rdao.putRol(oper);
//		udao.putUser(u1);
//		udao.putUser(u2);

		
		boolean userValido = udao.existUsuario("user");
		System.out.println(EnumColor.BLUE + "existe " + userValido);
		System.out.println(EnumColor.WHITE+ "");

		boolean r1 = udao.existUsuario("paco");
		long r2 = udao.validarUser("user", "clave");

		udao.close();
		rdao.close();
	}

	void ReadProperties() {
		try {
			InputStream archivo = new FileInputStream("./src/main/resources/mysql.properties");

			Properties p = new Properties();
			p.load(archivo);
			
			String a = p.getProperty("TypeDataBase");
			String b = p.getProperty("user");
			String c = p.getProperty("password");
			
			System.out.println(a+" "+b+" "+c);

			
		} catch (Exception e) {
			System.err.println("no existe el archivo = " + e);
			System.exit(1);
		}
	}

}
