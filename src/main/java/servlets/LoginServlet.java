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

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuarioSesion = request.getSession().getId();
		if (!sesiones.keySet().contains(usuarioSesion)){
			response.getWriter().append("<H1>Accion no permitida para usuarios no logeados</H1>");
		}
		else {	
			String accion=request.getParameter("accion");
			if (accion.equalsIgnoreCase("borrar")) {
				//System.out.println("recibido mensaje de borrar ");
				borrar (request, response);
			}
			else if (accion.equalsIgnoreCase("ordenar")) {
				ordenar (request, response);
			}
			else {
				response.getWriter().append("<H1>El metodo doGet ha recibido una accion inesperada</H1>");
			}
		}
		
	}
	
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
		
	private void ordenar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String orden=request.getParameter("orden")!=null?request.getParameter("orden"):"";
		String sentido = orden.contains("ASC")?" DESC":" ASC";
		int pos = orden.indexOf(" ");
		
		String anterior=(pos>0)?orden.substring(0, pos):"ninguno";
		
		String traza = "Se recibe ordenar por: " + anterior + " en orden contrario a"+sentido;
		logger.trace(traza);

		UserDao udao= new UserDao();
		request.setAttribute("listaUsuarios", udao.getUsersOrdenados(orden));
		RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp?sentido="+sentido+"&anterior="+anterior);
		rd.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//HttpSession sesion = request.getSession();
		String accion=request.getParameter("accion");
		if (accion == null) {
			response.getWriter().append("<H1>Llamada a LoginServlet incompleta</H1>");
		} 
		else if (accion.equalsIgnoreCase("login")) {
			login (request, response);
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
	
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
				
				request.setAttribute("listaUsuarios", udao.getUsers());
				RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
				rd.forward(request, response);
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
		System.out.println(enlace);
	}
	
	else {
		udao.putUser(u);	
		request.setAttribute("listaUsuarios", udao.getUsers());
		enlace="tablaUsers.jsp"; 
	}
	
	RequestDispatcher rd = request.getRequestDispatcher(enlace);
	rd.forward(request, response);
	
	
	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		// creo un usuario con los valores de los parametros recibidos
		User nuevo = rellenaDatosUser(request);
		
		// actualizar los datos de usuario en el mapa de sesiones
		String usuarioSesion = request.getSession().getId();
		sesiones.put(usuarioSesion, nuevo);
					
		// leo de la base de datos el usuario con el id recibido
				String idString=request.getParameter("id");
				long id=Long.parseLong(idString);
				//long id=nuevo.getIdUsuario();
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
		request.setAttribute("listaUsuarios", udao.getUsers());
		RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
		rd.forward(request, response);
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
