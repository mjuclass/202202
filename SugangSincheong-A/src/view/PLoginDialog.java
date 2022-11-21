package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.SLogin;
import valueObject.VAccount;
import view.Main.ActionHandler;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;
	
	private SLogin sLogin;
	
	public PLoginDialog(ActionHandler actionHandler) {
		this.setLocation(100, 100);
		this.setSize(200,200);
		this.setModal(true);
		
		
		LayoutManager layoutManager = new FlowLayout();
		this.setLayout(layoutManager);
		
		JLabel lbId=new JLabel("아이디");    
		this.add(lbId);
		 		
		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		this.add(tfId);
		
		JLabel lbPassword =new JLabel("비밀번호");    
		this.add(lbPassword);
		
		this.tfPassword = new JPasswordField();   
		this.tfPassword.setColumns(10);
		this.add(tfPassword);
		
		
		JButton btLogin = new JButton("Login");    
		this.add(btLogin,BorderLayout.SOUTH);
		btLogin.addActionListener(actionHandler);
		
		
		this.sLogin = new SLogin();
	}
	
	void login() {
		String id = this.tfId.getText();
		char[] password = this.tfPassword.getPassword();
		VAccount vAccount = this.sLogin.login(id);
		PMainFrame mainFrame = (PMainFrame)this.getParent();
		mainFrame.setVAccount(vAccount);
		this.dispose();
	}
	

}
