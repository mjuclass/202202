package service;
import java.util.Vector;

import entity.EDirectory;
import valueObject.VDirectory;

public class SDirectory {
	
	private EDirectory eDirectory;
	public SDirectory() {
		this.eDirectory = new EDirectory();
	}

	public Vector<VDirectory> getDirectories(String fileName) {
		return this.eDirectory.getDirectories(fileName);
	}
}
