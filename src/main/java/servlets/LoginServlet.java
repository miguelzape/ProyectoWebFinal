package servlets;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import proyectoFinal.Daos.UserDao;
import proyectoFinal.entities.User;
import proyectoFinal.utils.Propiedades;
import proyectoFinal.utils.Utils;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		String accion=request.getParameter("accion");
		if (accion.equalsIgnoreCase("borrar")) {
			System.out.println("recibido mensaje de borrar ");
			borrar (request, response);
		}
		else {
			response.getWriter().append("<H1>El metodo doGet ha recibido una accion inesperada</H1>");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession sesion = request.getSession();
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
			long idEncontrado= udao.validarUser (userCaja, passWordCaja);
			
			//boolean userValido = udao.existUsuario(userCaja);
			//System.out.println("Usuario= "+userCaja+ " clave= "+passWordCaja+ " existe " + userValido);
			//System.out.println("el id del usario es= " + idEncontrado);
			
			if (idEncontrado > 0) {
				//response.getWriter().append("<H1>Logeado correctamente</H1>");
				request.setAttribute("listaUsuarios", udao.getUsers());
				RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
				rd.forward(request, response);
			}
			else
			{
				//response.getWriter().append("<H1>Credenciales no validas</H1>");
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
		//response.getWriter().append("<H1>No existe aun el metodo modificar de doPost</H1>");
		User nuevo = rellenaDatosUser(request);
		String idString=request.getParameter("id");
		long id=Long.parseLong(idString);
		UserDao udao= new UserDao();
		User viejo = udao.getUser(id);
		
		viejo.setApellidos(nuevo.getApellidos());
		viejo.setClave(nuevo.getClave());
		viejo.setDni(nuevo.getDni());
		viejo.setEmail(nuevo.getApellidos());
		
		
		
//		request.setAttribute("listaUsuarios", udao.getUsers());
//		RequestDispatcher rd = request.getRequestDispatcher("tablaUsers.jsp");
//		rd.forward(request, response);
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
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		//System.out.println(getServletContext().getRealPath("/"));
		Propiedades pro = new Propiedades();
		pro.cargarProperties();
	}
		

}
