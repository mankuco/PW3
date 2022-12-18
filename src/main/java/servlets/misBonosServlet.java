package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Reserva.*;
import data.Reserva.*;
import display.CustomerBean;

/**
 * Servlet implementation class misBonosServlet
 */
@WebServlet("/misBonosServlet")
public class misBonosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public misBonosServlet() {
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
				GestorReservas gestor = new GestorReservas();
				ArrayList<BonoReservaDTO> bonos = null;
				if(userBean.getRol() == false) {
					bonos =  gestor.listarBonos(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				}
				else {
					bonos =  gestor.listarBonosUsuario(userBean.getEmail(), userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				}
				request.setAttribute("verBonos", bonos);
				RequestDispatcher rd = request.getRequestDispatcher("./mvc/view/Bonos.jsp");
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
