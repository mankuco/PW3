package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Usuario.UsuarioDTO;
import data.Usuario.UsuarioDAO;
import display.CustomerBean;

/**
 * Servlet implementation class cambiarContrasenaServlet
 */
@WebServlet("/cambiarContrasenaServlet")
public class cambiarContrasenaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public cambiarContrasenaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/mvc/view/cambiarContrasena.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				String contrasena = request.getParameter("contrasena");
				String nueva = request.getParameter("nueva");
				if (nueva.equals("")) {
					String errorMsg = "La contraseña no puede quedar vacía";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher rd = request.getRequestDispatcher("/errorPage.jsp");
					rd.forward(request, response);
				}
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				UsuarioDTO usuario = usuarioDAO.buscarUsuario(userBean.getEmail());
				if (usuario.getContrasena().equals(contrasena)) {
					usuario.setContrasena(nueva);
					usuarioDAO.modificarUsuario(usuario);
					String msg = "Contraseña actualizada";
					request.setAttribute("msg", msg);
					RequestDispatcher rd = request.getRequestDispatcher("/mvc/view/cambiarContrasena.jsp");
					rd.forward(request, response);
				}
				else {
					String errorMsg = "La contraseña actual es incorrecta";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher rd = request.getRequestDispatcher("/errorPage.jsp");
					rd.forward(request, response);
				}
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
		}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
