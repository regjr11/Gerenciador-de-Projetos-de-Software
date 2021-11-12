package gerenciador_projetos_software.model.dao;

import gerenciador_projetos_software.model.entity.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDao {

    public boolean checkLogin(String email, String senha) {

        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        boolean check = false;

        try {

            stmt = con.prepareStatement("SELECT * FROM usuario WHERE Email = ? and Senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return check;
    }

    public void create(Usuario u) {

        Connection con = ConexaoBD.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO Usuario (Matricula,Nome,Funcao,Name_User,Senha,Email)VALUES(?,?,?,?,?,?)");
            stmt.setInt(1, u.getMatricula());
            stmt.setString(2, u.getNome());
            stmt.setString(3, u.getFuncao());
            stmt.setString(4, u.getUser());
            stmt.setString(5, u.getSenha());
            stmt.setString(6, u.getEmail());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }

    }

    public void update(Usuario u) {

        Connection con = ConexaoBD.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE Usuario SET Nome = ? ,Funcao = ?,Name_User = ?, Senha = ?, Email = ? WHERE Matricula = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getFuncao());
            stmt.setString(3, u.getUser());
            stmt.setString(4, u.getSenha());
            stmt.setString(5, u.getEmail());
            stmt.setInt(6, u.getMatricula());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }

    }

    public void delete(Usuario u) {

        Connection con = ConexaoBD.getConnection();

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM Usuario WHERE Matricula = ?");
            stmt.setInt(1, u.getMatricula());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt);
        }

    }


    public List<Usuario> read() {
        Connection con = ConexaoBD.getConnection();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Usuario> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM Usuario");
            rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario();

                usuario.setMatricula(rs.getInt("matricula"));
                usuario.setNome(rs.getString("nome"));
                usuario.setUser(rs.getString("user"));
                usuario.setFuncao(rs.getString("funcao"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoBD.closeConnection(con, stmt, rs);
        }

        return usuarios;

    }
}

