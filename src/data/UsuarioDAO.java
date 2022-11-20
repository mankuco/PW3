package data;

import java.util.ArrayList;

import business.UsuarioDTO;

import java.sql.*;



public class UsuarioDAO extends DAO {

    /*
     * @Resumen Guarda en la base de datos un nuevo usuario
     */
    public void guardarUsuario(UsuarioDTO usuario) {
        try {
        	Connection con = getConnection();
        	PreparedStatement ps = con.prepareStatement(getProps().getProperty("inserta-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setDate(5, Date.valueOf(usuario.getFechaInscripcion()));
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
        	PreparedStatement ps = con.prepareStatement(getProps().getProperty("modifica-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getEmail());
            ps.setDate(4, Date.valueOf(usuario.getFechaNacimiento()));
            ps.setDate(5, Date.valueOf(usuario.getFechaInscripcion()));
            ps.setString(6, usuario.getEmail());
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
         	PreparedStatement ps = con.prepareStatement(getProps().getProperty("lista-usuarios"), PreparedStatement.RETURN_GENERATED_KEYS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.add(new UsuarioDTO(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getDate("fechaInscripcion").toLocalDate()));
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
         	PreparedStatement ps = con.prepareStatement(getProps().getProperty("busca-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioDTO(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getDate("fechaNacimiento").toLocalDate(), rs.getDate("fechaInscripcion").toLocalDate());
            }
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
        return usuario;
    }

    //Eliminar usuario base de datos
    
    public void eliminarUsuario(String email) {
        try {
        	 Connection con = getConnection();
         	PreparedStatement ps = con.prepareStatement(getProps().getProperty("borra-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
    }

    //Existe usuario base de datos
    public boolean existeUsuario(String email) {
        boolean existe = false;
        try {
        	
        	 Connection con = getConnection();
         	PreparedStatement ps = con.prepareStatement(getProps().getProperty("existe-usuario"), PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                existe = true;
            }
        } catch (SQLException e) {
            close();
            System.out.println(e);
        }
        return existe;
    }
}
