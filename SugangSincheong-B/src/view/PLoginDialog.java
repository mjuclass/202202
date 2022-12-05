package view;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		LayoutManager layoutManager = new GridBagLayout();
		this.setLayout(layoutManager);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JLabel lbId=new JLabel("아이디");    
		this.add(lbId, gridBagConstraints);
		 		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		this.add(tfId, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		JLabel lbPassword =new JLabel("비밀번호");    
		this.add(lbPassword, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		this.tfPassword = new JPasswordField();   
		this.tfPassword.setColumns(10);
		this.add(tfPassword, gridBagConstraints);
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		JButton btLogin = new JButton("Login");
		this.getRootPane().setDefaultButton(btLogin);
		this.add(btLogin, gridBagConstraints);
		
		btLogin.addActionListener(actionHandler);
		
		this.sLogin = new SLogin();
	}
	
	public VAccount login() {
		String id = this.tfId.getText();
		char[] password = this.tfPassword.getPassword();
		return this.sLogin.login(id);
	}
	
}
