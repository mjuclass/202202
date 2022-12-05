public class Scheduler extends Thread {
	
	// associations
	private Queue<Interrupt> interruptQueue;
	private Queue<Interrupt> fileIOInterruptQueue;
	// component
	private Queue<Process> readyQueue;
	private Queue<Process> waitQueue;
	
	private InterruptHandler interruptHandler;
	
	// working variables
	private boolean bPowerOn;
	private Process runningProcess;

	/////////////////////////////////////////////////
	public Scheduler(Queue<Interrupt> interruptQueue, Queue<Interrupt> fileIOInterruptQueue) {
		this.interruptQueue = interruptQueue;
		this.fileIOInterruptQueue = fileIOInterruptQueue;
		
		this.readyQueue = new Queue<Process>();
		this.waitQueue = new Queue<Process>();
		
		this.interruptHandler = new InterruptHandler(
				this.interruptQueue, this.fileIOInterruptQueue,
				this.readyQueue, this.waitQueue);			
		
		// working objects
		this.runningProcess = null;			
		this.bPowerOn = true;
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

		private Queue<Interrupt> interruptQueue;
		private Queue<Interrupt> fileIOInterruptQueue;
		private Queue<Process> readyQueue;
		private Queue<Process> waitQueue;
		
		public InterruptHandler(
				Queue<Interrupt> interruptQueue, Queue<Interrupt> fileIOInterruptQueue,
				Queue<Process> readyQueue, Queue<Process> waitQueue) {
			this.interruptQueue = interruptQueue;
			this.fileIOInterruptQueue = fileIOInterruptQueue;
			
			this.readyQueue = readyQueue;
			this.waitQueue = waitQueue;
		}
		
		private void HandleTimeOut(Process process) {
			this.readyQueue.enqueue(process);
			runningProcess = this.readyQueue.dequeue();
		}
		private void HandleProcessStart(Process process) {
			process.initialize();
			this.readyQueue.enqueue(process);
		}
		private void HandleProcessTerminated(Process process) {
			process.finish();
			runningProcess = this.readyQueue.dequeue();;
		}
		
		private void HandleFileIOStart(Interrupt.EInterrupt eInterrupt, Process process) {
			waitQueue.enqueue(process);
			Interrupt interrupt = new Interrupt(eInterrupt, process);
			this.fileIOInterruptQueue.enqueue(interrupt);
			runningProcess = this.readyQueue.dequeue();
		}
		private void HandleFileIOTerminated(Interrupt.EInterrupt eInterrupt, Process process) {
			waitQueue.remove(process);
			readyQueue.enqueue(process);
		}

		public void handle() {
			Interrupt interrupt = this.interruptQueue.dequeue();
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
				case eOpenStart:
				case eCloseStart:
				case eReadStart:
				case eWriteStart:
					HandleFileIOStart(interrupt.geteInterrupt(), interrupt.getProcess());
					break;
				case eOpenTerminated:
				case eCloseTerminated:
				case eReadTerminated:
				case eWriteTerminated:
					HandleFileIOTerminated(interrupt.geteInterrupt(), interrupt.getProcess());
					break;
			default:
					break;
				}
			}
		}
	}

}
