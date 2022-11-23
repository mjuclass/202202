package view;

import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.PSugangsincheongPanel.ActionHandler;

public class PControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JButton buttonRight;
	private JButton buttonLeft;
	
	public PControlPanel(String panelId, ActionHandler actionHandler) {
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		this.buttonRight = new JButton(">>");
		this.buttonRight.addActionListener(actionHandler);
		this.buttonRight.setActionCommand(panelId+this.buttonRight.getText());
		this.add(this.buttonRight);
		
		this.buttonLeft = new JButton("<<");
		this.buttonLeft.addActionListener(actionHandler);
		this.buttonLeft.setActionCommand(panelId+this.buttonLeft.getText());
		this.add(this.buttonLeft);
	}
}
