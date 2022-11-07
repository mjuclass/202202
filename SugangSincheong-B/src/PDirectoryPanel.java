import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PDirectoryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectory campusTable;
	private PDirectory collegeTable;
	
	public PDirectoryPanel() {
		JScrollPane scrollPane = new JScrollPane();
		this.campusTable = new PDirectory();
		scrollPane.setViewportView(this.campusTable);
		this.add(scrollPane);

		scrollPane = new JScrollPane();
		this.collegeTable = new PDirectory();
		scrollPane.setViewportView(this.collegeTable);
		this.add(scrollPane);

		this.campusTable.setData("root");
	}
	
	private class PDirectory extends JTable {
		private static final long serialVersionUID = 1L;
		
		private DefaultTableModel tableModel;
		public PDirectory() {
			Vector<String> header = new Vector<String>();
			header.add("Test");
			this.tableModel = new DefaultTableModel(header, 0);
			this.setModel(this.tableModel);
			
		}
		
		public void setData(String fileName) {
			SDirectory sDirectory = new SDirectory();
			Vector<VDirectory> vDirectories = sDirectory.getDirectories(fileName);

			for (VDirectory vDirectory: vDirectories) {
				Vector<String> row = new Vector<String>();
				row.add(vDirectory.getName());
				this.tableModel.addRow(row);		
			}
		}
	}
}
