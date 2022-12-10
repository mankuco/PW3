package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Usuario.UsuarioDTO;
import data.Usuario.UsuarioDAO;
import display.CustomerBean;

import java.time.*;

/**
 * Servlet implementation class editarPerfilServlet
 */
@WebServlet("/editarPerfilServlet")
public class editarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarPerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean == null || userBean.getEmail() != null) {
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");

			String fechaNacimiento = request.getParameter("fechaNacimiento");
			LocalDate fecha = LocalDate.parse(fechaNacimiento);
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			UsuarioDTO usuario = usuarioDAO.buscarUsuario(userBean.getEmail());
			
			usuario.setFechaNacimiento(fecha);
			usuario.setApellidos(apellidos);
			usuario.setNombre(nombre);
			
			usuarioDAO.modificarUsuario(usuario);
			
			userBean.setApellidos(apellidos);
			userBean.setNombre(nombre);
			userBean.setFechaNacimiento(fecha);
			
			String msg = "Perfil actualizado correctamente";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/mvc/view/editarPerfil.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
