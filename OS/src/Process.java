import java.util.Scanner;
import java.util.Vector;

public class Process {
	private int PC;
	private int codeSize, dataSize, stackSize, heapSize;	
	private Vector<String> codeList;
	 
	// getters
	public int getPC() {
		return PC;
	}
	public void setPC(int pC) {
		PC = pC;
	}
	public int getCodeSize() {
		return codeSize;
	}
	public int getDataSize() {
		return dataSize;
	}
	public int getStackSize() {
		return stackSize;
	}
	public int getHeapSize() {
		return heapSize;
	}
	
	public Process() {
		this.codeList = new Vector<String>();
	}
	public void initialize() {
	}
	public void finish() {
	}
	
	
	private void parseData (Scanner scanner) {
		String command = scanner.next();
		while (command.compareTo(".end") != 0) {
			String operand = scanner.next();
			int size = Integer.parseInt(operand);
			if (command.compareTo("codeSize") == 0) {
				this.codeSize = size;
			} else if (command.compareTo("dataSize") == 0) {
				this.dataSize = size;
			} else if (command.compareTo("stackSize") == 0) {
				this.stackSize = size;
			} else if (command.compareTo("heapSize") == 0) {
				this.heapSize = size;
			}
			command = scanner.next();
		}		
	}
	private void parseCode(Scanner scanner) {
		String line = scanner.nextLine();
		while (scanner.hasNext()) {
			this.codeList.add(line);
			line = scanner.nextLine();
		}		
	}
	
	public void parse(Scanner scanner) {
		while (scanner.hasNext()) {
			String token = scanner.next();
			if (token.compareTo(".program") == 0) {				
			} else if (token.compareTo(".code") == 0) {
				this.parseCode(scanner);
			} else if (token.compareTo(".data") == 0) {
				this.parseData(scanner);				
			} else if (token.compareTo(".end") == 0) {
			}
		}
	}

	public boolean executeInstruction() {
		String instruction = this.codeList.get(this.getPC());
		System.out.println(instruction);
		this.setPC(this.getPC()+1);
		if (instruction.compareTo("halt") == 0) {
			return false;
		}
		return true;
	}
}
