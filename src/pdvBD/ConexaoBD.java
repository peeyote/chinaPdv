package pdvBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import pdv.telaPdv;

public class ConexaoBD {

	public Connection con = null;
	public Statement stm = null;

	public ConexaoBD() {

		try {

			Class.forName("org.firebirdsql.jdbc.FBDriver");
			con = DriverManager.getConnection("jdbc:firebirdsql:localhost/3050:C:/pdv/BDCaixa1.fdb", "SYSDBA",
					"masterkey");
			stm = con.createStatement();
			
			//Se conectado com sucesso
			
			telaPdv pdv = new telaPdv();
			pdv.setVisible(true);
			pdv.txtCodProduto.grabFocus();
			
		} catch (Exception e) {
			System.out.println("Não foi possível conectar ao banco !" + e.getMessage());
			telaConfigBD config = new telaConfigBD();
			config.setVisible(true);
		}

	}

}