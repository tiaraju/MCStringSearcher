package util;

import java.util.ArrayList;
import java.util.List;

public class MemoryWatcher implements Runnable{
	
	private List<Long> memoryConsumption;
	private volatile boolean running = true;
	
	@Override
	public void run() {
		this.memoryConsumption = new ArrayList<Long>();
		while(running){
			memoryConsumption.add(Runtime.getRuntime().totalMemory());
		}
		
	}
	
	
	private void stopWatcher(){
		this.running = false;
	}
	
	

}
