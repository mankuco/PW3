package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Pista_Kart.Dificultades;
import business.Pista_Kart.GestorPistas;
import business.Pista_Kart.PistaDTO;
import data.Pista_Kart.PistaDAO;
import display.CustomerBean;

/**
 * Servlet implementation class editarPistaServlet
 */
@WebServlet("/editarPistaServlet")
public class editarPistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarPistaServlet() {
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
				
			    String nombrePista = request.getParameter("id");
				request.setAttribute("nombrePista", nombrePista);
				RequestDispatcher rd = request.getRequestDispatcher("/mvc/view/editarPista.jsp");
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
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");		
		if (userBean != null) {
				if (userBean.getEmail() != null) {
			String nombrePista = request.getParameter("pistaName");
			String estado = request.getParameter("pistaEstado");
			Boolean.parseBoolean(estado);
			PistaDAO pistas = new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
			pistas.cambiaEstado(nombrePista,estado);
			
			String msg = "Pista editada correctamente";
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher("/misPistasServlet");
			rd.forward(request, response);
		}
		}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}
}
