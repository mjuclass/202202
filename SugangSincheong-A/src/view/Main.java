package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
	private PLoginDialog loginDialog;
	public Main() {
		
	}
	public void initialize() {
		ActionHandler actionHandler = new ActionHandler();

		PLoginDialog loginDialog = new PLoginDialog(actionHandler);
		loginDialog.setVisible(true);		
	}
	public void run() {
//		Account account = this.loginDialog.login();
//		if (account != null) {
//			MainFrame mainfFrmae = new MainFrame();
//			mainfFrmae.intitialize();
//		}
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
		main.run();
		main.finish();	
		
//		PMainFrame mainFrame = new PMainFrame();
//		mainFrame.initialize();
//		mainFrame.run();
//		mainFrame.finish();	
	}
}