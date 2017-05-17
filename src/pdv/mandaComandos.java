package pdv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mandaComandos {
	public mandaComandos(String comando){
		FileWriter arquivo;
		try {
			arquivo = new FileWriter(new File("C:/Comandos/ent.txt"));
			arquivo.write(comando);
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

}
