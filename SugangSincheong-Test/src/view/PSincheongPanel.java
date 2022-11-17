package view;

import view.PSugangsincheongPanel.ListSelectionHandler;

public class PSincheongPanel extends PLectureTable {
	private static final long serialVersionUID = 1L;
	
	public PSincheongPanel(ListSelectionHandler listSelectionHandler) {
		this.getSelectionModel().addListSelectionListener(listSelectionHandler);	
	}

}
