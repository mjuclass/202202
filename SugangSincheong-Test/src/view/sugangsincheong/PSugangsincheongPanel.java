package view.sugangsincheong;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PSugangsincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel directoryPanel;
	private PControlPanel controlPanel1;
	private PMiridamgiPanel miridamgiPanel;
	private PControlPanel controlPanel2;
	private PSincheongPanel sincheongPanel;	
	
	public PSugangsincheongPanel() {
		ListSelectionHandler listSelectionHandler = new ListSelectionHandler();
		
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);

		this.controlPanel1 = new PControlPanel();
		this.add(this.controlPanel1);
		

		this.miridamgiPanel = new PMiridamgiPanel(listSelectionHandler);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(this.miridamgiPanel);
		this.add(scrollPane);
		
		this.controlPanel2 = new PControlPanel();
		this.add(this.controlPanel2);	
		
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
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		}		
	}
	
	private class PControlPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private JButton buttonLeft, buttonRight;
		public PControlPanel() {
			LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(layoutManager);
			
			this.buttonLeft = new JButton("<<");
			this.add(this.buttonLeft);
			this.buttonRight = new JButton(">>");
			this.add(this.buttonRight);
		}
	}
}
