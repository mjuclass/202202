package view;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton buttonRight;
	private JButton buttonLeft;
	
	public PControlPanel() {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.buttonRight = new JButton(">>");
		this.add(this.buttonRight);
		
		this.buttonLeft = new JButton("<<");
		this.add(this.buttonLeft);
	}
}
