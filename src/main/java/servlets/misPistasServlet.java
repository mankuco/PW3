package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Pista_Kart.PistaDTO;
import display.CustomerBean;
import data.Pista_Kart.*;
import data.Reserva.ReservaDAO;

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
				
				PistaDAO PistaDAO = new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				
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
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
	    String pistaID = request.getParameter("id");
	
	     PistaDAO pistaDAO= new PistaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
	     pistaDAO.borraPista(pistaID);
	 
	      response.setStatus(HttpServletResponse.SC_OK);
	      response.getWriter().println("Reserva eliminada con éxito");
		}else {response.sendRedirect(request.getContextPath());}
		}else {response.sendRedirect(request.getContextPath());}
		}


}
