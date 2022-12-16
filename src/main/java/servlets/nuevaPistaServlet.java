package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Pista_Kart.PistaDTO;
import data.Pista_Kart.PistaDAO;
import display.CustomerBean;

import java.time.*;

/**
 * Servlet implementation class nuevaPista
 */
@WebServlet("/nuevaPista")
public class nuevaPista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nuevaPista() {
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
			String nombrePista = request.getParameter("nombrePista");
			String tipoEstado = request.getParameter("tipoEstado");
			String dificultad = request.getParameter("dificultad");
			String maxkarts = request.getParameter("maxkarts");

			
			PistaDAO pistaDAO = new PistaDAO();
			PistaDTO pista = new PistaDTO();
			
			pista.setNombrePista(nombrePista);
			pista.setTipoEstado(tipoEstado);
			pista.setDificultad(dificultad);
			pista.setMaxKarts(maxKarts);
			pistaDAO.guardarPista(pista);
			
			String msg = "Pista guardada correctamente";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/mvc/view/nuevaPista.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
