package ViewSugangSingcheong;
import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import service.SDirectory;
import valueObject.VDirectory;
import valueObject.VLecture;

public class PDirectoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ListSelectionHandler listSelectionHandler;
	private PDirectory campusTable;
	private PDirectory collegeTable;
	private PDirectory departmentTable;
	
	private PLectureTable lectureTable;
	
	public PDirectoryPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);

		this.listSelectionHandler = new ListSelectionHandler();
		
		JPanel subPanel1 = new JPanel();
			layoutManager = new BoxLayout(subPanel1, BoxLayout.X_AXIS);
			subPanel1.setLayout(layoutManager);
			
			JScrollPane scrollPane = new JScrollPane();
			this.campusTable = new PDirectory();
			this.campusTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.campusTable);
			subPanel1.add(scrollPane);
	
			scrollPane = new JScrollPane();
			this.collegeTable = new PDirectory();
			this.collegeTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.collegeTable);
			subPanel1.add(scrollPane);
			
			scrollPane = new JScrollPane();
			this.departmentTable = new PDirectory();
			this.departmentTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.departmentTable);			
			subPanel1.add(scrollPane);
		this.add(subPanel1);
			
		JPanel subPanel2 = new JPanel();
			layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
			subPanel2.setLayout(layoutManager);
			
			scrollPane = new JScrollPane();
			this.lectureTable = new PLectureTable();
			this.lectureTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.lectureTable);
			subPanel2.add(scrollPane);			
		this.add(subPanel2);
		
		this.updateTable(null);
	}
	
	private void updateTable(Object object) {
		String fileName = null;
		int[] selectedIndices;
		if (object == null) {
			fileName = "root";
			this.campusTable.setData(fileName);
		} else if (object == this.campusTable.getSelectionModel()) {
			selectedIndices = this.campusTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.campusTable.getVDirectories().get(selectedIndices[0]).getFileName();
				this.collegeTable.setData(fileName);
			}
		} else if (object == this.collegeTable.getSelectionModel()) {
			selectedIndices = this.collegeTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.collegeTable.getVDirectories().get(selectedIndices[0]).getFileName();
				this.departmentTable.setData(fileName);
			}
		} else if (object == this.departmentTable.getSelectionModel()) {		
			selectedIndices = this.departmentTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.departmentTable.getVDirectories().get(selectedIndices[0]).getFileName();
				Vector<VLecture> vLectures = this.lectureTable.queryData(fileName);
				this.lectureTable.setData(vLectures);
			}
		} else if (object == this.lectureTable.getSelectionModel()) {
			
		}
	}
	
	public Vector<VLecture> getSelectedLectures() {
		return this.lectureTable.getSelectedLectures();
	}

	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {				
				System.out.println(event.getSource().toString());
				updateTable(event.getSource());
			} else {
			}
		}
		
	}
	private class PDirectory extends JTable {
		private static final long serialVersionUID = 1L;
		private Vector<VDirectory> vDirectories;
		
		private DefaultTableModel tableModel;
		public PDirectory() {
			Vector<String> header = new Vector<String>();
			header.add("Test");
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);
			
		}
		public Vector<VDirectory> getVDirectories() {
			return this.vDirectories;
		}
		
		public String setData(String fileName) {
			SDirectory sDirectory = new SDirectory();
			this.vDirectories = sDirectory.getDirectories(fileName);

			this.tableModel.setNumRows(0);
			for (VDirectory vDirectory: this.vDirectories) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);		
			}
			this.setRowSelectionInterval(0, 0);
			return vDirectories.get(0).getFileName();
		}
	}
}
