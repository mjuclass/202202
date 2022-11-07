import java.util.Vector;

public class CLecture {

	private ELecture eLecture;
	public CLecture() {
		this.eLecture = new ELecture();
	}
	public Vector<VLecture> getVLectures(String fileName) {
		// TODO Auto-generated method stub
		return this.eLecture.getVLectures(fileName);
	}

}
