package viewSugangSincheong;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import valueObject.VLecture;

public class PLectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	
	private SLecture sLecture;
	
	private DefaultTableModel tableModel;
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
	
	// read data from file/db
	public Vector<VLecture> queryData(String fileName) {
		this.sLecture = new SLecture();
		return this.sLecture.getLectures(fileName);		
	}
	
	// set data to Table
	public void setData(Vector<VLecture> vLectures) {
		
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

	public Vector<VLecture> getSelectedLectures() {
		int[] selectedIndices = this.getSelectedRows();
		return null;
	}
}

