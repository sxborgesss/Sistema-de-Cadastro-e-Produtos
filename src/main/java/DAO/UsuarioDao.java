package DAO;

import com.example.trabalho03.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuarios (nome, login, senha, tipo) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getLogin());
            ps.setString(3, usuario.getSenha());
            ps.setInt(4, usuario.getTipo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    public Usuario buscar(String login, String senha) throws ErroDao {
        String sql = "SELECT id, nome, login, senha, tipo FROM usuarios WHERE login = ? AND senha = ?";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, senha);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("login"),
                            rs.getString("senha"),
                            rs.getInt("tipo")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
    public void editarUsuario(Usuario usuario) throws ErroDao {
        String sql = "UPDATE usuarios SET login = ?, senha = ? WHERE id = ?";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
}

