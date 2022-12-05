
public class QueueSynchronized<T> extends Queue<T>{
	private static final long serialVersionUID = 1L;
	
	private static final int MAX_NUM_ELEMENT = 10;

	public QueueSynchronized() {
		super(MAX_NUM_ELEMENT);
	}
	
	public void initialize() {	
	}
	
	public void finish() {
	}
	
	public synchronized void enqueue(T element) {
		super.enqueue(element);
	}
	public synchronized T dequeue() {
		T element = super.dequeue();
		return element;
	}
}
