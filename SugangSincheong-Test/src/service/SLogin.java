package service;

import entity.EAccount;
import valueObject.VAccount;

public class SLogin {
	private EAccount eAccount;
	public SLogin() {
		this.eAccount = new EAccount();
	}

	public VAccount login(String id) {
		VAccount vAccount = this.eAccount.getAccount(id);
		return vAccount;
	}

}
