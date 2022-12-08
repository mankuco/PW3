package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import display.CustomerBean;
import business.Usuario.UsuarioDTO;
import data.Usuario.UsuarioDAO;
import servlets.*;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null && userBean.getEmail() == null) {
			if(userBean.getRol()==true) {
				response.sendRedirect("./mvc/view/pAdmin.jsp");
			}
			else {
				response.sendRedirect("./mvc/view/pCliente.jsp");
			}
		}
		else {
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if ((email == null) || (password == null) || email.equals("") || password.equals("")) {
			String errorMsg = "Complete los campos del login";
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher rd = request.getRequestDispatcher("/errorPage.jsp");
			rd.forward(request, response);
		}
		else {
			UsuarioDAO user = new UsuarioDAO();
			UsuarioDTO usuario = user.buscarUsuario(email);
			
			CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
			
			if(usuario != null) {
				if (usuario.getContrasena().equals(password)) {
									
					userBean.setEmail(usuario.getEmail());
					userBean.setNombre(usuario.getNombre());
					userBean.setApellidos(usuario.getApellidos());
					userBean.setContrasena(usuario.getContrasena());
					userBean.setRol(usuario.getRol());
					
					response.sendRedirect("usuarioServlet");
				}
				else {
					String errorMsg = "El usuario y/o la contrase�a no coinciden con nuestros registros";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher rd = request.getRequestDispatcher("/errorPage.jsp");
					rd.forward(request, response);
				}
			}
			else {
				String errorMsg = "El usuario y/o la contrase�a no coinciden con nuestros registros";
				request.setAttribute("errorMsg", errorMsg);
				RequestDispatcher rd = request.getRequestDispatcher("/errorPage.jsp");
				rd.forward(request, response);
			}
		}
		
	}

}
