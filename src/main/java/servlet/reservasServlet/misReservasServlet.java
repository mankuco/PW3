package servlet.reservasServlet;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Reserva.Reserva;
import business.Reserva.ReservaDTO;
import business.Usuario.UsuarioDTO;
import display.CustomerBean;
import data.Reserva.ReservaDAO;
import data.Usuario.UsuarioDAO;

/**
 * Servlet implementation mis Reservas
 */
@WebServlet("/misReservasServlet")
public class misReservasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public misReservasServlet() {
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
				
				ReservaDAO ReservaDAO = new ReservaDAO();
				ArrayList<Reserva> reservasADM =  ReservaDAO.verReservas();
				ArrayList<Reserva> reservasUsuario =  ReservaDAO.verReservasUsuario(user.getEmail());
				
				request.setAttribute("verReservasADM", reservasADM);
				request.setAttribute("verReservasUsuario", reservasUsuario);
				RequestDispatcher rd = request.getRequestDispatcher("src/main/webapp/Reservas.jsp");
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
