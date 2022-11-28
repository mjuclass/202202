package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import global.Constants;
import global.Locale;
import service.SLogin;
import valueObject.VAccount;
import view.Main.ActionHandler;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;
	
	private SLogin sLogin;
	
	public PLoginDialog(ActionHandler actionHandler) {
		
		this.setSize(Constants.CLoginDialog.WIDTH,Constants.CLoginDialog.HEIGHT);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - this.getWidth()/2, Constants.CLoginDialog.Y);		
		this.setModal(true);
		
		LayoutManager layoutManager = new FlowLayout();
		this.setLayout(layoutManager);
		
		JLabel lbId=new JLabel(Locale.LLoginPanel.ID_LABEL);    
		this.add(lbId);
		 		
		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		this.add(tfId);
		
		JLabel lbPassword =new JLabel(Locale.LLoginPanel.PASSWORD_LABEL);    
		this.add(lbPassword);
		
		this.tfPassword = new JPasswordField();   
		this.tfPassword.setColumns(10);
		this.add(tfPassword);		
		
		JButton btLogin = new JButton(Locale.OK_LABEL);    
		this.getRootPane().setDefaultButton(btLogin);
		this.add(btLogin,BorderLayout.SOUTH);
		btLogin.addActionListener(actionHandler);		
		
		this.sLogin = new SLogin();
	}
	
	public VAccount login() {
		String id = this.tfId.getText();
		char[] password = this.tfPassword.getPassword();
		return this.sLogin.login(id);
	}
	

}
