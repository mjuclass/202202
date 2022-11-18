package view.sugangsincheong;

import view.sugangsincheong.PSugangsincheongPanel.ListSelectionHandler;

public class PSincheongPanel extends PLectureTable {
	private static final long serialVersionUID = 1L;
	
	public PSincheongPanel(ListSelectionHandler listSelectionHandler) {
		super("department/");
		this.getSelectionModel().addListSelectionListener(listSelectionHandler);	
	}

}
