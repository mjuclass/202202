package ViewSugangSingcheong;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import valueObject.VLecture;

public class PLectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	
	private DefaultTableModel tableModel;
	private Vector<VLecture> vLecturs;
	public PLectureTable() {
		Vector<String> header = new Vector<String>();
		header.add("Test");
		header.add("Test");
		header.add("Test");
		header.add("Test");
		header.add("Test");
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);			
	}
	
	public Vector<VLecture> getSelectedLectures() {
		int[] selectedIndices = this.getSelectedRows();
		Vector<VLecture> selectedVLectures = new Vector<VLecture>();
		
		return selectedVLectures;
	}
	
	public Vector<VLecture> queryData(String fileName) {
		SLecture sLecture = new SLecture();
		return sLecture.getLectures(fileName);
	}
	public void setData(Vector<VLecture> vLectures) {
		this.vLecturs = vLectures;
		
		this.tableModel.setNumRows(0);
		for (VLecture vLecture: vLectures) {				
			Vector<String> row = new Vector<String>();
			row.add(vLecture.getId());
			row.add(vLecture.getName());
			row.add(vLecture.getProfessor());
			row.add(vLecture.getCredit());
			row.add(vLecture.getTime());

			this.tableModel.addRow(row);		
		}
		this.setRowSelectionInterval(0, 0);
	}
}