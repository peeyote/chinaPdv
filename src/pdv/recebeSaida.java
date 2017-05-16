package pdv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class recebeSaida {
	public String saida1=null;

	public recebeSaida() {
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader("C:/ACBrMonitorPLUS/sai.txt"));
			while (scanner.hasNext()) {
				saida1 += scanner.nextLine();
				saida1 += "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

			File f = new File("C:/ACBrMonitorPLUS/sai.txt");
			f.delete();
	}
}

