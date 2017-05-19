package pdvBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pdv.telaPdv;

public class ConexaoBD {

	public Connection con = null;
	public Statement stm = null;
	public ResultSet rs = null;

	public ConexaoBD() {

		try {

			Class.forName("org.firebirdsql.jdbc.FBDriver");
			con = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:C:/syspdv/syspdv_mov.fdb", "SYSDBA",
					"masterkey");
			stm = con.createStatement();

			telaPdv pdv = new telaPdv();
			pdv.setVisible(true);

		} catch (Exception e) {
			System.out.println("Não foi possível conectar ao banco !" + e.getMessage());
			telaConfigBD config = new telaConfigBD();
			config.setVisible(true);
		}

	}
	

	public void desconectaBD() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}