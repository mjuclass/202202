package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObject.VAccount;

public class Main {
	private PLoginDialog loginDialog;
	
	public Main() {
		
	}
	public void initialize() {
		ActionHandler actionHandler = new ActionHandler();

		this.loginDialog = new PLoginDialog(actionHandler);
		this.loginDialog.setVisible(true);		
	}
	public void run() {
		VAccount vAccount = loginDialog.login();
		loginDialog.dispose();
		
		if (vAccount != null) {
			PMainFrame mainFrame = new PMainFrame(vAccount);
			mainFrame.initialize();
		}
	}
	public void finish() {
		
	}
	
	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			run();
		}		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
//		main.finish();	
	}
}