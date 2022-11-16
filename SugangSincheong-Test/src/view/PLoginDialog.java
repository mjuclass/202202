package view;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.SLogin;
import valueObject.VAccount;
import view.Main.ActionHandler;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfId;
	private JPasswordField tfPassword;
	private JButton okButton, cancelButton;
	private SLogin sLogin;
	
	public PLoginDialog(Frame parent, ActionHandler actionHandler) {
		super(parent, "Login");
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - this.getWidth(), 100);
		this.setSize(300,200);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		FlowLayout layoutManager = new FlowLayout(FlowLayout.CENTER, 0, 5);
		this.setLayout(layoutManager);
		
		JPanel idPanel = new JPanel();		
			JLabel lbId=new JLabel("  아이디 ");    
			idPanel.add(lbId);
			 		
			this.tfId = new JTextField();
			this.tfId.setColumns(10);
			idPanel.add(tfId);		
		this.add(idPanel);
		
		JPanel passwordPanel = new JPanel();		
			JLabel lbPassword =new JLabel("비밀번호");    
			passwordPanel.add(lbPassword);
			
			this.tfPassword = new JPasswordField();   
			this.tfPassword.setColumns(10);
			passwordPanel.add(tfPassword);
		this.add(passwordPanel);
		
		
		JPanel commandPanel = new JPanel();		
			this.okButton = new JButton("OK");    
			this.okButton.addActionListener(actionHandler);
			this.getRootPane().setDefaultButton(this.okButton);
			commandPanel.add(this.okButton);
			
			this.cancelButton = new JButton("Cancel");    
			this.cancelButton.addActionListener(actionHandler);
			commandPanel.add(this.cancelButton);			
		this.add(commandPanel);
		
		this.sLogin = new SLogin();
	}
	
	public void initialize() {
		this.setVisible(true);
	}
	
	public VAccount getAccount(Object object) {
		VAccount vAccount = null;
		if (object == this.okButton) {
			String id = this.tfId.getText();
			char[] password = this.tfPassword.getPassword();
			vAccount = this.sLogin.login(id, password);
		} else {
		}
		this.dispose();
		return vAccount;
	}
	

}
