package view;

import view.PSugangsincheongPanel.ListSelectionHandler;

public class PMiridamgiPanel extends PLectureTable {
	private static final long serialVersionUID = 1L;

	public PMiridamgiPanel(ListSelectionHandler listSelectionHandler) {
		this.getSelectionModel().addListSelectionListener(listSelectionHandler);
	}
}
