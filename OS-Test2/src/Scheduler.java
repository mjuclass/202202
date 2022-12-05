public class Scheduler extends Thread {
	// working variables
	private boolean bPowerOn;
	private Process runningProcess;
	
	// component
	private Queue<Process> readyQueue;
	private Queue<Process> waitQueue;
	private InterruptHandler interruptHandler;
	
	// associations
	private Queue<Interrupt> interruptQueue;	

	/////////////////////////////////////////////////
	public Scheduler( Queue<Interrupt> interruptQueue) {
		// working objects
		this.runningProcess = null;			
		this.bPowerOn = true;
		
		// components
		this.interruptHandler = new InterruptHandler();			
		this.readyQueue = new Queue<Process>();
		this.waitQueue = new Queue<Process>();
		
		// associations
		this.interruptQueue = interruptQueue;		
	}
	public void initialize() {	
	}
	
	public void finish() {
	}
	
	public void run() {
		while (this.bPowerOn) {
			this.interruptHandler.handle();
			if (this.runningProcess == null) {
				this.runningProcess = this.readyQueue.dequeue();
			} else {
				this.runningProcess.executeInstruction(interruptQueue);
			}
		}
	}
	
	private class InterruptHandler {

		public InterruptHandler() {
		}
		
		public void initialize() {	
		}
		
		public void finish() {
		}
		
		private void HandleTimeOut(Process process) {
//			getReadyQueue().enqueue(runningProcess);
//			runningProcess = getReadyQueue().dequeue();
		}
		private void HandleProcessStart(Process process) {
			process.initialize();
			readyQueue.enqueue(process);
		}
		private void HandleProcessTerminated(Process process) {
			process.finish();
			runningProcess = null;
		}
		private void HandleReadStart(Process process) {
			// io start
			waitQueue.enqueue(runningProcess);			
			// fileSystem.read(runningProcess);			
			runningProcess = readyQueue.dequeue();
		}
		private void HandleReadTerminated(Process process) {
			waitQueue.remove(process);
			readyQueue.enqueue(process);
		}
		private void HandleWriteStart(Process process) {
			// io start
			waitQueue.enqueue(runningProcess);
			runningProcess = readyQueue.dequeue();
		}
		private void HandleWriteTerminated(Process process) {
			waitQueue.remove(process);
			readyQueue.enqueue(process);
		}


		public void handle() {
			Interrupt interrupt = interruptQueue.dequeue();
			if (interrupt != null) {
				switch (interrupt.geteInterrupt()) {
				case eTimeOut:
					HandleTimeOut(interrupt.getProcess());
					break;
				case eProcessStart:
					HandleProcessStart(interrupt.getProcess());
					break;
				case eProcessTerminated:
					HandleProcessTerminated(interrupt.getProcess());
					break;
				case eReadStart:
					HandleReadStart(interrupt.getProcess());
					break;
				case eReadTerminated:
					HandleReadTerminated(interrupt.getProcess());
					break;
				case eWriteStart:
					HandleWriteStart(interrupt.getProcess());
					break;
				case eWriteTerminated:
					HandleWriteTerminated(interrupt.getProcess());
					break;
				default:
					break;
				}
			}
		}
	}

}
