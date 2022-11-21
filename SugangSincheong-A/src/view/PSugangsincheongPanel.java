package view;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

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
		
		this.miridamgiPanel = new PMiridamgiPanel();
		this.add(this.miridamgiPanel);
		
		this.controlPanel2 = new PControlPanel();
		this.add(this.controlPanel2);
		
		this.sincheongPanel = new PSincheongPanel();
		this.add(this.sincheongPanel);
		
	}
}
