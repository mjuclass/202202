package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

import global.Constants;
import valueObject.VAccount;

public class PMainFrame extends JFrame {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private PAccountPanel accountPanel;
	private PSugangsincheongPanel sugangsincheongPanel;	
	
	private VAccount vAccount;	
	public void setVAccount(VAccount vAccount) { this.vAccount = vAccount; }
	
	public PMainFrame(VAccount vAccount) {
		// attributes
		this.setSize(Constants.CMainFrame.WIDTH,Constants.CMainFrame.HEIGHT);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - this.getWidth()/2, Constants.CMainFrame.Y);		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.vAccount = vAccount;
		
		// components
		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		this.accountPanel = new PAccountPanel(this.vAccount);
		this.add(this.accountPanel, BorderLayout.NORTH);
		
		this.sugangsincheongPanel = new PSugangsincheongPanel();
		this.add(sugangsincheongPanel, BorderLayout.CENTER);		
	}

	public void initialize() {
		
		this.setVisible(true);
	}
}
