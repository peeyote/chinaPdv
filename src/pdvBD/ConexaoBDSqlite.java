package pdvBD;
import java.sql.*;

import javax.swing.JOptionPane;

public class ConexaoBDSqlite {
	private Connection conexao;
	public Statement statement;
	public ResultSet resultset;
	public PreparedStatement prep;

	public void conecta() throws Exception {
		Class.forName("org.sqlite.JDBC");
		conexao = DriverManager.getConnection("jdbc:sqlite:C:/pdv/Caixa_BD.sqlite");
		statement = conexao.createStatement();
		conexao.setAutoCommit(false);
		conexao.setAutoCommit(true);
	}

	public void exec(String sql) throws Exception {
		resultset = statement.executeQuery(sql);
	}

	public void desconecta() {
		boolean result = true;
		try {
			conexao.close();
		} catch (SQLException fecha) {
			result = false;
		}
	}
}
