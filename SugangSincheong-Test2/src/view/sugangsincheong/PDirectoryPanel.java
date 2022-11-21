package view.sugangsincheong;
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
import service.SLecture;
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
			this.campusTable = new PDirectory("root/");
			this.campusTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.campusTable);
			subPanel1.add(scrollPane);
	
			scrollPane = new JScrollPane();
			this.collegeTable = new PDirectory("campus/");
			this.collegeTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.collegeTable);
			subPanel1.add(scrollPane);
			
			scrollPane = new JScrollPane();
			this.departmentTable = new PDirectory("college/");
			this.departmentTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.departmentTable);			
			subPanel1.add(scrollPane);
		this.add(subPanel1);
			
		JPanel subPanel2 = new JPanel();
			layoutManager = new BoxLayout(subPanel2, BoxLayout.Y_AXIS);
			subPanel2.setLayout(layoutManager);
			
			scrollPane = new JScrollPane();
			this.lectureTable = new PLectureTable("department/");
			this.lectureTable.getSelectionModel().addListSelectionListener(this.listSelectionHandler);
			scrollPane.setViewportView(this.lectureTable);
			subPanel2.add(scrollPane);			
		this.add(subPanel2);			

		this.updateTable(null);
	}
	
	private void updateTable(Object object) {
		String fileName = null;
		int[] selectedIndices;;
		if (object == null) {
			this.campusTable.setData("root");
		} else if (object == this.campusTable.getSelectionModel()) {
			selectedIndices = this.campusTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.campusTable.getFileName(selectedIndices[0]);
				this.collegeTable.setData(fileName);
			}
		} else if (object == this.collegeTable.getSelectionModel()) {			
			selectedIndices = this.collegeTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.collegeTable.getFileName(selectedIndices[0]);
				this.departmentTable.setData(fileName);
			}
		} else if (object == this.departmentTable.getSelectionModel()) {			
			selectedIndices = this.departmentTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				fileName = this.departmentTable.getFileName(selectedIndices[0]);
				this.lectureTable.setData(fileName);
			}
		} else if (object == this.lectureTable) {			
			selectedIndices = this.lectureTable.getSelectedRows();
			if (selectedIndices.length > 0) {
				
			}
		}
	}
	
	private class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
//				System.out.println(event.getSource().toString());
				updateTable(event.getSource());
			}
		}		
	}
	
	private class PDirectory extends JTable {
		private static final long serialVersionUID = 1L;
		
		private DefaultTableModel tableModel;
		
		private SDirectory sDirectory;
		private Vector<VDirectory> vDirectories;
		private String directoryName;
		
		public PDirectory(String directoryName) {
			this.directoryName = directoryName;
			
			this.sDirectory = new SDirectory();
			this.vDirectories = sDirectory.getDirectories(this.directoryName + "header");
			
			Vector<String> header = new Vector<String>();
			header.add(this.vDirectories.get(0).getName());
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);			
		}
		public String getFileName(int index) {
			return this.vDirectories.get(index).getFileName();
		}
		
		public void setData(String fileName) {
			this.vDirectories = sDirectory.getDirectories(this.directoryName + fileName);

			this.tableModel.setNumRows(0);
			for (VDirectory vDirectory: this.vDirectories) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);		
			}
			this.setRowSelectionInterval(0, 0);
		}
	}
}
