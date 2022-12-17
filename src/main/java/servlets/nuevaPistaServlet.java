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
import business.Pista_Kart.Dificultades;

import java.time.*;

/**
 * Servlet implementation class nuevaPista
 */
@WebServlet("/nuevaPista")
public class nuevaPistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nuevaPistaServlet() {
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
			String dificultad = request.getParameter("pistaType");
			String maxkarts = request.getParameter("maxKarts");
			Dificultades dificultades = Dificultades.valueOf(dificultad);
			

			PistaDAO pistaDAO = new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
			PistaDTO pista = new PistaDTO();
			
			pista.setNombrePista(nombrePista);
			pista.setDificultad(dificultades);
			pista.setMaxKarts(Integer.parseInt(maxkarts));
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
