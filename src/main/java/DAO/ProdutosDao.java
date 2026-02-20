package DAO;

import com.example.trabalho03.Modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDao {
    public void adicionarProduto(Produto produto) throws ErroDao {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getPreco());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    public Produto buscarPorId(int id) throws ErroDao {
        String sql = "SELECT id, nome, preco FROM produtos WHERE id = ?";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("preco")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }

    public List<Produto> listarProdutos() throws ErroDao {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco FROM produtos ORDER BY id";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("preco"));
                produtos.add(p);
            }
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
        return produtos;
    }

    public void remover(int id) throws ErroDao {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
    public void editarProduto(Produto produto) throws ErroDao {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection con = Conexao.pegaConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getPreco());
            ps.setInt(3, produto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ErroDao(e);
        }
    }
}