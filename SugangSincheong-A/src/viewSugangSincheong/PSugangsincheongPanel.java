package viewSugangSincheong;
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
	
	private void moveFromLectureToMiridamgi() {
		Vector<VLecture> vLectures = this.directoryPanel.getSelectedLectures();
		this.miridamgiPanel.addLectures(vLectures);
		System.out.println("1moveFromLectureToMiridamgi");
	}
	private void moveFromMiridamgiToLecture() {
		Vector<VLecture> vLectures = this.miridamgiPanel.getSelectedLectures();
		this.directoryPanel.addLectures(vLectures);
		System.out.println("2moveFromMiridamgiToLecture");
	}
	private void moveFromMiridamgiToSincheong() {
		Vector<VLecture> vLectures = this.miridamgiPanel.getSelectedLectures();
		this.sincheongPanel.addLectures(vLectures);
		System.out.println("3moveFromMiridamgiToSincheong");
	}
	private void moveFromSincheongToMiridamgi() {
		Vector<VLecture> vLectures = this.sincheongPanel.getSelectedLectures();
		this.miridamgiPanel.addLectures(vLectures);
		System.out.println("4moveFromSincheongToMiridamgi");
	}
	
	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
				if (event.getActionCommand().compareTo("1>>")==0) {
					moveFromLectureToMiridamgi();
				} else if (event.getActionCommand().compareTo("1<<")==0) {
					moveFromMiridamgiToLecture();
				} else if (event.getActionCommand().compareTo("2>>")==0) {
					moveFromMiridamgiToSincheong();
				} else if (event.getActionCommand().compareTo("2<<")==0) {
					moveFromSincheongToMiridamgi();
				}
			
		}		
	}
}
