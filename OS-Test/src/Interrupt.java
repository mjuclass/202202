
public class Interrupt {
	public enum EInterrupt {
		eTimeOut,
		eProcessStart,
		eProcessTerminated,
		eRead,
		eReadTerminated,
		eWrite,
		eWriteTerminated,
		eHalt
	}
		
	private EInterrupt eInterrupt;
	private Process process;
	
	public Interrupt(String sInterrupt, Process process) {
		this.eInterrupt = EInterrupt.valueOf(sInterrupt)
		this.process = process;
	}
	public EInterrupt geteInterrupt() {return eInterrupt;}
	public Process getProcess() {return process;}
	
}

