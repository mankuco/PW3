package servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Pista_Kart.PistaDTO;
import business.Reserva.Reserva;
import business.Reserva.ReservaDTO;
import business.Usuario.UsuarioDTO;
import display.CustomerBean;
import data.Reserva.ReservaDAO;
import data.Usuario.UsuarioDAO;
import data.Pista_Kart.*;

/**
 * Servlet implementation mis Reservas
 */
@WebServlet("/misPistasServlet")
public class misPistasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public misPistasServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				ServletContext context = getServletContext();
				
				UsuarioDAO UsuarioDAO = new UsuarioDAO();
				UsuarioDTO user = UsuarioDAO.buscarUsuario(userBean.getEmail());
				
				PistaDAO PistaDAO = new PistaDAO();
				ArrayList<PistaDTO> pistas =  PistaDAO.verPistas();
				
				request.setAttribute("verPistas", pistas);
				RequestDispatcher rd = request.getRequestDispatcher("./mvc/view/Pistas.jsp");
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
		doGet(request, response);
	}

}
