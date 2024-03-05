package proyectoFinal;

import java.util.Date;

import proyectoFinal.daos.UserDao;
import proyectoFinal.entities.User;
import proyectoFinal.enums.EnumUsuarios;
import proyectoFinal.utils.EnumColor;
import proyectoFinal.utils.Propiedades;
import proyectoFinal.utils.Utils;

public class Principal {

	public static void main(String[] args) {
		
		
		String parametro =  (args.length>0)?args[0]:"";
		Principal p = new Principal();
		p.hacerTodo();

	}

	public void hacerTodo() {

	
		Propiedades pro = new Propiedades();
		pro.cargarProperties();
		
		//System.err.println("el valor para user es: " + pro.leerProper("user"));
		
		UserDao udao = new UserDao();
		//RolDao rdao = new RolDao();
		//Rol admin = new Rol("Administrador");
		//Rol oper = new Rol("Operador");


	   for (EnumUsuarios e: EnumUsuarios.values()) {
		   
		   boolean existeUsuario = udao.existUsuario(e.getUsuario());
		   if (!existeUsuario) {
			   // solo si no existe ya, se añade usuario a la base de datos
			   Date f =Utils.stringToDate(e.getFecha());
			   User usuario = new User(e.getUsuario(),e.getClave(),e.getNombre(),e.getApellidos(),
					   e.getDni(),e.getGenero(),e.getEmail(),e.getTelefono(),f,e.getRol());
			   udao.putUser(usuario);
		   }     
	   }
			
//		rdao.putRol(admin);
//		rdao.putRol(oper);

	
		boolean userValido = udao.existUsuario("user");
		System.out.println(EnumColor.BLUE + "existe " + userValido);
		System.out.println(EnumColor.WHITE+ "");

		boolean r1 = udao.existUsuario("paco");
		User r2 = udao.validarUser("user", "clave");

		udao.close();
		
	
		//rdao.close();
	}

}
