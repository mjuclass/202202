package control;

import model.DataAccessObject;
import model.MLogin;
import valueObject.VLogin;

public class CLogin {

	public boolean validateUser(VLogin vLogin) {
		boolean bResult = false;
		
		DataAccessObject dataAccessObject = new DataAccessObject();
		MLogin mLogin = (MLogin) dataAccessObject.getAModel("UserId", MLogin.class, vLogin.getUserId());
		if (mLogin != null) {
			if (vLogin.getPassword().contentEquals(mLogin.getPassword())) {
				bResult = true;
			} else {
				// password mismatch
			}
		} else {
			// no userId
		}		
		return bResult;
	}
}
