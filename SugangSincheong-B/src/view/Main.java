package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
//		VAccount account = this.loginDialog.login();
//		if (account != null) {
//			PMainFrame mainFrame = new PMainFrame();
//			mainFrame.initialize();			
//		}		
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
		main.run();
		main.finish();
		
	
	}



}