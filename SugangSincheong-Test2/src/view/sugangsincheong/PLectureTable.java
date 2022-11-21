package view.sugangsincheong;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import service.SLecture;
import valueObject.VLecture;

public class PLectureTable extends JTable {
	private static final long serialVersionUID = 1L;
	
	private SLecture sLecture;
	private Vector<VLecture> vLectures;
	private String directoryName;
	
	private DefaultTableModel tableModel;
	public PLectureTable(String directoryName) {
		this.directoryName = directoryName;
		
		this.sLecture = new SLecture();
		this.vLectures = sLecture.getLectures(directoryName+"header");
		
		Vector<String> header = new Vector<String>();
		header.add(this.vLectures.get(0).getId());
		header.add(this.vLectures.get(0).getName());
		header.add(this.vLectures.get(0).getProfessor());
		header.add(this.vLectures.get(0).getCredit());
		header.add(this.vLectures.get(0).getTime());
		this.tableModel = new DefaultTableModel(header, 0);
		this.setModel(this.tableModel);			
	}
	
	public void setData(String fileName) {
		this.vLectures = sLecture.getLectures(directoryName+fileName);

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
