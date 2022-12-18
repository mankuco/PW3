package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Reserva.Reserva;
import business.Usuario.UsuarioDTO;
import display.CustomerBean;
import data.Pista_Kart.KartDAO;
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
				
				UsuarioDAO usuario = new UsuarioDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				UsuarioDTO user = usuario.buscarUsuario(userBean.getEmail());
				
				
				
				ReservaDAO ReservaDAO = new ReservaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				ArrayList<Reserva> reservasADM =  ReservaDAO.verReservas(null,null);
				ArrayList<Reserva> reservasUsuario= ReservaDAO.verReservasUsuario(userBean.getEmail(),null,null);
				
				request.setAttribute("verReservasADM", reservasADM);
				request.setAttribute("verReservasUsuario", reservasUsuario);
				RequestDispatcher rd = request.getRequestDispatcher("./mvc/view/Reservas.jsp");
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
			CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
				
				String fechaInicioStr = request.getParameter("fecha-inicio");
				String fechaFinStr = request.getParameter("fecha-fin");

				LocalDate fechaInicio = null;
				LocalDate fechaFin = null;

				if (fechaInicioStr != null && !fechaInicioStr.isEmpty()) {
				  fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ISO_DATE);
				}else { fechaInicio=null;}

				if (fechaFinStr != null && !fechaFinStr.isEmpty()) {
				  fechaFin = LocalDate.parse(fechaFinStr, DateTimeFormatter.ISO_DATE);
				}else{ fechaFin=null;}
				
				ReservaDAO ReservaDAO = new ReservaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
				ArrayList<Reserva> reservasADMFilt =  ReservaDAO.verReservasFiltradas(fechaInicio,fechaFin);
				ArrayList<Reserva> reservasUsuarioFilt= ReservaDAO.verReservasUsuario(userBean.getEmail(),fechaInicio,fechaFin);
				
				request.setAttribute("verReservasFiltradas", reservasADMFilt);
				request.setAttribute("verReservasFiltradasUsuario", reservasUsuarioFilt);
				RequestDispatcher rd = request.getRequestDispatcher("./mvc/view/Reservas.jsp");
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
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerBean userBean = (CustomerBean) request.getSession().getAttribute("userBean");
		if (userBean != null) {
			if (userBean.getEmail() != null) {
	    String ReservaID = request.getParameter("id");
	
	     ReservaDAO ReservaDAO = new ReservaDAO(userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
	     ReservaDAO.borraReserva(ReservaID);
	 
	      response.setStatus(HttpServletResponse.SC_OK);
	      response.getWriter().println("Reserva eliminada con éxito");
		}else {response.sendRedirect(request.getContextPath());}
		}else {response.sendRedirect(request.getContextPath());}
		}

}
