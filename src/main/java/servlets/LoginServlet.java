package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoFinal.Daos.UserDao;
import proyectoFinal.entities.User;
import proyectoFinal.enums.EnumUsuarios;
import proyectoFinal.utils.Propiedades;
import proyectoFinal.utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, User> sesiones=new HashMap<>();
	private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    // Constructor vacio
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioSesion = request.getSession().getId();
		if (!sesiones.keySet().contains(usuarioSesion)){
			response.getWriter().append("<H1>Accion no permitida para usuarios no logeados</H1>");
		}
		else {	
			String accion=request.getParameter("accion");
			if (accion.equalsIgnoreCase("borrar")) {
				borrar (request, response);
			}
			else if (accion.equalsIgnoreCase("ordenar")) {
				ordenar (request, response);
			}
			else if (accion.equalsIgnoreCase("filtrar")) {
				filtrar (request, response);
			}
			else if (accion.equalsIgnoreCase("logout")) {
				logOut (request, response);
			}
			else if (accion.equalsIgnoreCase("contenido")) {
				mostrarContenido (request, response);
			}
			else {
				response.getWriter().append("<H1>El metodo doGet ha recibido una accion inesperada</H1>");
			}
		}
		
	}
	
	// necesita el parameter 'id' (String) con el identificador del usuario que se quiere borrar
	// devuelve a 'tablaUsers.jsp'
	//	      el parameter 'listaUsuarios' (List<User>) con una lista de usuarios.
	private void borrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
		String Cadenaid=request.getParameter("id");
		Long id=Long.parseLong(Cadenaid);
		//System.out.println("El id que se quiere borrar es "+id);
		UserDao udao= new UserDao();
		udao.deleteUser(id);
		request.setAttribute("listaUsuarios", udao.getUsers());
		RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
		rd.forward(request, response);
			
	}
		
	// necesita el paremeter 'orden' (String) Es el campo por el que se ordena + (opcional) " ASC" o " DESC" 
	// devuelve a 'tablaUsers.jsp'
	//	      el parameter 'listaUsuarios' (List<User>) con una lista de usuarios ordenados.
	//	      el parameter 'sentido' (String) ' ASC' o ' DESC' que es el proximo sentido en caso de reordenar por el mismo campo
	//	      el parameter 'anterior' (String) Es el campo por el que acaba de ordenar 
	private void ordenar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orden=request.getParameter("orden")!=null?request.getParameter("orden"):"nombre";
		String sentido = orden.contains("ASC") ? " DESC" : " ASC";
		int pos = orden.indexOf(" ");
		
		String anterior=(pos>0)?orden.substring(0, pos):orden; 
		
		String traza = "Se recibe ordenar por: " + anterior + " en orden contrario a"+sentido;
		logger.trace(traza);

		UserDao udao= new UserDao();
		request.setAttribute("listaUsuarios", udao.getUsersOrdenados(orden));
		RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp?sentido="+sentido+"&anterior="+anterior);
		rd.forward(request, response);
	}
	
	// necesita el paremeter 'campo' (String) Es el campo por el que se filtra
	// necesita el paremeter 'valor' (String) Es el valor que se busca en el campo anterior
	// devuelve a 'tablaUsers.jsp'
	//	      el parameter 'listaUsuarios' (List<User>) con una lista de usuarios.
	private void filtrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String campo=request.getParameter("campo")!=null?request.getParameter("campo"):"";
		String valor=request.getParameter("valor")!=null?request.getParameter("valor"):"";
		response.getWriter().append("<H1>Filtrado. campo= "+campo+" valor= "+valor+"</H1>");
		
	}
	
	
	// no necesita parameters
	// si la sesion actual esta registrada la elimina y abre 'index.jsp' sin parameters
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// cogo el id de la sesion actual
		String usuarioSesion = request.getSession().getId();
		// borro del mapa de sesiones los datos
		sesiones.remove(usuarioSesion);
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//HttpSession sesion = request.getSession();
		String accion=request.getParameter("accion");
		if (accion == null) {
			response.getWriter().append("<H1>Llamada a LoginServlet incompleta</H1>");
		} 
		else if (accion.equalsIgnoreCase("login")) {
			logIn (request, response);
		}
		else if (accion.equalsIgnoreCase("nuevo")) {
			nuevo (request, response);
		}
		else if (accion.equalsIgnoreCase("modificar")) {
			modificar (request, response);
		}
		
		else {
			response.getWriter().append("<H1>El metodo doPost ha recibido una accion inesperada</H1>");
		}
				
	}
	
	// se llama desde 'tablaUsers.jsp' si se pulsa el enlace 'volver a contenido'
	//  y desde los metodos 'login' y 'modificar'
	// devuelve a 'contenido.jsp'
	//     el parameter 'Usuario' (User) con el usuario de la sesion actual.
	private void mostrarContenido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String usuarioSesion = request.getSession().getId();
		User u = sesiones.get(usuarioSesion);
		
		request.setAttribute("Usuario", u);
		RequestDispatcher rd = request.getRequestDispatcher("contenido.jsp");
		rd.forward(request, response);
	}
	

	// necesita el paremeter 'userCaja' (String) Con el usuario (el nombre de usuario es otra cosa)
	// necesita el paremeter 'passWordCaja' (String) Con la clave
	// si los datos ESTAN registrados devuelve a 'contenido.jsp'
	//	      el parameter 'Usuario' (User) con el usuario de la sesion actual.
	// si los datos NO ESTAN registrados devuelve a 'index.jsp'
	//	      el parameter 'msg' (String) con el texto 'Credenciales incorrectas'
	private void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		if (request.getParameter("userCaja")!=null && request.getParameter("passwordCaja")!=null) {
			String userCaja=request.getParameter("userCaja");
			String passWordCaja=request.getParameter("passwordCaja");
			UserDao udao= new UserDao();
			User usuario = udao.validarUser (userCaja, passWordCaja);
			
			if (usuario != null) {
				//guardar la id de sesion para verificar accesos a los metodos
				String usuarioSesion = request.getSession().getId();
				if (!sesiones.keySet().contains(usuarioSesion)) {
					sesiones.put(usuarioSesion, usuario);
				}
				mostrarContenido(request, response);
			}
			else
			{
				//mostrar la pantalla de inicio con mensaje de error
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?msg=Credenciales incorrectas");
				rd.forward(request, response);
			}
			
		}else {
			response.getWriter().append("<H1>Se necesita usuario y clave</H1>");
		}
		
	}
	
	
	// recoge los paremeters con 'request.getParameter'para crear un objeto User
	// devuelve el objeto User con los campos rellenos
	private User rellenaDatosUser(HttpServletRequest request) {
		String nombre=request.getParameter("nombre")!=null?request.getParameter("nombre"):"";
		String apellidos=request.getParameter("apellidos")!=null?request.getParameter("apellidos"):"";
		String dni=request.getParameter("dni")!=null?request.getParameter("dni"):"";
		String genero=request.getParameter("genero")!=null?request.getParameter("genero"):"";
		String idusuario=request.getParameter("idusuario")!=null?request.getParameter("idusuario"):"";
		String clave=request.getParameter("password")!=null?request.getParameter("password"):"";
		String mail=request.getParameter("mail")!=null?request.getParameter("mail"):"";
		String telefono=request.getParameter("telefono")!=null?request.getParameter("telefono"):"";
		long tele = Long.parseLong(telefono);
		String fecha=request.getParameter("fecha")!=null?request.getParameter("fecha"):"";
		String rol=request.getParameter("tipo")!=null?request.getParameter("tipo"):"";
		// 1985-01-03 año-mes-dia. asi se recibe
		// System.out.println("la fecha recibida es= "+fecha); 
		Date f=Utils.string2ToDate(fecha);
		
		User u = new User(idusuario,clave,nombre,apellidos,dni,genero,mail,tele,f,rol);
		return u;
	}
	
	// necesita todos los parameters que se envian desde el formulario de 'userdata.jsp'
	// si el 'usuario' ESTA registrado, devuelve a 'userdata.jsp'
	//	      todos los parameters que se recibieron del formulario de 'userdata.jsp'    
	//	      el parameter 'msg' (String) con el texto 'Ya existe el usuario '+usuario.
	// si el 'usuario' NO ESTA registrado, devuelve a 'tablaUsers.jsp'
	//	      el parameter 'listaUsuarios' (List<User>) con la lista de usuarios.
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
					
	User u = rellenaDatosUser(request);
	String fecha = Utils.dateToString(u.getNacimiento());
	
	//response.getWriter().append("<H1>"+nombre+apellidos+dni+genero+idusuario+clave+mail+telefono+fecha+"</H1>");
	
	UserDao udao= new UserDao();
	
	String enlace;
	if (udao.existUsuario(u.getUsuario())) {
		enlace ="userdata.jsp?usuario=\"\"&nombre="+u.getNombre()+"&apellidos="+u.getApellidos()+
				"&dni="+u.getDni()+"&genero="+u.getSexo()+"&mail="+u.getEmail()+
				"&telefono="+u.getTelefono()+"&nacimiento="+fecha+"&rol="+u.getRol()+
				"&clave="+u.getClave()+"&msg=Ya existe el usuario " + u.getUsuario();	
	}
	
	else {
		udao.putUser(u);	
		request.setAttribute("listaUsuarios", udao.getUsers());
		enlace="tablaUsers.jsp"; 
	}
	logger.trace(enlace);
	RequestDispatcher rd = request.getRequestDispatcher(enlace);
	rd.forward(request, response);
	}
	
	
	// necesita todos los parameters que se envian desde el formulario de 'userdata.jsp'
	// necesita el paremeter 'retorno' (String) para saber a que JSP hay que llamar al final
	// actualiza el usuario recibido por parameters en la base de datos y en el mapa sesiones
	// si 'retorno' ES IGUA A 'contenido', se devuelve a 'contenido.jsp'
	//			el parameter 'Usuario' (User) con el usuario de la sesion actual.
	// si 'retorno' ES DISTINTO DE 'contenido', se devuelve a 'tablaUsers.jsp'
	//	      el parameter 'listaUsuarios' (List<User>) con la lista de usuarios.

	private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		// creo un usuario con los valores de los parametros recibidos
		User nuevo = rellenaDatosUser(request);
	    String retorno = request.getParameter("retorno")!=null?request.getParameter("retorno"):"ninguno";
		
		// leo los datos de usuario actual desde el mapa de sesiones
		String usuarioSesion = request.getSession().getId();
		User actual = sesiones.get(usuarioSesion);
		
		//  Si el usuario nuevo(modificado) es el de la sesion actual. se actualiza en el mapa de sesiones
		if (nuevo.getUsuario()==actual.getUsuario()) {
			sesiones.put(usuarioSesion, nuevo);
		}
					
		// leo el parametro con el id recibido
		String idString=request.getParameter("id"); 
		long id=Long.parseLong(idString);
		
		//se lee el usuario de la base de datos con ese id, para poder actualizarlo
		UserDao udao= new UserDao();
		User viejo = udao.getUser(id);
		
		//sustituyo los datos del usuario leido de la base de datos, por los recibidos en parametros
		viejo.setApellidos(nuevo.getApellidos());
		viejo.setClave(nuevo.getClave());
		viejo.setDni(nuevo.getDni());
		viejo.setEmail(nuevo.getEmail());
		viejo.setNacimiento(nuevo.getNacimiento());
		viejo.setNombre(nuevo.getNombre());
		viejo.setRol(nuevo.getRol());
		viejo.setSexo(nuevo.getSexo());
		viejo.setTelefono(nuevo.getTelefono());
		
		// actualizo la base de datos con el usuario modificado
		udao.editUser(viejo);
		
		// abrir la tabla de usuarios para verla con los datos actualizados
		if (retorno.equalsIgnoreCase("contenido")) {
			// ir a contenido pasandolo solo el usuario actual
			mostrarContenido(request, response);	
		}
		else {
			// ir a la tabla de usarios pasandole la lista completa de usuario
			request.setAttribute("listaUsuarios", udao.getUsers());
			RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
			rd.forward(request, response);
		}
	}
	

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		//Cargar las propiedades y pasarlo a memoria para leerlo mas rapido desde cualquier parte
		Propiedades pro = new Propiedades();
		pro.cargarProperties();
		
		//Crer la conexion con la base de datos
		UserDao udao = new UserDao();
	
		//comprobar si los usuarios del EnumUsarios estan en la base de datos
		//y si no estan se añaden aqui.
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
	}
		

}
