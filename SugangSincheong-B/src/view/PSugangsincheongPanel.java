package view;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PSugangsincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel directoryPanel;
	private PControlPanel controlPanel1;
	private PMiridamgiPanel miridamgiPanel;
	private PControlPanel controlPanel2;
	private PSincheongPanel sincheongPanel;
	
	public PSugangsincheongPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);
		
		this.controlPanel1 = new PControlPanel();
		this.add(this.controlPanel1);

		JScrollPane scrollPane = new JScrollPane();
		this.miridamgiPanel = new PMiridamgiPanel();
		scrollPane.setViewportView(this.miridamgiPanel);
		this.add(scrollPane);
		
		this.controlPanel2 = new PControlPanel();
		this.add(this.controlPanel2);
		
		scrollPane = new JScrollPane();
		this.sincheongPanel = new PSincheongPanel();
		scrollPane.setViewportView(this.sincheongPanel);
		this.add(scrollPane);
	}
}
