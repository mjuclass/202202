import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EAccount {
	private String Id;
	private String password;
	private String name;
	
	
	public EAccount() {
	}

	private boolean read(String id) {
		boolean found = false;
		try {
			File file = new File("account/account");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext() && !found) {
				this.Id = scanner.next();
				this.password = scanner.next();
				this.name = scanner.next();
				
				if (this.Id.compareTo(id) == 0) {
					found = true;
				}
			}
			
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return found;
		
	}
	public VAccount getAccount(String id) {
		VAccount vAccount = null;
		// file read
		boolean found = this.read(id);
		if (found) {
			vAccount = new VAccount();
			vAccount.setId(this.Id);
			vAccount.setPassword(this.password);
			vAccount.setName(this.name);
		}
		
		return vAccount;
	}

}
