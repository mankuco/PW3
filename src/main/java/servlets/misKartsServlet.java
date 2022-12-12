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

import business.Pista_Kart.KartDTO;
import business.Pista_Kart.PistaDTO;
import business.Reserva.Reserva;
import business.Reserva.ReservaDTO;
import business.Usuario.UsuarioDTO;
import display.CustomerBean;
import data.Reserva.ReservaDAO;
import data.Usuario.UsuarioDAO;
import data.Pista_Kart.*;

/**
 * Servlet implementation mis Karts
 */
@WebServlet("/misKartsServlet")
public class misKartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public misKartsServlet() {
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
				
				KartDAO KartDAO = new KartDAO();
				ArrayList<KartDTO> karts =  KartDAO.listartodoskart();
				
				request.setAttribute("verPistas", karts);
				RequestDispatcher rd = request.getRequestDispatcher("./mvc/view/Karts.jsp");
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
