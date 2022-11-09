import java.util.Vector;

public class SLecture {
	
	private ELecture eLecture;
	public SLecture() {
		this.eLecture = new ELecture();
	}

	public Vector<VLecture> getLectures(String fileName) {
		return this.eLecture.getLectures(fileName);
	}
}
