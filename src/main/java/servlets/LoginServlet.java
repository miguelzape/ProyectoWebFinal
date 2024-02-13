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
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
			
			udao.close();
		}else {
			response.getWriter().append("<H1>Se necesita usuario y clave</H1>");
		}
		
	}

	
	
	private void nuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
				
				
	String nombre=request.getParameter("nombre");
	String apellidos=request.getParameter("apellidos");
	String dni=request.getParameter("dni");
	String genero=request.getParameter("genero");
	String idusuario=request.getParameter("idusuario");
	String clave=request.getParameter("password");
	String mail=request.getParameter("mail");
	String telefono=request.getParameter("telefono");
	long tele = Long.parseLong(telefono);
	String fecha=request.getParameter("fecha");
	String rol=request.getParameter("rol");
	// 1985-01-03 año-mes-dia. asi se recibe
	System.out.println("la fecha recibida es= "+fecha);
	Date f=Utils.string2ToDate(fecha);
	fecha = Utils.dateToString(f);
	
	response.getWriter().append("<H1>"+nombre+apellidos+dni+genero+idusuario+clave+mail+telefono+fecha+"</H1>");
	
	
	UserDao udao= new UserDao();
	
	String enlace;
	if (udao.existUsuario(idusuario)) {
		enlace ="userdata.jsp?usuario=\"\"&nombre="+nombre+"&apellidos="+apellidos+"&dni="+dni+"&genero="+genero+"&mail="+mail+"&telefono=0&nacimiento="+fecha+"&rol="+rol+"&clave="+clave+"&msg=Ya existe el usuario " + idusuario;
		System.out.println(enlace);
	}
	
	else {
		User usuario = new User(idusuario,clave,nombre,apellidos,dni,genero,mail,tele,f,rol);
		udao.putUser(usuario);	
		request.setAttribute("listaUsuarios", udao.getUsers());
		enlace="tablaUsers.jsp"; 
	}
	
	RequestDispatcher rd = request.getRequestDispatcher(enlace);
	rd.forward(request, response);
	
	udao.close();
	

	}
	
	
	private void modificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
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
	
	udao.close();

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
