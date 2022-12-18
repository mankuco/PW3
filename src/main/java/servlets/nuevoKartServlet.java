package servlets;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import display.CustomerBean;
import business.Pista_Kart.Estados;
import business.Pista_Kart.GestorPistas;
import business.Pista_Kart.PistaDTO;
import business.Pista_Kart.KartDTO;

/**
 * Servlet implementation class nuevaPista
 */
@WebServlet("/nuevoKart")
public class nuevoKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nuevoKartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");		
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				String estado = request.getParameter("kartEstado");
				String tipo = request.getParameter("kartType");
				String pistaName = request.getParameter("pistaName");
				if (pistaName == "null") {
					pistaName = null;
				}
				Estados estados = Estados.valueOf(estado);
				GestorPistas karts = new GestorPistas();
				PistaDTO pista = karts.existePista(pistaName,userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				int idKart = karts.crearKart(Boolean.parseBoolean(tipo), estados, userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				KartDTO kart = karts.existeKart(idKart, userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				karts.asociarKartPista(kart, pista, userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				String msg = "Kart guardado correctamente";
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher("/misKartsServlet");
				rd.forward(request, response);
			}
		}
			else {
				response.sendRedirect(request.getContextPath());
		}
	}

}
