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
import business.Pista_Kart.Estados;
import business.Pista_Kart.GestorPistas;
import business.Pista_Kart.KartDTO;
import business.Pista_Kart.PistaDTO;
import data.Pista_Kart.PistaDAO;
import display.CustomerBean;

/**
 * Servlet implementation class editarPistaServlet
 */
@WebServlet("/editarKartServlet")
public class editarKartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editarKartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");		
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				String estado = request.getParameter("kartEstado");
				String tipo = request.getParameter("kartType");
				String pistaName = request.getParameter("pistaName");
				Estados estados = Estados.valueOf(estado);
				GestorPistas karts = new GestorPistas();
				String idKart = request.getParameter("idk");
				KartDTO kart = karts.existeKart(Integer.parseInt(idKart), userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				
				PistaDTO pista = karts.existePista(pistaName,userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
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
