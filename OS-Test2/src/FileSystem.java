import java.util.Vector;

public class FileSystem extends Thread {

	public enum EMode {
		eRead,
		eWrite,
		eClosed
	}
	
	public class FileControlBlock {
		private EMode eMode;
		private Process process;
		private int currentPosition;
		public EMode geteMode() {
			return eMode;
		}
		public void seteMode(EMode eMode) {
			this.eMode = eMode;
		}
		public Process getProcess() {
			return process;
		}
		public void setProcess(Process process) {
			this.process = process;
		}
		public int getCurrentPosition() {
			return currentPosition;
		}
		public void setCurrentPosition(int currentPosition) {
			this.currentPosition = currentPosition;
		}		
	}
	
	private Vector<FileControlBlock> fileHeaders;
	private Vector<Vector<Integer>> directory;
	
	private Queue<Interrupt> interruptQueue;	
	private Queue<Interrupt> fileIOInterruptQueue;
	
	public FileSystem(Queue<Interrupt> interruptQueue, Queue<Interrupt> fileIOInterruptQueue) {
		this.fileHeaders = new Vector<FileControlBlock>();	
		this.directory = new Vector<Vector<Integer>>();	
		
		this.interruptQueue = interruptQueue;
		this.fileIOInterruptQueue = fileIOInterruptQueue;
	}
	
	public void initialize() {
	}
	public void finish() {	
	}

	private void openStart(Process process) {
		int fileId = process.pop();
		int iMode = process.pop();
		EMode eMode = EMode.values()[iMode];
		if (fileId < this.fileHeaders.size()) {				
			if (eMode == EMode.eRead) {
				if (this.fileHeaders.get(fileId).geteMode() == EMode.eClosed) {
					this.fileHeaders.get(fileId).seteMode(eMode);								
					this.fileHeaders.get(fileId).setProcess(process);
					this.fileHeaders.get(fileId).setCurrentPosition(0);
				} 
			} else if (eMode == EMode.eWrite) {
				this.fileHeaders.get(fileId).seteMode(eMode);								
				this.fileHeaders.get(fileId).setProcess(process);
				this.fileHeaders.get(fileId).setCurrentPosition(0);
			} 
		} else {
			if (eMode == EMode.eWrite) {
				FileControlBlock fileControlBlcok = new FileControlBlock();
				fileControlBlcok.seteMode(eMode);								
				fileControlBlcok.setProcess(process);
				fileControlBlcok.setCurrentPosition(0);
				this.fileHeaders.add(fileControlBlcok);
			}
		}
	}
	private void readStart(Process process) {
	}
	private void writeStart(Process process) {
	}
	
	public void run() {
		while (true) {
			Interrupt interrupt = this.fileIOInterruptQueue.dequeue();
			if (interrupt != null) {
				Process process = interrupt.getProcess();
				switch(interrupt.geteInterrupt()) {
				case eOpenStart:
					this.openStart(process);
					break;
				case eReadStart:
					this.readStart(process);
					break;
				case eWriteStart:
					this.writeStart(process);
					break;
				default:
					break;
				}
			}
		}
	}
}
