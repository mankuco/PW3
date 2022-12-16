package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase que gestiona la conexion a la base de datos
 */

public abstract class DAO {
	
	protected static Connection con = null;
	protected static Properties prop;
	protected static String jdbc, dbuser, dbpass;
	
	/**
	 * Constructor 
	 */
	public DAO(Properties prop, String jdbc, String dbuser, String dbpass) {
		DAO.prop = prop;
		DAO.jdbc = jdbc;
		DAO.dbuser = dbuser;
		DAO.dbpass = dbpass;
	}
	
	protected static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(jdbc, dbuser, dbpass);
		} catch(SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}