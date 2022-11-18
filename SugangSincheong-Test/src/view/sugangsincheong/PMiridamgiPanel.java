package view.sugangsincheong;

import view.sugangsincheong.PSugangsincheongPanel.ListSelectionHandler;

public class PMiridamgiPanel extends PLectureTable {
	private static final long serialVersionUID = 1L;

	public PMiridamgiPanel(ListSelectionHandler listSelectionHandler) {
		super("department/");
		this.getSelectionModel().addListSelectionListener(listSelectionHandler);
	}
}
