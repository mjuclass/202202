import java.util.Vector;

public class CDirectory {

	private EDirectory eDirectory;
	public CDirectory() {
		this.eDirectory = new EDirectory();
	}
	public Vector<VDirectory> getVDirectories(String fileName) {
		// TODO Auto-generated method stub
		return this.eDirectory.getVDirectories(fileName);
	}

}
