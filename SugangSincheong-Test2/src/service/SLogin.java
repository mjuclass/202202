package service;

import entity.EAccount;
import valueObject.VAccount;

public class SLogin {
	private EAccount eAccount;
	public SLogin() {
		this.eAccount = new EAccount();
	}

	public VAccount login(String id, char[] password) {
		VAccount vAccount = this.eAccount.getAccount(id);
		if (vAccount != null) {
			if (vAccount.getPassword().compareTo(String.valueOf(password)) == 0) {
				return vAccount;
			}
		}
		return null;
	}

}
