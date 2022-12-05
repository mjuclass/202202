public class Main {
	
	private Queue<Interrupt> interruptQueue;
	private Queue<Interrupt> fileIOInterruptQueue;
	
	private UI ui;
	private Scheduler scheduler;
	private FileSystem fileSystem;	
	
	public Main() {		
		this.interruptQueue = new QueueSynchronized<Interrupt>();
		this.fileIOInterruptQueue = new QueueSynchronized<Interrupt>();
		
		this.ui = new UI(this.interruptQueue);		
		this.scheduler = new Scheduler(this.interruptQueue, fileIOInterruptQueue);
		this.fileSystem = new FileSystem(this.interruptQueue, fileIOInterruptQueue);		
	}
	private void initialize() {
		this.interruptQueue.initialize();
		this.fileIOInterruptQueue.initialize();
		
		this.ui.initialize();		
		this.scheduler.initialize();
		this.fileSystem.initialize();
	}
	private void finish() {		
		this.ui.finish();		
		this.scheduler.finish();
		this.fileSystem.finish();
		
		this.interruptQueue.finish();
		this.fileIOInterruptQueue.finish();
	}

	private void run() {		
		ui.start();		
		scheduler.start();		
		fileSystem.start();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.run();
		main.finish();
	}


}
