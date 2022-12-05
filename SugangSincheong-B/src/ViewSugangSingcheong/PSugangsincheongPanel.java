package ViewSugangSingcheong;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import valueObject.VLecture;

public class PSugangsincheongPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private PDirectoryPanel directoryPanel;
	private PControlPanel controlPanel1;
	private PMiridamgiPanel miridamgiPanel;
	private PControlPanel controlPanel2;
	private PSincheongPanel sincheongPanel;
	
	public PSugangsincheongPanel() {
		ActionHandler actionHandler = new ActionHandler();
		
		LayoutManager layoutManager = new BoxLayout(this, BoxLayout.X_AXIS);
		this.setLayout(layoutManager);
		
		this.directoryPanel = new PDirectoryPanel();
		this.add(this.directoryPanel);
		
		this.controlPanel1 = new PControlPanel("1", actionHandler);
		this.add(this.controlPanel1);

		JScrollPane scrollPane = new JScrollPane();
		this.miridamgiPanel = new PMiridamgiPanel();
		scrollPane.setViewportView(this.miridamgiPanel);
		this.add(scrollPane);
		
		this.controlPanel2 = new PControlPanel("2", actionHandler);
		this.add(this.controlPanel2);
		
		scrollPane = new JScrollPane();
		this.sincheongPanel = new PSincheongPanel();
		scrollPane.setViewportView(this.sincheongPanel);
		this.add(scrollPane);
	}
	private void moveFromLecturesToMiridamgi() {
		Vector<VLecture> lectures = this.directoryPanel.getSelectedLectures();
		this.miridamgiPanel.addLectures(lectures);
	}
	private void moveFromMiridamgiToLectures() {
		Vector<VLecture> lectures = this.miridamgiPanel.getSelectedLectures();
		this.directoryPanel.addLectures(lectures);
	}
	public void moveFromMiridamgiToSincheong() {
		Vector<VLecture> lectures = this.miridamgiPanel.getSelectedLectures();
		this.sincheongPanel.addLectures(lectures);
	}
	public void moveFromSincheongToMiridamgi() {
		Vector<VLecture> lectures = this.sincheongPanel.getSelectedLectures();
		this.miridamgiPanel.addLectures(lectures);
	}

	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().compareTo("1>>")==0) {
				moveFromLecturesToMiridamgi();
				System.out.println("1>>");
			} else if (event.getActionCommand().compareTo("1<<")==0) {
				moveFromMiridamgiToLectures();
				System.out.println("1<<");
			} else if (event.getActionCommand().compareTo("2>>")==0) {
				moveFromMiridamgiToSincheong();
				System.out.println("2>>");
			} else if (event.getActionCommand().compareTo("2<<")==0) {
				moveFromSincheongToMiridamgi();
				System.out.println("2<<");
			}			
		}		
	}
}
