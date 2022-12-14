package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;

import valueObject.VAccount;
import view.sugangsincheong.PSugangsincheongPanel;

public class PMainFrame extends JFrame {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private PAccountPanel accountPanel;
	private PSugangsincheongPanel sugangsincheongPanel;	
	
	public PMainFrame(VAccount vAccount) {
		// attributes
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - Constants.MainFrame.WIDTH/2, Constants.MainFrame.Y);
		this.setSize(Constants.MainFrame.WIDTH, Constants.MainFrame.HIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		LayoutManager layoutManager = new BorderLayout();
		this.setLayout(layoutManager);
		
		// components
		this.accountPanel = new PAccountPanel(vAccount);
		this.add(this.accountPanel, BorderLayout.NORTH);
		
		this.sugangsincheongPanel = new PSugangsincheongPanel();
		this.add(sugangsincheongPanel, BorderLayout.CENTER);
		
	}

	public void initialize() {
		this.setVisible(true);		
	}
}
