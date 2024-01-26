package proyectoFinal;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import proyectoFinal.Daos.UserDao;
import proyectoFinal.entities.Rol;
import proyectoFinal.entities.User;

public class Principal {

	public static void main(String[] args) {
		
		
		String parametro =  (args.length>0)?args[0]:"";
		Principal p = new Principal();
		p.hacerTodo();

	}

	public void hacerTodo() {

		this.ReadProperties();

		UserDao udao = new UserDao();

		Rol admin = new Rol("Administrador");
		Rol oper = new Rol("Operador");

		User u1 = new User("user", "clave", "nombre", "apellidos", "dni", "V", "user@dominio.es", 916050000, new Date(),
				admin);
		User u2 = new User("user2", "clave2", "nombre2", "apellidos2", "dni2", "V", "user2@dominio.es", 916050002,
				new Date(), oper);

		udao.putUser(u1);
		udao.putUser(u2);

		for (User u : udao.getUsers()) {
			System.out.println(u.getIdUsuario());
		}

		boolean r1 = udao.existUsuario("paco");
		long r2 = udao.validarUser("user", "clave");

		udao.close();
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
