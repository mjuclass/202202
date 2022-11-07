import java.util.Vector;

public class SDirectory {
	
	private EDirectory eDirectory;
	public SDirectory() {
		this.eDirectory = new EDirectory();
	}

	public Vector<VDirectory> getDirectories(String fileName) {
		return this.eDirectory.getDirectories(fileName);
	}
}
