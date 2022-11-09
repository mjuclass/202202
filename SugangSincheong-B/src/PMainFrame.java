import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private PAccountPanel accountPanel;
	private PSugangsincheongPanel sugangsincheongPanel;	
	
	private VAccount vAccount;	
	public void setVAccount(VAccount vAccount) { this.vAccount = vAccount; }
	
	public PMainFrame() {
		// attributes
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - this.getWidth(), 20);
		this.setSize(1000,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		PLoginDialog loginDialog = new PLoginDialog(this);
		loginDialog.setVisible(true);
		
		// components
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		this.accountPanel = new PAccountPanel(this.vAccount);
		this.add(this.accountPanel, BorderLayout.NORTH);
		
		this.sugangsincheongPanel = new PSugangsincheongPanel();
		this.add(sugangsincheongPanel, BorderLayout.CENTER);
		
	}
}
