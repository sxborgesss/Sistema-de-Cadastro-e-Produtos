package DAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.RecursiveTask;

public class Conexao {
    public static Connection pegaConexao() throws ErroDao {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemaprodutos?useSSL=false", "root","bd123");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ErroDao(e);
        }
    }
}
