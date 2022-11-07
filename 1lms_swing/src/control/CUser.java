package control;

import model.DataAccessObject;
import model.MUser;
import valueObject.VUser;

public class CUser {

	public VUser getUser(String userId) {
		DataAccessObject dataAccessObject = new DataAccessObject();
		MUser mUser = (MUser) dataAccessObject.getAModel(userId, MUser.class, userId);
		if (mUser != null) {
			VUser vUser = new VUser(mUser.getUserId(), mUser.getName(), mUser.getAddress());
			return vUser;
		}
		return null;
	}
}
