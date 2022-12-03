package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.Usuario.*;
import business.Usuario.*;
import display.CustomerBean;

/**
 * Servlet implementation class usuarioServlet
 */
@WebServlet("/usuarioServlet")
public class usuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public usuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				UsuarioDAO usuario = new UsuarioDAO();
				UsuarioDTO user = usuario.buscarUsuario(userBean.getEmail());
				
				UsuarioDAO DAO = new UsuarioDAO();
				ArrayList<UsuarioDTO> lusuarios = DAO.listarUsuarios();
				
				request.setAttribute("listaUsuarios", lusuarios);
				
				RequestDispatcher rd;
				if(user.getRol()==true) {
					rd = request.getRequestDispatcher("/MVC/View/pAdmin.jsp");
				}
				else {
					rd = request.getRequestDispatcher("/MVC/View/pCliente.jsp");
				}
				rd.forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
		}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
