import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Process {
	public enum EOperand {
		eRegister,
		eAddress,
		eValue
	}

	
	private int PC;
	private Vector<Integer> registers;
	
	private Vector<Instruction> codeSegment;
	private Vector<Integer> dataSegment;
	private Vector<Integer> stackSegment;
	private Vector<Integer> heapSegment;
	
	private HashMap<String, Integer> symbolTable;
	private HashMap<String, Integer> labelMap;
	
	public Process() {
		this.registers = new Vector<Integer>();
		
		this.symbolTable = new HashMap<String, Integer>();
		this.labelMap = new HashMap<String, Integer>();
	}
	public void initialize() {
	}
	public void finish() {
	}	
	
	private void parseData (Scanner scanner) {
		Instruction instruction = new Instruction(scanner);
		while (instruction.getCommand().compareTo(".end") != 0) {
			if (instruction.getCommand().compareTo("codeSize") == 0) {
				int codeSize = Integer.parseInt(instruction.getOperand1());
				this.codeSegment = new Vector<Instruction>(codeSize);				
			} else if (instruction.getCommand().compareTo("dataSize") == 0) {
				int dataSize = Integer.parseInt(instruction.getOperand1());
				this.dataSegment = new Vector<Integer>(dataSize);				
			} else if (instruction.getCommand().compareTo("stackSize") == 0) {
				int stackSize = Integer.parseInt(instruction.getOperand1());
				this.stackSegment = new Vector<Integer>(stackSize);				
			} else if (instruction.getCommand().compareTo("heapSize") == 0) {
				int heapSize = Integer.parseInt(instruction.getOperand1());
				this.heapSegment = new Vector<Integer>(heapSize);
			}
			instruction = new Instruction(scanner);
		}		
	}
	private void parsePhaseI(Scanner scanner) {
		int lineNo = 0;
		Instruction instruction = new Instruction(scanner);
		while (instruction.getCommand().compareTo(".end") != 0) {
			if (instruction.getCommand().compareTo("label") == 0) {
				this.labelMap.put(instruction.getOperand1(), lineNo);
			} else if (instruction.getCommand().compareTo("//") == 0) {
			} else if (instruction.getCommand().compareTo("") == 0) {
			} else {
				this.codeSegment.add(instruction);
				lineNo++;
			}
			instruction = new Instruction(scanner);
		}
	}
	
	private Instruction parseOperand(Instruction instruction) {
		String operand = instruction.getOperand1();
		EOperand eOperand = null;
		if (operand.charAt(0) == 'r') {
			eOperand = EOperand.eRegister;
			operand = operand.substring(1);
		} else if (operand.charAt(0) == '@') {
			eOperand = EOperand.eAddress;
			operand = operand.substring(1);
		} else {
			eOperand = EOperand.eValue;
		}

		return instruction;
	}
	
	private void parsePhaseII() {
		for (Instruction instruction: this.codeSegment ) {
			if (instruction.getCommand().compareTo("greaterThanEqual") == 0) {
				int lineNO = this.labelMap.get(instruction.getOperand1());
				instruction.setOperand1(EOperand.eValue, Integer.toString(lineNO));
			} else if (instruction.getCommand().compareTo("jump") == 0) {
				int lineNO = this.labelMap.get(instruction.getOperand1());
				instruction.setOperand1(EOperand.eValue, Integer.toString(lineNO));
			} else if (
					instruction.getCommand().compareTo("move") == 0 ||
					instruction.getCommand().compareTo("add") == 0 ||
					instruction.getCommand().compareTo("subtract") == 0) {
				
			}
		}		
	}
	private void parseCode(Scanner scanner) {
		this.parsePhaseI(scanner);
		this.parsePhaseII();		
	}
	
	public void parse(Scanner scanner) {
		while (scanner.hasNext()) {
			Instruction instruction = new Instruction(scanner);
			
			if (instruction.getCommand().compareTo(".program") == 0) {				
			} else if (instruction.getCommand().compareTo(".code") == 0) {
				this.parseCode(scanner);
			} else if (instruction.getCommand().compareTo(".data") == 0) {
				this.parseData(scanner);				
			} else {								
			}
		}
	}

	public void executeInstruction(Queue<Interrupt> interruptQueue) {
		Instruction instruction = this.codeSegment.get(this.PC);
		instruction.println();
		this.PC = this.PC+1;
		if (instruction.getCommand().compareTo("halt") == 0) {
			Interrupt interrupt = new Interrupt(Interrupt.EInterrupt.eProcessTerminated, this);
			interruptQueue.enqueue(interrupt);
		}
	}
	
	private class Instruction {
		private String[] tokens;
		
		private String command;
		private EOperand eOperand1;
		private String operand1;
		private EOperand eOperand2;
		private String operand2;
		
		public Instruction(Scanner scanner) {
			String line = scanner.nextLine();
			this.tokens = line.split(" ");
			this.command = this.tokens[0];
			this.operand1 = "";
			this.operand2 = "";
			
			if (this.tokens.length > 1) {
				this.operand1 = this.tokens[1];
			} 
			if (this.tokens.length > 2) {
				this.operand2 = this.tokens[2];				
			}			
		}
		
		public void println() {
			String line = new String();
			for (String token: this.tokens) {
				line = line + token;
			}
			System.out.println(line);
		}
		
		public void setOperand1(EOperand eOperand, String operand) {
			this.eOperand1 = eOperand;
			this.operand1 = operand;			
		}
		public void setOperand2(EOperand eOperand, String operand) {
			this.eOperand2 = eOperand;
			this.operand2 = operand;			
		}
		
		public String getCommand() {
			return command;
		}
		public EOperand getEOperand1() {
			return eOperand1;
		}
		public String getOperand1() {
			return operand1;
		}
		public EOperand getEOperand2() {
			return eOperand2;
		}
		public String getOperand2() {
			return operand2;
		}		
	}
}
