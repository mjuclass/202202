import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class EDirectory {
	public EDirectory() {
	}

	public Vector<VDirectory> getVDirectories(String fileName) {
		Vector<VDirectory> vDirectories = new Vector<VDirectory>();
		try {
			Scanner scanner = new Scanner(new File("data/"+fileName));
			while (scanner.hasNext()) {
				VDirectory vDirectory = new VDirectory();
				vDirectory.setId(scanner.next());
				vDirectory.setName(scanner.next());
				vDirectory.setFileName(scanner.next());
				vDirectories.add(vDirectory);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vDirectories;
	}
}
