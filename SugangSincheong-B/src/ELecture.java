import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class ELecture {
	public Vector<VLecture> getLectures(String fileName) {
		Vector<VLecture> vLectures = new Vector<VLecture>();
		try {
			Scanner scanner = new Scanner(new File("directory/"+fileName));
			while (scanner.hasNext()) {
				VLecture vLecture = new VLecture();
				vLecture.read(scanner);
				vLectures.add(vLecture);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vLectures;
	}

}
