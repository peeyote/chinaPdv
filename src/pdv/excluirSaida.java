package pdv;

import java.io.File;

public class excluirSaida {
	public File excluirSaida(){
		File f = new File("C:/comandos/saida/ent-resp.txt");
		f.delete();
		return f;
	}
}
