package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class UserDataServlet
 */
public class UserDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserDataServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String dni=request.getParameter("dni");
		String genero=request.getParameter("genero");
		String idusuario=request.getParameter("idusuario");
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");
		String telefono=request.getParameter("telefono");
		String fecha=request.getParameter("fecha");
		response.getWriter().append("<H1>"+nombre+apellidos+dni+genero+idusuario+password+mail+telefono+fecha+"</H1>");
	}

}
