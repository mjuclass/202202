import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
	
	private class PDirectoryTable extends JTable {
		private static final long serialVersionUID = 1L;
		
		private DefaultTableModel tableModel;
		
		public PDirectoryTable(String title) {
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			Vector<String> header = new Vector<String>();
			header.add(title);
			this.tableModel = new DefaultTableModel(header, 3);
			this.setModel(this.tableModel);
			
//			this.tableModel.setRowCount(0);
//			for (int i=0; i<3; i++) {
//				Vector<String> row = new Vector<String>();
//				row.add("test" + i);
//				this.tableModel.addRow(row);
//			}
		}
	}
}
