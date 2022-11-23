package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObject.VAccount;

public class Main {
	
	private PLoginDialog loginDialog;
	
	public Main() {		
	}
	private void initialize() {
		ActionHandler actionHandler = new ActionHandler();
		
		this.loginDialog = new PLoginDialog(actionHandler);
		this.loginDialog.setVisible(true);
	}
	
	private void run() {
		VAccount account = this.loginDialog.login();
		this.loginDialog.dispose();
		
		if (account != null) {
			PMainFrame mainFrame = new PMainFrame(account);
			mainFrame.initialize();			
		}		
	}
	private void finish() {
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
//		main.run();
//		main.finish();
		
	
	}



}