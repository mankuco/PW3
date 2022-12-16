package data.Usuario;

import java.util.ArrayList;
import java.sql.*;
import java.util.Properties;

import business.Usuario.UsuarioDTO;
import data.*;

public class UsuarioDAO extends DAO {
	
	/**
	 * Constructor 
	 */
	public UsuarioDAO(Properties prop, String jdbc, String dbuser, String dbpass) {
		super(prop, jdbc, dbuser, dbpass);
	}
	
	public Properties getprop() {
		return prop;
	}
	public String getjdbc() {
		return jdbc;
	}
	public String getdbuser() {
		return dbuser;
	}
	public String getdbpass() {
		return dbpass;
	}
	
    /*
     * @Resumen Guarda en la base de datos un nuevo usuario
     */
    public void guardarUsuario(UsuarioDTO usuario) {
        try {
        	Connection con = getConnection();
        	PreparedStatement ps = con.prepareStatement(prop.getProperty("inserta-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
        	ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setDate(5, Date.valueOf(usuario.getFechaInscripcion()));
            ps.setString(6, usuario.getContrasena());
            ps.setBoolean(7, usuario.getRol());
            ps.executeUpdate();
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
    }

   
    //Modifica un usuario en la base de datos
    public void modificarUsuario(UsuarioDTO usuario) {
        try {
            Connection con = getConnection();
        	PreparedStatement ps = con.prepareStatement(prop.getProperty("modifica-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setDate(3, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setString(4, usuario.getContrasena());
            ps.setString(5, usuario.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
    }

    //Listar usuarios base de datos
    public ArrayList<UsuarioDTO> listarUsuarios() {
        ArrayList<UsuarioDTO> usuarios = new ArrayList<UsuarioDTO>();
        try {
        	Connection con = getConnection();
         	PreparedStatement ps = con.prepareStatement(prop.getProperty("lista-usuarios"), PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("contrasena"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getDate("fechaInscripcion").toLocalDate(), rs.getBoolean("rol")));
            }
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
        return usuarios;
    }

    //Buscar usuario base de datos
    public UsuarioDTO buscarUsuario(String email) {
    	UsuarioDTO usuario = null;
        try {
        	Connection con = getConnection();
         	PreparedStatement ps = con.prepareStatement(prop.getProperty("busca-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioDTO(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("contrasena"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getDate("fechaInscripcion").toLocalDate(), rs.getBoolean("rol"));
            }
        }
        catch (SQLException e) {
            close();
            System.out.println(e);
        }
        return usuario;
    }

    //Eliminar usuario base de datos
    
    public void eliminarUsuario(String email) {
        try {
        	 Connection con = getConnection();
         	PreparedStatement ps = con.prepareStatement(prop.getProperty("borra-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
    }
}
