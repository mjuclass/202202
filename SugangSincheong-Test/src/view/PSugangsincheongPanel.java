package view;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PSugangsincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel directoryPanel;
	private PMiridamgiPanel miridamgiPanel;
	private PSincheongPanel sincheongPanel;	
	
	public PSugangsincheongPanel() {
//		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		LayoutManager layoutManager = new GridLayout();
		this.setLayout(layoutManager);
		
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);
		
		ListSelectionHandler listSelectionHandler = new ListSelectionHandler();

		this.miridamgiPanel = new PMiridamgiPanel(listSelectionHandler);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.miridamgiPanel);
		this.add(scrollPane);
		
		this.sincheongPanel = new PSincheongPanel(listSelectionHandler);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.sincheongPanel);
		this.add(scrollPane);
	}
	
	private void updateTable(Object object) {
		
	}
	
	public class ListSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent event) {
			if (!event.getValueIsAdjusting()) {
//				System.out.println(event.getSource().toString());
				updateTable(event.getSource());
			}
		}
		
	}
}
