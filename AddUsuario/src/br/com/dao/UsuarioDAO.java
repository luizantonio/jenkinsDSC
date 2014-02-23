package br.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import br.com.model.Usuario;

public class UsuarioDAO {

    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<Usuario>();
        Connection c = null;
    	String sql = "SELECT * FROM usuario ORDER BY user_nome";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    
    public List<Usuario> findByName(String user_nome) {
        List<Usuario> list = new ArrayList<Usuario>();
        Connection c = null;
    	String sql = "SELECT * FROM usuario as e " +
			"WHERE UPPER(user_nome) LIKE ? " +	
			"ORDER BY user_nome";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + user_nome.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public Usuario findById(int id) {
    	String sql = "SELECT * FROM usuario WHERE id = ?";
    	Usuario usuario = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return usuario;
    }

    public Usuario save(Usuario usuario)
	{
		return usuario.getId() == 0 ? create(usuario): update(usuario) ;
	}    
    
    public Usuario create(Usuario usuario) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO usuario (user_nome, user_cpf, user_rg, id_telefone, id_email, user_login, user_senha) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new String[] { "ID" });
            ps.setString(1, usuario.getUser_nome());
            ps.setString(2, usuario.getUser_cpf());
            ps.setString(3, usuario.getUser_rg());
            ps.setString(4, usuario.getId_telefone());
            ps.setString(5, usuario.getId_email());
            ps.setString(6, usuario.getUser_login());
            ps.setString(7, usuario.getUser_senha());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            
            int id = rs.getInt(1);
            usuario.setId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return usuario;
    }

    public Usuario update(Usuario usuario) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE usuario SET user_nome=?, user_cpf=?, user_rg=?, id_telefone=?, id_email=?, user_login=?, user_senha=? WHERE id=?");
            ps.setString(1, usuario.getUser_nome());
            ps.setString(2, usuario.getUser_cpf());
            ps.setString(3, usuario.getUser_rg());
            ps.setString(4, usuario.getId_telefone());
            ps.setString(5, usuario.getId_email());
            ps.setString(6, usuario.getUser_login());
            ps.setString(7, usuario.getUser_senha());
            ps.setInt(8, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return usuario;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM usuario WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
    }

    protected Usuario processRow(ResultSet rs) throws SQLException {
    	Usuario usuario = new Usuario(); 	
    	usuario.setId(rs.getInt("id"));
    	usuario.setUser_nome(rs.getString("user_nome"));
    	usuario.setUser_cpf(rs.getString("user_cpf"));
    	usuario.setUser_rg(rs.getString("user_rg"));
    	usuario.setId_telefone(rs.getString("id_telefone"));
    	usuario.setId_email(rs.getString("id_email"));
    	usuario.setUser_login(rs.getString("user_login"));
    	usuario.setUser_senha(rs.getString("user_senha"));
    		 	
        return usuario;
    }
    
}
