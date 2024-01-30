package Projeto.Configuracoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:mysql://aws.connect.psdb.cloud/bancotestes_planetscale";
    private static final String USUARIO = "9lznz8qgnr2sfgw6tj5j";
    private static final String SENHA = "pscale_pw_bdyr3k9iUARvib76Ga7tjnRya42Ay0lqZHkxjfAni9Z";

    // Objeto de conexão
    private static Connection conexao;

    // Método para obter uma conexão com o banco de dados
    public static Connection obterConexao() {
        try {
            // Carregar o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

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
