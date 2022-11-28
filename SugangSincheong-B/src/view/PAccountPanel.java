package view;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import global.Locale;
import valueObject.VAccount;

public class PAccountPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PAccountPanel(VAccount vAccount) {
		JLabel lName = new JLabel(vAccount.getName());
		this.add(lName);
		
		JLabel lGreeting = new JLabel(Locale.AccountPanel.INSA_POSTFIX);
		this.add(lGreeting);
		
		JLabel lLogin = new JLabel(Locale.AccountPanel.LOGINTIME_PREFIX);
		this.add(lLogin);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Locale.TIME_FORMAT);
		JLabel lTime = new JLabel(simpleDateFormat.format(new Date()));
		this.add(lTime);
		
		JLabel lDescription = new JLabel(Locale.IPNIDA);
		this.add(lDescription);
	}

}
