import java.util.Vector;

public class FileSystem extends Thread {

	private Queue<Interrupt> interruptQueue;
	private Vector<Integer> disk;
	
	public FileSystem(Queue<Interrupt> interruptQueue) {
		this.disk = new Vector<Integer>();
		
		this.interruptQueue = interruptQueue;
	}
	
	public void initialize() {
	}
	public void finish() {
	}

	
	public void run() {
		Interrupt interrupt = null;
		while(true) {
			interrupt = this.interruptQueue.dequeue();
			Process process = interrupt.getProcess();
			if (interrupt.geteInterrupt() == Interrupt.EInterrupt.eReadStart) {
				int address = process.pop();
				int value = this.disk.get(address);
				process.push(value);
				interrupt = new Interrupt(Interrupt.EInterrupt.eReadTerminated, process);
				this.interruptQueue.enqueue(interrupt);
			} else if (interrupt.geteInterrupt() == Interrupt.EInterrupt.eWriteStart) {
				int address = process.pop();
				int value = process.pop();
				this.disk.set(address,  value);
				interrupt = new Interrupt(Interrupt.EInterrupt.eWriteTerminated, process);
				this.interruptQueue.enqueue(interrupt);
			}
		}
	}
}
