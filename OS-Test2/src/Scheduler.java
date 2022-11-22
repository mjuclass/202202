public class Scheduler extends Thread {
	// component
	private InterruptHandler interruptHandler;
	private Queue<Process> readyQueue;
	private Queue<Process> waitQueue;
	
	// associations
	private Queue<Interrupt> interruptQueue;
	
	// working variables
	private boolean bPowerOn;
	private Process runningProcess;

	/////////////////////////////////////////////////
	public Scheduler( 
			Queue<Interrupt> interruptQueue) {
		// components
		this.interruptHandler = new InterruptHandler();			
		this.readyQueue = new Queue<Process>();
		this.waitQueue = new Queue<Process>();
		
		// associations
		this.interruptQueue = interruptQueue;
		
		// working objects
		this.runningProcess = null;			
		this.bPowerOn = true;
	}

	public void run() {
		while (this.bPowerOn) {
			this.interruptHandler.handle();
			if (this.runningProcess != null) {
				this.runningProcess.executeInstruction(interruptQueue);
			} else {
				this.runningProcess = this.readyQueue.dequeue();
			}
		}
	}
	
	private class InterruptHandler {

		public InterruptHandler() {
		}
		
		private void HandleTimeOut(Process process) {
			readyQueue.enqueue(runningProcess);
			runningProcess = readyQueue.dequeue();
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
			waitQueue.enqueue(runningProcess);
			runningProcess = readyQueue.dequeue();
		}
		private void HandleReadTerminated(Process process) {
			readyQueue.enqueue(process);
		}
		private void HandleWriteStart(Process process) {
			waitQueue.enqueue(runningProcess);
			runningProcess = readyQueue.dequeue();
		}
		private void HandleWriteTerminated(Process process) {
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
