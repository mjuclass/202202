package constants;

public class Constants {
	
	public enum EConfigurations {
		miridamgiFilePostfix("M"),
		singcheongFilePostfix("S");
		
		private String text;
		private EConfigurations(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}
	}
	
	public enum EPResultPanel {
		gangjwaNo("���¹�ȣ"),
		gangjwaName("���¸�");
		
		private String text;
		private EPResultPanel(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}
	}

	public enum EPGangjwaSelectionPanel {
		gangjwaNo("���¹�ȣ"),
		gangjwaName("���¸�"),
		damdangGyosu("��米��"),
		hakjeom("����"),
		time("�ð�");
		
		private String text;
		private EPGangjwaSelectionPanel(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}		
	}
	
	public enum EPHakgwaSelectionPanel {
		rootFileName("root"),
		campus("ķ�۽�"),
		college("����"),
		hakgwa("�а�");
		
		private String text;
		private EPHakgwaSelectionPanel(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}		
	}
	
	public enum ELoginDialog {
		width("300"),
		height("200"),
		nameLabel(" ���̵�   "),
		sizeNameText("10"),
		passwordLabel("��й�ȣ"),
		sizePasswordText("10"),
		okButtonLabel("ok"),
		cancelButtonLabel("cancel"),
		loginFailed("���̵� �н����尡 Ʋ��");
		
		private String text;
		private ELoginDialog(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}
	}

	public enum EMainFrame {
		width("1000"),
		height("600");
		
		private String text;
		private EMainFrame(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
		public int getInt() {
			return Integer.parseInt(text);
		}
	}
	
	public enum EMenuBar {
		eFile("����"),
		eEdit("����");
		
		String text;
		EMenuBar(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EFileMenu {
		eNew("���θ����"),
		eOpen("����"),
		eSave("�����ϱ�"),
		eSaveAs("�ٸ��̸�����"),
		ePrint("����Ʈ"),
		eExit("����");
		
		String text;
		EFileMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
	
	public enum EEditMenu {
		eCopy("����"),
		eCut("�߶󳻱�"),
		ePaste("���̱�"),
		eDelete("����");
		
		String text;
		EEditMenu(String text) {
			this.text = text;
		}
		public String getText() {
			return this.text;
		}
	}
}
