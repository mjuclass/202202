import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI extends Thread {
	private Queue<Interrupt> interruptQueue;
	
	public UI(Queue<Interrupt> interruptQueue) {
		this.interruptQueue = interruptQueue;
	}

	public void run() {
		Loader loader = new Loader();
		
		// console command
		// "r fileName" -> execute fileName
		// "q" -> quit program
		
		Scanner scanner = new Scanner(System.in);
		String command = scanner.next();
		while (command.compareTo("q") != 0) {
			if (command.compareTo("r") == 0) {
				String fileName = scanner.next();
				loader.load(fileName);
			}
			command = scanner.next();
		}
		scanner.close();
	}
	
	private class Loader {	
		public void load(String exeName) {
			try {
				File file = new File("data" + "/" + exeName);
				Scanner scanner = new Scanner(file);
				Process process = new Process();
				process.parse(scanner);
				Interrupt interrupt = new Interrupt(
						Interrupt.EInterrupt.eProcessStart, process);
				interruptQueue.enqueue(interrupt);
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
