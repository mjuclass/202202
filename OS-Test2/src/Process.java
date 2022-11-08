import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

public class Process {
	private static final int MAX_REGISTERS = 10;
	private int PC;
	private boolean bGreaterThanEqual;
	private Vector<Integer> registers;
	
	private Vector<Instruction> codeSegment;
	private Vector<Integer> dataSegment;
	private Vector<Integer> stackSegment;
	private Vector<Integer> heapSegment;
	
	private HashMap<String, Integer> symbolTable;
	private HashMap<String, Integer> labelMap;
	
	public Process() {
		this.bGreaterThanEqual = false;
		this.registers = new Vector<Integer>(MAX_REGISTERS);
		for (int i=0; i<MAX_REGISTERS; i++) {
			this.registers.add(i);
		}
		
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
				for (int i=0; i<dataSize; i++) {
					this.dataSegment.add(i);
				}
			} else if (instruction.getCommand().compareTo("stackSize") == 0) {
				int stackSize = Integer.parseInt(instruction.getOperand1());
				this.stackSegment = new Vector<Integer>(stackSize);				
				for (int i=0; i<stackSize; i++) {
					this.stackSegment.add(i);
				}
			} else if (instruction.getCommand().compareTo("heapSize") == 0) {
				int heapSize = Integer.parseInt(instruction.getOperand1());
				this.heapSegment = new Vector<Integer>(heapSize);
				for (int i=0; i<heapSize; i++) {
					this.heapSegment.add(i);
				}
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
	
	private void parsePhaseII() {
		for (Instruction instruction: this.codeSegment ) {
			if (instruction.getCommand().compareTo("greaterThanEqual") == 0) {
				int lineNO = this.labelMap.get(instruction.getOperand1());
				instruction.setOperand1(Integer.toString(lineNO));
			} else if (instruction.getCommand().compareTo("jump") == 0) {
				int lineNO = this.labelMap.get(instruction.getOperand1());
				instruction.setOperand1(Integer.toString(lineNO));
			} else if (
					instruction.getCommand().compareTo("move") == 0 ||
					instruction.getCommand().compareTo("add") == 0 ||
					instruction.getCommand().compareTo("subtract") == 0 ||
					instruction.getCommand().compareTo("load") == 0 ||
					instruction.getCommand().compareTo("stoore") == 0)
			{
				
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
		if (instruction.getCommand().compareTo("interrupt") == 0) {
			Interrupt interrupt = null;
			if (instruction.getOperand1().compareTo("halt") == 0) {
				interrupt = new Interrupt(Interrupt.EInterrupt.eProcessTerminated, this);				
			} else if (instruction.getOperand1().compareTo("readInt") == 0) {
				///////////////
				this.dataSegment.set(this.stackSegment.get(0), 3);
				
				interrupt = new Interrupt(Interrupt.EInterrupt.eReadStart, this);				
			} else if (instruction.getOperand1().compareTo("writeInt") == 0) {
				//////////////
				System.out.println("Interrupt Write:" + this.dataSegment.get(this.stackSegment.get(0)));

				interrupt = new Interrupt(Interrupt.EInterrupt.eWriteStart, this);				
			} else {
				System.out.println("interrupt:" + instruction.getOperand1() + " not supported");
			}
			interruptQueue.enqueue(interrupt);
		} else if (instruction.getCommand().compareTo("load") == 0) {
			this.registers.set(
				Integer.parseInt(instruction.getOperand1().substring(1)),
				this.dataSegment.get(Integer.parseInt(instruction.getOperand2()))				
			);
		} else if (instruction.getCommand().compareTo("store") == 0) {
			this.dataSegment.set(
				Integer.parseInt(instruction.getOperand1()),
				this.registers.get(Integer.parseInt(instruction.getOperand2().substring(1)))
			);
		} else if (instruction.getCommand().compareTo("movec") == 0) {
			this.registers.set(
				Integer.parseInt(instruction.getOperand1().substring(1)),
				Integer.parseInt(instruction.getOperand2())				
			);
		} else if (instruction.getCommand().compareTo("add") == 0) {
			int index1 = Integer.parseInt(instruction.getOperand1().substring(1));			
			int index2 = Integer.parseInt(instruction.getOperand2().substring(1));				
			this.registers.set(index1, this.registers.get(index1) + this.registers.get(index2));
		} else if (instruction.getCommand().compareTo("addc") == 0) {
			int index1 = Integer.parseInt(instruction.getOperand1().substring(1));			
			int value = Integer.parseInt(instruction.getOperand2());				
			this.registers.set(index1, this.registers.get(index1) + value);
		} else if (instruction.getCommand().compareTo("subtract") == 0) {
			int index1 = Integer.parseInt(instruction.getOperand1().substring(1));			
			int index2 = Integer.parseInt(instruction.getOperand2().substring(1));				
			this.registers.set(index1, this.registers.get(index1) - this.registers.get(index2));
			
			if (this.registers.get(index1) < 0) {
				this.bGreaterThanEqual = false;
			} else {
				this.bGreaterThanEqual = true;
			}
		} else if (instruction.getCommand().compareTo("jump") == 0) {
			this.PC = Integer.parseInt(instruction.getOperand1());
		} else if (instruction.getCommand().compareTo("greaterThanEqual") == 0) {
			if (bGreaterThanEqual) {
				this.PC = Integer.parseInt(instruction.getOperand1());
			}
		} else if (instruction.getCommand().compareTo("push") == 0) {
			this.stackSegment.set(0, Integer.parseInt(instruction.getOperand1()));
		} else {
			System.out.println("instruction:" + instruction.getCommand() + " not supported");		}
		
	}
	
	private class Instruction {
		private String[] tokens;
		
		private String command;
		private String operand1;
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
			System.out.println(this.command+this.operand1+this.operand2);
		}
		
		public void setOperand1(String operand) {
			this.operand1 = operand;			
		}
		public void setOperand2(String operand) {
			this.operand2 = operand;			
		}
		
		public String getCommand() {
			return this.command;
		}
		public String getOperand1() {
			return this.operand1;
		}
		public String getOperand2() {
			return this.operand2;
		}		
	}
}
