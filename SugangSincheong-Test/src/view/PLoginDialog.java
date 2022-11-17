package view;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
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
		
		
		LayoutManager layoutManager = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		this.setLayout(layoutManager);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setSize(10,10);
		this.add(panelHeader);
		
		JPanel panelID = new JPanel();		
			JLabel labelId = new JLabel(Constants.LoginDialog.LABEL_ID);
			panelID.add(labelId);
			 		
			this.textFieldId = new JTextField();
			this.textFieldId.setColumns(Constants.LoginDialog.SIZE_COLUMN_ID);
			panelID.add(textFieldId);		
		this.add(panelID);
		
		JPanel panelPassword = new JPanel();		
			JLabel lbPassword =new JLabel(Constants.LoginDialog.LABEL_PASSWORD);    
			panelPassword.add(lbPassword);
			
			this.passwordFieldPassword = new JPasswordField();   
			this.passwordFieldPassword.setColumns(Constants.LoginDialog.SIZE_COLUMN_PASSWORD);
			panelPassword.add(passwordFieldPassword);
		this.add(panelPassword);
		
		
		JPanel panelCommand = new JPanel();		
			this.buttonOK = new JButton(Constants.LABEL_OK);    
			this.buttonOK.addActionListener(actionHandler);
			this.getRootPane().setDefaultButton(this.buttonOK);
			panelCommand.add(this.buttonOK);
			
			this.buttonCancel = new JButton(Constants.LABEL_CANCEL);    
			this.buttonCancel.addActionListener(actionHandler);
			panelCommand.add(this.buttonCancel);			
		this.add(panelCommand);
		
		JPanel paenlFooter = new JPanel();
		paenlFooter.setSize(10,10);
		this.add(paenlFooter);

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
