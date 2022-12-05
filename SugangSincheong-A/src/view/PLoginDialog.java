package view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		
		LayoutManager layoutManager = new GridBagLayout();
		this.setLayout(layoutManager);
		GridBagConstraints gridBagConstraint = new GridBagConstraints();
		
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 0;
		JLabel lbId=new JLabel(Locale.LLoginPanel.ID_LABEL);    
		this.add(lbId, gridBagConstraint);
		 		
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 0;
		this.tfId = new JTextField();
		this.tfId.setColumns(10);
		this.add(tfId, gridBagConstraint);
		
		gridBagConstraint.gridx = 0;
		gridBagConstraint.gridy = 1;
		JLabel lbPassword =new JLabel(Locale.LLoginPanel.PASSWORD_LABEL);    
		this.add(lbPassword, gridBagConstraint);
		
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 1;
		this.tfPassword = new JPasswordField();   
		this.tfPassword.setColumns(10);
		this.add(tfPassword, gridBagConstraint);		
		
		gridBagConstraint.gridx = 1;
		gridBagConstraint.gridy = 2;
		JButton btLogin = new JButton(Locale.OK_LABEL);    
		this.getRootPane().setDefaultButton(btLogin);
		this.add(btLogin,gridBagConstraint);
		btLogin.addActionListener(actionHandler);		
		
		this.sLogin = new SLogin();
	}
	
	public VAccount login() {
		String id = this.tfId.getText();
		char[] password = this.tfPassword.getPassword();
		return this.sLogin.login(id);

	}

}
