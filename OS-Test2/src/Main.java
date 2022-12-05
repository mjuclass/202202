public class Main {
	private Queue<Interrupt> interruptQueue;
	
	private Scheduler scheduler;
	private UI ui;
	private FileSystem fileSystem;
	
	public Main() {		
		this.interruptQueue = new QueueSynchronized<Interrupt>();
		
		this.scheduler = new Scheduler(interruptQueue);
		this.ui = new UI(interruptQueue);		
		this.fileSystem = new FileSystem(interruptQueue);		
	}
	private void initialize() {
		this.interruptQueue.initialize();
		
		this.scheduler.initialize();
		this.ui.initialize();
		this.fileSystem.initialize();
	}
	
	private void finish() {		
		this.scheduler.finish();
		this.ui.finish();
		this.fileSystem.finish();
		
		this.interruptQueue.finish();
	}

	private void run() {
		scheduler.start();		
		ui.start();		
		fileSystem.start();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.run();
		main.finish();
	}


}
