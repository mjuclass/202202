package service;
import java.util.Vector;

import entity.ELecture;
import valueObject.VLecture;

public class SLecture {
	private ELecture eLecture;
	
	public SLecture() {
		this.eLecture = new ELecture();
	}

	public Vector<VLecture> getLectures(String fileName) {
		return this.eLecture.getLectures(fileName);
	}
}
