package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import proyectoFinal.Daos.UserDao;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("userCaja")!=null && request.getParameter("passwordCaja")!=null) {
			String userCaja=request.getParameter("userCaja");
			String passWordCaja=request.getParameter("passwordCaja");
			UserDao udao= new UserDao();
			long idEncontrado= udao.validarUser (userCaja, passWordCaja);
			
			//boolean userValido = udao.existUsuario(userCaja);
			//System.out.println("Usuario= "+userCaja+ " clave= "+passWordCaja+ " existe " + userValido);
			//System.out.println("el id del usario es= " + idEncontrado);
			
			if (idEncontrado > 0) {
				response.getWriter().append("<H1>Logeado correctamente</H1>");
			}
			else
			{
				response.getWriter().append("<H1>Credenciales no validas</H1>");
			}
			
			udao.close();
		}else {
			response.getWriter().append("<H1>Se necesita usuario y clave</H1>");
		}
	}

}
