package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.Reserva.*;
import display.CustomerBean;


/**
 * Servlet implementation class nuevaPista
 */
@WebServlet("/nuevaReserva")
public class nuevaReservaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public nuevaReservaServlet() {
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
			String nombrePista = request.getParameter("pistaName");
			int minutosReserva = Integer.parseInt(request.getParameter("numeroNinos"));
			int numeroNinos = Integer.parseInt(request.getParameter("numeroAdultos"));
			int numeroAdultos = Integer.parseInt(request.getParameter("minutosReserva"));
			TipoReserva tipos = TipoReserva.valueOf(request.getParameter("reservaType"));
			boolean bono = Boolean.parseBoolean(request.getParameter("reservaBono"));
			LocalTime hora = LocalTime.parse(request.getParameter("horaReserva"));
			LocalDate fecha = LocalDate.parse(request.getParameter("fechaReserva"));
			String email = userBean.getEmail();
			GestorReservas reserva = new GestorReservas();
			reserva.crearReserva(email, minutosReserva, nombrePista, tipos, nombrePista, fecha, hora, numeroNinos, numeroAdultos, userBean.getprop(), userBean.getjdbc(), userBean.getdbuser(), userBean.getdbpass());
			String msg = "Reserva guardada correctamente";
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
