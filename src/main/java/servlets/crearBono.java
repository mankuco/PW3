package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Reserva.*;
import display.CustomerBean;

/**
 * Servlet implementation class crearBono
 */
@WebServlet("/crearBono")
public class crearBono extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public crearBono() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null)
			if(userBean.getEmail() != null) {
				GestorReservas gestor = new GestorReservas();
				String dificultad = request.getParameter("bonoType");
				TipoReserva tipo = TipoReserva.ADULTOS;
				if(dificultad.equals("INFANTIL")) {
					tipo = TipoReserva.INFANTIL;
				}
				else if(dificultad.equals("FAMILIAR")) {
					tipo = TipoReserva.FAMILIAR;
				}
				RequestDispatcher rd;
				if(gestor.crearBono(null, null, null, null, null, tipo, null, userBean.getEmail(), userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass()) == true) {
					String msg = "Bono creado correctamente";
					request.setAttribute("msg", msg);
					rd = request.getRequestDispatcher("/misBonosServlet");
				}
				else {
					String msg = "Ya tiene un bono activo en este momento";
					request.setAttribute("msg", msg);
					rd = request.getRequestDispatcher("/misBonosServlet");
				}
				rd.forward(request, response);
			}
			else {
				response.sendRedirect(request.getContextPath());
			}
		else {
			response.sendRedirect(request.getContextPath());
		}
	}

}
