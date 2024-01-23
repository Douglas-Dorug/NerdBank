package Projeto.Configuracoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mariadb://localhost:3306/novoDB";
    private static final String USUARIO = "root";
    private static final String SENHA = "admin010203";

    // Objeto de conexão
    private static Connection conexao;

    // Método para obter uma conexão com o banco de dados
    public static Connection obterConexao() {
        try {
            // Carregar o driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // Estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            return conexao;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Tratamento de erro
            return null;
        }
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tratamento de erro
        }
    }
}
