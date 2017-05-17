package pdv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class recebeSaida {
	public String saida1="";

	public String recebeSaida() {
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader("C:/Comandos/saida/ent-resp.txt"));
			while (scanner.hasNext()) {
				saida1 += scanner.nextLine();
				saida1 += "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return saida1;

	}
}

