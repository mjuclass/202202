package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import valueObject.VAccount;

public class Main {

	private PMainFrame mainFrame;
	private PLoginDialog loginDialog;
	
	private Main() {
		ActionHandler actionHandler = new ActionHandler();
		this.loginDialog = new PLoginDialog(null, actionHandler);
		
	}
	private void initialize() {
		this.loginDialog.initialize();
	}
	
	private void run(VAccount vAccount) {
		this.mainFrame = new PMainFrame(vAccount);
		this.mainFrame.initialize();
	}
	
	private void terminate() {
		System.exit(0);
	}
	
	public class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			VAccount vAccount = loginDialog.getAccount(event.getSource());
			if (vAccount != null) {
				run(vAccount);
			} else {
				terminate();
			}
		}		
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
	}
}