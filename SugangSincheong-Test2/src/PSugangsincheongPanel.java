import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class PSugangsincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel directoryPanel;
	
	public PSugangsincheongPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);
	}

	public void initialize() {
		this.directoryPanel.initialize();
	}
}
