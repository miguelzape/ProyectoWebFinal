package proyectoFinal;

import java.util.Arrays;
import java.util.Date;

import proyectoFinal.Daos.UserDao;
import proyectoFinal.entities.Rol;
import proyectoFinal.entities.User;

public class Principal {

	public static void main(String[] args) {
		
		Principal p = new Principal();
		p.hacerTodo();

	}
	
	public void hacerTodo() {
		
		UserDao udao= new UserDao();
		
		Rol admin=new Rol("Administrador");
		Rol oper=new Rol("Operador");
		
		
		User u1 = new User("user","clave","nombre","apellidos","dni","V","user@dominio.es",
				916050000,new Date(),Arrays.asList(admin));
		User u2 = new User("user2","clave2","nombre2","apellidos2","dni2","V","user2@dominio.es",
				916050002,new Date(),Arrays.asList(oper));
		
		udao.putUser(u1);
		udao.putUser(u2);
		
	
		for (User u: udao.getUsers()) {
			System.out.println(u.getIdUsuario());
		}
		
		boolean r1 = udao.existUsuario("paco");
		long r2 = udao.validarUser("user","clave");
		
		udao.close();
	}

}
