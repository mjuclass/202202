import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PDirectoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryTable campusTable;
	private PDirectoryTable collegeTable;
	private PDirectoryTable departmentTable;
	
	public PDirectoryPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		JScrollPane scrollPane = null;
		
		this.campusTable = new PDirectoryTable("campus");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.campusTable);
		this.add(scrollPane);
		
		this.collegeTable = new PDirectoryTable("college");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.collegeTable);
		this.add(scrollPane);
		
		this.departmentTable = new PDirectoryTable("department");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.departmentTable);
		this.add(scrollPane);
	}
	
	public void initialize() {
		String campusName = this.campusTable.setRows("root");
		String collegeName = this.collegeTable.setRows(campusName);
		String departmentName = this.departmentTable.setRows(collegeName);		
	}

	
	private class PDirectoryTable extends JTable {
		private static final long serialVersionUID = 1L;
		
		private DefaultTableModel tableModel;
		private CDirectory cDirectory;
		
		public PDirectoryTable(String title) {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			Vector<String> header = new Vector<String>();
			header.add(title);
			this.tableModel = new DefaultTableModel(header, 3);
			this.setModel(this.tableModel);
			
			this.cDirectory = new CDirectory();
		}
		
		private String setRows(String fileName) {
			Vector<VDirectory> vDirectories = this.cDirectory.getVDirectories(fileName);
			
			this.tableModel.setRowCount(0);
			for (VDirectory vDirectory: vDirectories) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);
			}
			this.setRowSelectionInterval(0, 0);
			return vDirectories.get(0).getFileName();
		}
	}
}
