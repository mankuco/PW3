package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase que gestiona la conexi�n a la base de datos
 */

public abstract class DAO {
	
	protected static Connection con = null;
	
	/**
	 * M�todo que obtiene las sentencias SQL 
	 */
	
	public static Properties getProps() {
		Properties prop = new Properties();
		
		InputStream inputStream;
		try {
			inputStream = new FileInputStream("/sql.properties");
			prop.load(inputStream);
		}
		catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		catch (Exception e2){
			e2.printStackTrace();
		}
		return prop;
    }
	
	/**
	 * M�todo que obtiene los par�metros para la conexi�n a la base de datos
	 */
	
	public static Properties getLoginProps() {
		Properties prop = new Properties();
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("/config.properties");
			prop.load(inputStream);
		}
		catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		catch (Exception e2){
			e2.printStackTrace();
		}
		return prop;
	}
	
	protected static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://oraclepr.uco.es:3306/i92curam","i92curam", "pw1234");
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