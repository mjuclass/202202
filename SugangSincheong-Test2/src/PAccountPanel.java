import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PAccountPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public PAccountPanel(VAccount vAccount) {
		JLabel lName = new JLabel(vAccount.getName());
		this.add(lName);
		
		JLabel lGreeting = new JLabel("�� �ȳ��ϼ���! ");
		this.add(lGreeting);
		
		JLabel lLogin = new JLabel("�α��� �ð���");
		this.add(lLogin);
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
		JLabel lTime = new JLabel(simpleDateFormat.format(new Date()));
		this.add(lTime);
		
		JLabel lDescription = new JLabel("�Դϴ�");
		this.add(lDescription);
	}

	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
