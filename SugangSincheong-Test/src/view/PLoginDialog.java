package view;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import service.SLogin;
import valueObject.VAccount;
import view.Main.ActionHandler;

public class PLoginDialog extends JDialog {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private JTextField textFieldId;
	private JPasswordField passwordFieldPassword;
	private JButton buttonOK, buttonCancel;
	private SLogin sLogin;
	
	public PLoginDialog(Frame parent, ActionHandler actionHandler) {
		super(parent, Constants.LoginDialog.LABEL_TITLE);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(size.width/2 - Constants.LoginDialog.WIDTH/2, Constants.LoginDialog.Y);
		this.setSize(Constants.LoginDialog.WIDTH, Constants.LoginDialog.HIGHT);
		this.setModal(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		LayoutManager layoutManager = new GridBagLayout();
		this.setLayout(layoutManager);
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		JLabel labelId = new JLabel(Constants.LoginDialog.LABEL_ID);
		this.add(labelId, gridBagConstraints);
		 		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;;
		this.textFieldId = new JTextField();
		this.textFieldId.setColumns(Constants.LoginDialog.SIZE_COLUMN_ID);
		this.add(textFieldId, gridBagConstraints);		
	
		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = GridBagConstraints.CENTER;
		JLabel lbPassword =new JLabel(Constants.LoginDialog.LABEL_PASSWORD);    
		this.add(lbPassword, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		this.passwordFieldPassword = new JPasswordField();   
		this.passwordFieldPassword.setColumns(Constants.LoginDialog.SIZE_COLUMN_PASSWORD);
		this.add(passwordFieldPassword,gridBagConstraints);
	
	
		gridBagConstraints.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints.ipadx = 20;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		this.buttonOK = new JButton(Constants.LABEL_OK);    
		this.buttonOK.addActionListener(actionHandler);
		this.getRootPane().setDefaultButton(this.buttonOK);
		this.add(this.buttonOK, gridBagConstraints);
		
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
	this.buttonCancel = new JButton(Constants.LABEL_CANCEL);    
		this.buttonCancel.addActionListener(actionHandler);
		this.add(this.buttonCancel, gridBagConstraints);			

		this.sLogin = new SLogin();
	}
	
	public void initialize() {
		this.setVisible(true);
	}
	
	public VAccount getAccount(Object object) {
		VAccount vAccount = null;
		if (object == this.buttonOK) {
			String id = this.textFieldId.getText();
			char[] password = this.passwordFieldPassword.getPassword();
			vAccount = this.sLogin.login(id, password);
		} else {
		}
		this.dispose();
		return vAccount;
	}
	

}
