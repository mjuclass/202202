package view;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import valueObject.VLecture;

public class PLectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	
	private SLecture sLecture;
	private Vector<VLecture> vLectures;
	
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
	public Vector<VLecture> getVLectures() {
		return this.vLectures;
	}
	
	public void setData(String fileName) {
		this.sLecture = new SLecture();
		this.vLectures = sLecture.getLectures(fileName);

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

